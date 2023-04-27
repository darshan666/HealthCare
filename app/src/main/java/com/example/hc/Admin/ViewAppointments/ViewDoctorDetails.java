package com.example.hc.Admin.ViewAppointments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hc.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.hc.appointment.*;

public class ViewDoctorDetails extends AppCompatActivity {

    TextView name,specialist,description,fees,schedule,location,city,state;
    CircleImageView DImg;
    String docId,DoctorName,AptCharge ;
    Button BookApt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_details);

        name = findViewById(R.id.VDDName);
        specialist = findViewById(R.id.VDDSpecialist);
        description = findViewById(R.id.VDDDescription);
        fees = findViewById(R.id.VDDFees);
        schedule = findViewById(R.id.VDDSchedule);
        location = findViewById(R.id.VDDLocation);
        city = findViewById(R.id.VDDCity);
        state = findViewById(R.id.VDDState);
        DImg = findViewById(R.id.VDDImg);
        BookApt = findViewById(R.id.VDDBookApt);





        Picasso.get().load(getIntent().getStringExtra("DoctorImg")).placeholder(R.drawable.s2).into(DImg);
        docId = getIntent().getStringExtra("docId");
        DoctorName = getIntent().getStringExtra("Fullname");
        AptCharge = getIntent().getStringExtra("Fees");
        name.setText(getIntent().getStringExtra("Fullname"));
        specialist.setText(getIntent().getStringExtra("Specialist"));
        description.setText(getIntent().getStringExtra("Description"));
        fees.setText(getIntent().getStringExtra("Fees"));
        schedule.setText(getIntent().getStringExtra("Schedule"));
        location.setText(getIntent().getStringExtra("Location"));
        city.setText(getIntent().getStringExtra("City"));
        state.setText(getIntent().getStringExtra("State"));

        BookApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewDoctorDetails.this,BookAppointment.class);
                intent.putExtra("docId",docId);
                intent.putExtra("Doctor Name",DoctorName);
                intent.putExtra("AptCharge",AptCharge);
                startActivity(intent);

            }
        });

    }
}