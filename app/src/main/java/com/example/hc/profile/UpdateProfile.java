package com.example.hc.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hc.R;
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

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.hc.Model.*;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UpdateProfile extends AppCompatActivity {

    TextInputLayout fullname,email,phone;
    CircleImageView profileImg;

    private DatabaseReference reference;
    private Uri imageUri;
    private String myUri="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        fullname = findViewById(R.id.edFullname);
        email = findViewById(R.id.edEmail);
        phone = findViewById(R.id.edPhone);
        profileImg = findViewById(R.id.edProfile);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        storageRef = FirebaseStorage.getInstance().getReference("Images/"+user.getUid()+".jpg");

        try {
            File localFile = File.createTempFile("tempImg",".jpg");

            storageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            profileImg.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateProfile.this, "Failed to Retrive Image", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


        String uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(uid)
        .addValueEventListener(new ValueEventListener() {
            String fname,mail,phones;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot KeyId:snapshot.getChildren()){

                    Users snapshot1 = snapshot.getValue(Users.class);

                    fname = snapshot1.getFullname().toString();
                    mail = snapshot1.getEmail().toString();
                    phones = snapshot1.getPhoneNumber().toString();
                }
                fullname.getEditText().setText(fname);
                email.getEditText().setText(mail);
                phone.getEditText().setText(phones);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateProfile.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });



        profileImg.setOnClickListener(new View.OnClickListener() {
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
        if(data.getData() != null) {
            imageUri = data.getData();
            profileImg.setImageURI(imageUri);

        }

    }

    private void UpdateProfileImg(){
        if(imageUri != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("Images");
            StorageReference imageFileRef = imagesRef.child(user.getUid()+".jpg");

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

                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("imageUrl",myUri);
                        reference = FirebaseDatabase.getInstance().getReference("User").child(user.getUid());
                        reference.updateChildren(hashMap);

                        Toast.makeText(UpdateProfile.this, "Image Upload Successfull", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }

    public void updateOnClickButton(View view) {
        UpdateProfileImg();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String  fname_ = fullname.getEditText().getText().toString();
        String email_ = email.getEditText().getText().toString();
        String phone_ = phone.getEditText().getText().toString();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("fullname",fname_);
        hashMap.put("email",email_);
        hashMap.put("phoneNumber",phone_);
        reference = FirebaseDatabase.getInstance().getReference("User").child(user.getUid());
        reference.updateChildren(hashMap);
        Toast.makeText(this, "Update Successfull", Toast.LENGTH_SHORT).show();


    }
}