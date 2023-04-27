package com.example.hc.Admin.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hc.R;
import com.squareup.picasso.Picasso;

public class DoctorDetails extends AppCompatActivity {

    TextView name,specialist,description,fees,schedule,location,city,state;
    ImageView DImg;
    String docId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        name = findViewById(R.id.DDName);
        specialist = findViewById(R.id.DDSpecialist);
        description = findViewById(R.id.DDDescription);
        fees = findViewById(R.id.DDFees);
        schedule = findViewById(R.id.DDSchedule);
        location = findViewById(R.id.DDLocation);
        city = findViewById(R.id.DDCity);
        state = findViewById(R.id.DDState);
        DImg = findViewById(R.id.DDImg);


        Picasso.get().load(getIntent().getStringExtra("DoctorImg")).placeholder(R.drawable.s2).into(DImg);
        docId = getIntent().getStringExtra("docId");
        name.setText(getIntent().getStringExtra("Fullname"));
        specialist.setText(getIntent().getStringExtra("Specialist"));
        description.setText(getIntent().getStringExtra("Description"));
        fees.setText(getIntent().getStringExtra("Fees"));
        schedule.setText(getIntent().getStringExtra("Schedule"));
        location.setText(getIntent().getStringExtra("Location"));
        city.setText(getIntent().getStringExtra("City"));
        state.setText(getIntent().getStringExtra("State"));




    }
}