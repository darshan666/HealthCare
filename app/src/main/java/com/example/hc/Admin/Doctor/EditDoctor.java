package com.example.hc.Admin.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditDoctor extends AppCompatActivity {

    CircleImageView doctorProfile;
    Button editDoctor;
    TextInputLayout fullname,specialist,Describation,phone,location,city,state,pincode,fees,schedule;
    private DatabaseReference reference;
    private Uri imageUri;
    private String myUri="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor2);

        doctorProfile = findViewById(R.id.EdDoctorProfile);
        fullname = findViewById(R.id.EdFullname);
        specialist = findViewById(R.id.Edschedule);
        Describation = findViewById(R.id.EdDescription);
        phone = findViewById(R.id.EdPhono);
        location = findViewById(R.id.EdAddress);
        city = findViewById(R.id.EdCity);
        state = findViewById(R.id.EdState);
        pincode = findViewById(R.id.EdPincode);
        fees = findViewById(R.id.Edfees);
        schedule = findViewById(R.id.Edschedule);
        editDoctor = findViewById(R.id.editDoctor);

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


        String DoctorId = reference.push().getKey();
        String uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Doctor");
        reference.child(uid)
                .addValueEventListener(new ValueEventListener() {
                    String Fullname,Specialist,Description,Phone,Location,City,State,Pincode,Fees,Schedule;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot KeyId:snapshot.getChildren()){

                            Doctor snapshot1 = snapshot.getValue(Doctor.class);

                            Fullname = snapshot1.getFullname().toString();
                            Specialist = snapshot1.getSpecialist().toString();
                            Description = snapshot1.getDescribation().toString();
                            Phone = snapshot1.getPhone().toString();
                            Location = snapshot1.getLocation().toString();
                            City = snapshot1.getCity().toString();
                            State = snapshot1.getState().toString();
                            Pincode = snapshot1.getPincode().toString();
                            Fees = snapshot1.getFees().toString();
                            Schedule = snapshot1.getSchedule().toString();
                        }
                        fullname.getEditText().setText(Fullname);
                        specialist.getEditText().setText(Specialist);
                        Describation.getEditText().setText(Description);
                        phone.getEditText().setText(Phone);
                        location.getEditText().setText(Location);
                        city.getEditText().setText(City);
                        state.getEditText().setText(State);
                        pincode.getEditText().setText(Pincode);
                        fees.getEditText().setText(Fees);
                        schedule.getEditText().setText(Schedule);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditDoctor.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void EditDoctorOnClickButton(View view) {
    }
}