package com.example.hc.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hc.Admin.Payment.Rozarpay_Payment;
import com.example.hc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class EditAppointment extends AppCompatActivity {

    Button updateAppointment;
    TextInputEditText DOB,AppointmentDate,AppointmentTime;
    TextInputLayout fullname,gender,DOBDate,phone,address,city,state,pincode,AppointmentDes,AppointmentDateL,AppointmentTimeL;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean isAllFieldsChecked = false;
    String aptId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        fullname = findViewById(R.id.EdFullname);
        DOB = findViewById(R.id.EddataOfBirth);
        gender = findViewById(R.id.Edgender);
        phone = findViewById(R.id.EdphoneNo);
        address = findViewById(R.id.Edaddress);
        city = findViewById(R.id.Edcity);
        state = findViewById(R.id.Edstate);
        pincode = findViewById(R.id.Edpincode);
        AppointmentDes = findViewById(R.id.Edreason);
        AppointmentDate = findViewById(R.id.EdAppointmentDate);
        AppointmentTime = findViewById(R.id.EdAppointmentTime);
        updateAppointment = findViewById(R.id.EdEditAppointment);
        DOBDate = findViewById(R.id.EdDobLayout);
        AppointmentDateL = findViewById(R.id.EdAppointmentDateL);
        AppointmentTimeL = findViewById(R.id.EdAppointmentTimeL);


        aptId = getIntent().getStringExtra("aptId");
        fullname.getEditText().setText(getIntent().getStringExtra("pname"));
        DOB.setText(getIntent().getStringExtra("dob"));
        gender.getEditText().setText(getIntent().getStringExtra("gender"));
        phone.getEditText().setText(getIntent().getStringExtra("phone"));
        address.getEditText().setText(getIntent().getStringExtra("address"));
        city.getEditText().setText(getIntent().getStringExtra("city"));
        state.getEditText().setText(getIntent().getStringExtra("state"));
        pincode.getEditText().setText(getIntent().getStringExtra("pincode"));
        AppointmentDes.getEditText().setText(getIntent().getStringExtra("reason"));
        AppointmentDate.setText(getIntent().getStringExtra("aptDate"));
        AppointmentTime.setText(getIntent().getStringExtra("aptTime"));



        DOBDate.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(EditAppointment.this,
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(EditAppointment.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog( EditAppointment.this,
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
        updateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

               // String AppointmentId = reference.push().getKey();
                String userid = firebaseUser.getUid();

                // AppointmentData appointmentData = new AppointmentData(Fullname,Dob,Gender,Phone,Address,City,State,Pincode,AppointmentReason,AppointmentDateTimes);
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("id",aptId);
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

                reference.child(userid).child(aptId).updateChildren(hashMap);
                Toast.makeText(EditAppointment.this, "Appointment Updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditAppointment.this,AppointmentList.class);
                startActivity(intent);
                finish();

            }
        });


    }
}