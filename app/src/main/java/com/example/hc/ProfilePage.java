package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hc.profile.*;
import com.example.hc.appointment.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends AppCompatActivity {
    Button logout;
    TextView txtEmail,appointmentlist,profileUpdate;
    CircleImageView profileImg;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            txtEmail.setText(currentUser.getEmail());
        }
        else {
            startActivity(new Intent(this,Login.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        logout = findViewById(R.id.logout);
        txtEmail = findViewById(R.id.emailtext);
        appointmentlist = findViewById(R.id.appointmentList);
        profileUpdate = findViewById(R.id.ProfileUpdate);
        firebaseAuth = FirebaseAuth.getInstance();
        profileImg = findViewById(R.id.imgProfile);
        storageReference = FirebaseStorage.getInstance().getReference("Images/"+firebaseAuth.getCurrentUser().getUid()+".jpg");



        try {
            File localFile = File.createTempFile("DoctorProfile",".jpg");

            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            profileImg.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfilePage.this, "Failed to Retrive Image", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateProfile.class);
                startActivity(i);
            }
        });

        appointmentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AppointmentList.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                preferences.clearData(ProfilePage.this);
                finish();
                Toast.makeText(ProfilePage.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}