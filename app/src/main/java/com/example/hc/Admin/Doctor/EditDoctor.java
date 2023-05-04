package com.example.hc.Admin.Doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hc.Model.*;
import com.example.hc.R;
import com.example.hc.profile.UpdateProfile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditDoctor extends AppCompatActivity {

    CircleImageView doctorProfile;
    Button editDoctor;
    TextInputLayout fullname,specialist,Describation,phone,location,city,state,pincode,fees,schedule;
    private DatabaseReference reference;
    private Uri imageUri;
    private String myUri="";
    String docId,docImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor2);

        doctorProfile = findViewById(R.id.EdDoctorProfile);
        fullname = findViewById(R.id.EdFullname);
        specialist = findViewById(R.id.EdSpecialist);
        Describation = findViewById(R.id.EdDescription);
        phone = findViewById(R.id.EdPhono);
        location = findViewById(R.id.EdAddress);
        city = findViewById(R.id.EdCity);
        state = findViewById(R.id.EdState);
        pincode = findViewById(R.id.EdPincode);
        fees = findViewById(R.id.Edfees);
        schedule = findViewById(R.id.Edschedule);
        editDoctor = findViewById(R.id.editDoctor);

        doctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });



        docId = getIntent().getStringExtra("docId");
        docImg = getIntent().getStringExtra("docImg");
        Picasso.get().load(getIntent().getStringExtra("docImg")).into(doctorProfile);
        fullname.getEditText().setText(getIntent().getStringExtra("DocName"));
        specialist.getEditText().setText(getIntent().getStringExtra("DocSpecialist"));
        Describation.getEditText().setText(getIntent().getStringExtra("DocDes"));
        phone.getEditText().setText(getIntent().getStringExtra("DocPhone"));
        location.getEditText().setText(getIntent().getStringExtra("DocLocation"));
        city.getEditText().setText(getIntent().getStringExtra("DocCity"));
        state.getEditText().setText(getIntent().getStringExtra("DocState"));
        pincode.getEditText().setText(getIntent().getStringExtra("DocPincode"));
        fees.getEditText().setText(getIntent().getStringExtra("DocAptFees"));
        schedule.getEditText().setText(getIntent().getStringExtra("DocSchedule"));



        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Doctor");

        storageRef = FirebaseStorage.getInstance().getReference("DoctorProfile/"+docId+".jpg");
        try {
            File localFile = File.createTempFile("tempImg",".jpg");

            storageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            doctorProfile.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditDoctor.this, "Failed to Retrive Image", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        editDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fullname = fullname.getEditText().getText().toString();
                String Specialist = specialist.getEditText().getText().toString();
                String Des =Describation.getEditText().getText().toString();
                String Phone = phone.getEditText().getText().toString();
                String Location = location.getEditText().getText().toString();
                String City = city.getEditText().getText().toString();
                String State = state.getEditText().getText().toString();
                String Pincode = pincode.getEditText().getText().toString();
                String AptFees = fees.getEditText().getText().toString();
                String Schedule =schedule.getEditText().getText().toString();

                if(imageUri != null){

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("id",docId);
                hashMap.put("Fullname",Fullname);
                hashMap.put("Specialist",Specialist);
                hashMap.put("Describation",Des);
                hashMap.put("PhoneNumber",Phone);
                hashMap.put("Location",Location);
                hashMap.put("City",City);
                hashMap.put("State",State);
                hashMap.put("Pincode",Pincode);
                hashMap.put("Fees",AptFees);
                hashMap.put("Schedule",Schedule);
                hashMap.put("profileUrl",myUri);

                reference.child(docId).updateChildren(hashMap);
                Intent intent = new Intent(EditDoctor.this,DoctorList.class);
                startActivity(intent);
                finish();
                Toast.makeText(EditDoctor.this, "Doctor Updated", Toast.LENGTH_SHORT).show();
            } else {
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("id",docId);
                    hashMap.put("Fullname",Fullname);
                    hashMap.put("Specialist",Specialist);
                    hashMap.put("Describation",Des);
                    hashMap.put("PhoneNumber",Phone);
                    hashMap.put("Location",Location);
                    hashMap.put("City",City);
                    hashMap.put("State",State);
                    hashMap.put("Pincode",Pincode);
                    hashMap.put("Fees",AptFees);
                    hashMap.put("Schedule",Schedule);
                    hashMap.put("profileUrl",docImg);

                    reference.child(docId).updateChildren(hashMap);
                    Intent intent = new Intent(EditDoctor.this,DoctorList.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(EditDoctor.this, "Doctor Updated", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            imageUri = data.getData();
            doctorProfile.setImageURI(imageUri);
            UpdateProfileImg();

        }
    }

    private void UpdateProfileImg() {
        if(imageUri != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            String DoctorId = reference.push().getKey();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("DoctorProfile");
            StorageReference imageFileRef = imagesRef.child(docId+".jpg");

            UploadTask uploadTask = imageFileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception{
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return imageFileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri dawnloadUri =  task.getResult();
                        myUri = dawnloadUri.toString();

//                        HashMap<String,Object> hashMap = new HashMap<>();
//                        hashMap.put("profileUrl",myUri);
//                        reference = FirebaseDatabase.getInstance().getReference("Doctor");
//                        reference.updateChildren(hashMap);

                        Toast.makeText(EditDoctor.this, "Image Upload Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("profileUrl",docImg);
                        reference.updateChildren(hashMap);
                    }
                }
            });


        }

    }
}