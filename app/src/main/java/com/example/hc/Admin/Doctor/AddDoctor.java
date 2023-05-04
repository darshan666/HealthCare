package com.example.hc.Admin.Doctor;

import static java.util.Calendar.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.CustomFloatAttributes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Model.Doctor;
import com.example.hc.Model.Users;
import com.example.hc.R;
import com.example.hc.appointment.BookAppointment;
import com.example.hc.profile.UpdateProfile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddDoctor extends AppCompatActivity {

    CircleImageView doctorProfile;
    Button addDoctor;
    TextInputLayout fullname,specialist ,email,Describation,phone,location,city,state,pincode,fees,schedule;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    Uri profileUri;
    private String myUri="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        doctorProfile = findViewById(R.id.doctorProfile);
        fullname = findViewById(R.id.DFullname);
        specialist = findViewById(R.id.specialist);
        email = findViewById(R.id.DEmail);
        Describation = findViewById(R.id.Describation);
        phone = findViewById(R.id.Phono);
        location = findViewById(R.id.Address);
        city = findViewById(R.id.City);
        state = findViewById(R.id.State);
        pincode = findViewById(R.id.Pincode);
        fees = findViewById(R.id.fees);
        schedule = findViewById(R.id.schedule);
        addDoctor = findViewById(R.id.addDoctor);





        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("User");
        storage = FirebaseStorage.getInstance();

        doctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            profileUri = data.getData();
            doctorProfile.setImageURI(profileUri);
            UpdateProfileImg();

        }
    }


    public void AddDoctorOnClickButton(View view) {
        String ProfileUrl = doctorProfile.toString();
        String Fullname = fullname.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();
        String Specialist = specialist.getEditText().getText().toString();
        String Describations =Describation.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();
        String Location = location.getEditText().getText().toString();
        String City = city.getEditText().getText().toString();
        String State = state.getEditText().getText().toString();
        String Pincode = pincode.getEditText().getText().toString();
        String Fees = fees.getEditText().getText().toString();
        String Schedule =schedule.getEditText().getText().toString();



        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(Email,"Doctor123").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String userid = task.getResult().getUser().getUid();
                firebaseDatabase = FirebaseDatabase.getInstance();
               DatabaseReference reference1 = firebaseDatabase.getReference("Doctor");
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("id",userid);
                hashMap.put("Fullname",Fullname);
                hashMap.put("Specialist",Specialist);
                hashMap.put("Describation",Describations);
                hashMap.put("PhoneNumber",Phone);
                hashMap.put("Location",Location);
                hashMap.put("City",City);
                hashMap.put("State",State);
                hashMap.put("Pincode",Pincode);
                hashMap.put("Fees",Fees);
                hashMap.put("Schedule",Schedule);
                hashMap.put("profileUrl",myUri);
                hashMap.put("userType",2);

                reference1.child(userid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Doctor Add SuccessFully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                Users users = new Users(userid, Fullname, Phone, Email,"Doctor123", myUri,2);

                reference.child(userid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Doctor Add SuccessFully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddDoctor.this,DoctorList.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });



//        reference.child(DoctorId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Doctor Add SuccessFully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(AddDoctor.this,DoctorList.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else {
//                    Toast.makeText(AddDoctor.this, "Add Doctor Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }

    private void UpdateProfileImg(){
        if(profileUri != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String DoctorId = reference.push().getKey();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("Images");
            StorageReference imageFileRef = imagesRef.child(DoctorId+".jpg");

            UploadTask uploadTask = imageFileRef.putFile(profileUri);

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
                        Toast.makeText(AddDoctor.this, "Image Upload Successfull", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}