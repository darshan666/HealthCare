package com.example.hc.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAppointmentDetails extends AppCompatActivity {

    TextView name,gender,phone,address,dob,city,state,pincode,aptDate,aptTime,aptReason;
    Button cancelApt,editApt;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_details);

        name = findViewById(R.id.PName);
        gender = findViewById(R.id.PGender);
        phone = findViewById(R.id.PPhone);
        address = findViewById(R.id.PAddress);
        dob = findViewById(R.id.PDob);
        city = findViewById(R.id.PCity);
        state = findViewById(R.id.PState);
        pincode = findViewById(R.id.PPincode);
        aptDate = findViewById(R.id.PAptDate);
        aptTime = findViewById(R.id.PAptTime);
        aptReason = findViewById(R.id.PAptReason);

        cancelApt = findViewById(R.id.ADCancelApt);
        editApt = findViewById(R.id.ADEditApt);
        String Id = getIntent().getStringExtra("Id");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Appointments");
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userid = firebaseUser.getUid();

        cancelApt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reference.child(userid).child(Id).removeValue();
                Intent intent = new Intent(ViewAppointmentDetails.this,AppointmentList.class);
                startActivity(intent);
                finish();
                Toast.makeText(ViewAppointmentDetails.this, "Appointment cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        editApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAppointmentDetails.this,EditAppointment.class);
                intent.putExtra("aptId",Id);
                intent.putExtra("pname",name.getText());
                intent.putExtra("gender",gender.getText());
                intent.putExtra("phone",phone.getText());
                intent.putExtra("address",address.getText());
                intent.putExtra("dob",dob.getText());
                intent.putExtra("city",city.getText());
                intent.putExtra("state",state.getText());
                intent.putExtra("pincode",pincode.getText());
                intent.putExtra("aptDate",aptDate.getText());
                intent.putExtra("aptTime",aptTime.getText());
                intent.putExtra("reason",aptReason.getText());
                startActivity(intent);
                finish();
                Toast.makeText(ViewAppointmentDetails.this, "Appointment Edit", Toast.LENGTH_SHORT).show();
            }
        });




        name.setText(getIntent().getStringExtra("PatientName"));
        gender.setText(getIntent().getStringExtra("Gender"));
        phone.setText(getIntent().getStringExtra("PhoneNumber"));
        address.setText(getIntent().getStringExtra("Address"));
        dob.setText(getIntent().getStringExtra("DOB"));
        city.setText(getIntent().getStringExtra("City"));
        state.setText(getIntent().getStringExtra("State"));
        pincode.setText(getIntent().getStringExtra("Pincode"));
        aptDate.setText(getIntent().getStringExtra("AptDate"));
        aptTime.setText(getIntent().getStringExtra("AptTime"));
        aptReason.setText(getIntent().getStringExtra("AptReason"));



    }
}