package com.example.hc.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hc.Model.AppointmentData;
import com.example.hc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class BookAppointment extends AppCompatActivity {
    Button submitAppointment;
    TextInputLayout fullname,DOB,gender,phone,address,city,state,pincode,AppointmentDes,AppointmentDateTime;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        fullname = findViewById(R.id.pFullname);
        DOB = findViewById(R.id.dataOfBirth);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.phoneNo);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);
        AppointmentDes = findViewById(R.id.reason);
        AppointmentDateTime = findViewById(R.id.AppointmentDateTime);
        submitAppointment = findViewById(R.id.submitAppointment);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Appointments");


    }

    public void submitOnClickButton(View view) {
        String Fullname = fullname.getEditText().getText().toString();
        String Dob = DOB.getEditText().getText().toString();
        String Gender =gender.getEditText().getText().toString();
        String Phone = phone.getEditText().getText().toString();
        String Address = address.getEditText().getText().toString();
        String City = city.getEditText().getText().toString();
        String State = state.getEditText().getText().toString();
        String Pincode = pincode.getEditText().getText().toString();
        String AppointmentReason = AppointmentDes.getEditText().getText().toString();
        String AppointmentDateTimes =AppointmentDateTime.getEditText().getText().toString();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String AppointmentId = reference.push().getKey();
        //String userid = firebaseUser.getUid();

       // AppointmentData appointmentData = new AppointmentData(Fullname,Dob,Gender,Phone,Address,City,State,Pincode,AppointmentReason,AppointmentDateTimes);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",AppointmentId);
        hashMap.put("Fullname",Fullname);
        hashMap.put("Dob",Dob);
        hashMap.put("Gender",Gender);
        hashMap.put("PhoneNumber",Phone);
        hashMap.put("Address",Address);
        hashMap.put("City",City);
        hashMap.put("State",State);
        hashMap.put("Pincode",Pincode);
        hashMap.put("AppointmentReason",AppointmentReason);
        hashMap.put("AppointmentDateTimes",AppointmentDateTimes);


        reference.child(AppointmentId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(BookAppointment.this, "Appointment Submited", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BookAppointment.this, "Appointment Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}