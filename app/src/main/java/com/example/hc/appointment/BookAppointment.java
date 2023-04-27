package com.example.hc.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hc.Model.AppointmentData;
import com.example.hc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.hc.Admin.Payment.*;

public class BookAppointment extends AppCompatActivity {
    Button submitAppointment;
    TextInputEditText DOB,AppointmentDate,AppointmentTime;
    TextInputLayout fullname,gender,DOBDate,phone,address,city,state,pincode,AppointmentDes,AppointmentDateL,AppointmentTimeL;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean isAllFieldsChecked = false;
    String DocId,DoctorName,AptCharge;
//    String AppointmentId = reference.push().getKey();
//    String userid = FirebaseAuth.getInstance().getUid();

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
        AppointmentDate = findViewById(R.id.AppointmentDate);
        AppointmentTime = findViewById(R.id.AppointmentTime);
        submitAppointment = findViewById(R.id.submitAppointment);
        DOBDate = findViewById(R.id.DobLayout);
        AppointmentDateL = findViewById(R.id.AppointmentDateL);
        AppointmentTimeL = findViewById(R.id.AppointmentTimeL);

        DocId = getIntent().getStringExtra("docId");
        DoctorName = getIntent().getStringExtra("Doctor Name");
        AptCharge = getIntent().getStringExtra("AptCharge");

        DOBDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                DOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        AppointmentDateL.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                AppointmentDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        AppointmentTimeL.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog( BookAppointment.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                AppointmentTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Appointments");

        submitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked){
                    String Fullname = fullname.getEditText().getText().toString();
                    String Dob = DOB.getText().toString();
                    String Gender =gender.getEditText().getText().toString();
                    String Phone = phone.getEditText().getText().toString();
                    String Address = address.getEditText().getText().toString();
                    String City = city.getEditText().getText().toString();
                    String State = state.getEditText().getText().toString();
                    String Pincode = pincode.getEditText().getText().toString();
                    String AppointmentReason = AppointmentDes.getEditText().getText().toString();
                    String AppointmentDates =AppointmentDate.getText().toString();
                    String AppointmentTimes =AppointmentTime.getText().toString();

                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    String AppointmentId = reference.push().getKey();
                    String userid = firebaseUser.getUid();

                    // AppointmentData appointmentData = new AppointmentData(Fullname,Dob,Gender,Phone,Address,City,State,Pincode,AppointmentReason,AppointmentDateTimes);
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("id",AppointmentId);
                    hashMap.put("docId",DocId);
                    hashMap.put("DoctorName",DoctorName);
                    hashMap.put("Fullname",Fullname);
                    hashMap.put("Dob",Dob);
                    hashMap.put("Gender",Gender);
                    hashMap.put("PhoneNumber",Phone);
                    hashMap.put("Address",Address);
                    hashMap.put("City",City);
                    hashMap.put("State",State);
                    hashMap.put("Pincode",Pincode);
                    hashMap.put("AppointmentReason",AppointmentReason);
                    hashMap.put("AppointmentDate",AppointmentDates);
                    hashMap.put("AppointmentTime",AppointmentTimes);


                    reference.child(userid).child(String.valueOf(AppointmentId)).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BookAppointment.this, "Appointment Submited", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BookAppointment.this,Rozarpay_Payment.class);
                                intent.putExtra("AptId",AppointmentId);
                                intent.putExtra("Payment",AptCharge);
                                intent.putExtra("patientId",userid);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(BookAppointment.this, "Appointment Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//                    Intent intent = new Intent(BookAppointment.this,Rozarpay_Payment.class);
//                    intent.putExtra("AptId",AppointmentId);
//                    intent.putExtra("Payment",AptCharge);
//                    intent.putExtra("patientId",userid);
//                    startActivity(intent);
                    //submitOnClickButton(v);
                }

            }
        });

    }

    private boolean CheckAllFields() {
        if(fullname.getEditText().getText().toString().length() == 0){
            fullname.setError("This field is required");
            return false;
        }
        if(DOB.getText().toString().length() == 0){
            DOB.setError("This field is required");
            return false;
        }
        if(gender.getEditText().getText().toString().length() == 0){
            gender.setError("This field is required");
            return false;
        }
        if(phone.getEditText().getText().toString().length() == 0){
            phone.setError("This field is required");
            return false;
        }
        if(address.getEditText().getText().toString().length() == 0){
            address.setError("This field is required");
            return false;
        }
        if(city.getEditText().getText().toString().length() == 0){
            city.setError("This field is required");
            return false;
        }
        if(state.getEditText().getText().toString().length() == 0){
            state.setError("This field is required");
            return false;
        }if(pincode.getEditText().getText().toString().length() == 0){
            pincode.setError("This field is required");
            return false;
        }
        if(AppointmentDes.getEditText().getText().toString().length() == 0){
            AppointmentDes.setError("This field is required");
            return false;
        }
        if(AppointmentDate.getText().toString().length() == 0){
            AppointmentDate.setError("This field is required");
            return false;
        }
        if(AppointmentTime.getText().toString().length() == 0){
            AppointmentTime.setError("This field is required");
            return false;
        }

        return   true;
    }

    //public void submitOnClickButton(View view) {
//        String Fullname = fullname.getEditText().getText().toString();
//        String Dob = DOB.getText().toString();
//        String Gender =gender.getEditText().getText().toString();
//        String Phone = phone.getEditText().getText().toString();
//        String Address = address.getEditText().getText().toString();
//        String City = city.getEditText().getText().toString();
//        String State = state.getEditText().getText().toString();
//        String Pincode = pincode.getEditText().getText().toString();
//        String AppointmentReason = AppointmentDes.getEditText().getText().toString();
//        String AppointmentDates =AppointmentDate.getText().toString();
//        String AppointmentTimes =AppointmentTime.getText().toString();
//
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//        String AppointmentId = reference.push().getKey();
//        String userid = firebaseUser.getUid();
//
//       // AppointmentData appointmentData = new AppointmentData(Fullname,Dob,Gender,Phone,Address,City,State,Pincode,AppointmentReason,AppointmentDateTimes);
//        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("id",AppointmentId);
//        hashMap.put("docId",DocId);
//        hashMap.put("DoctorName",DoctorName);
//        hashMap.put("Fullname",Fullname);
//        hashMap.put("Dob",Dob);
//        hashMap.put("Gender",Gender);
//        hashMap.put("PhoneNumber",Phone);
//        hashMap.put("Address",Address);
//        hashMap.put("City",City);
//        hashMap.put("State",State);
//        hashMap.put("Pincode",Pincode);
//        hashMap.put("AppointmentReason",AppointmentReason);
//        hashMap.put("AppointmentDate",AppointmentDates);
//        hashMap.put("AppointmentTime",AppointmentTimes);
//
//
//        reference.child(userid).child(String.valueOf(AppointmentId)).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(BookAppointment.this, "Appointment Submited", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(BookAppointment.this, "Appointment Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
 //   }


}