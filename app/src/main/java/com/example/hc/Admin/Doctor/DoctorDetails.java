package com.example.hc.Admin.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hc.R;
import com.example.hc.appointment.AppointmentList;
import com.example.hc.appointment.ViewAppointmentDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetails extends AppCompatActivity {

    TextView name,specialist,description,fees,schedule,location,city,state;
    CircleImageView DImg;
    Button DoctorEdit,DoctorRemove;
    String docId ;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

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
        DoctorRemove = findViewById(R.id.DDDeleteDoctor);
        DoctorEdit = findViewById(R.id.DDEditDoctor);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Doctor");


        String docImg = getIntent().getStringExtra("DoctorImg");
        String Phone = getIntent().getStringExtra("PhoneNumber");
        String Pincode = getIntent().getStringExtra("Pincode");
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

        DoctorRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(docId).removeValue();
                Intent intent = new Intent(DoctorDetails.this, DoctorList.class);
                startActivity(intent);
                finish();
                Toast.makeText(DoctorDetails.this, "Doctor Removed", Toast.LENGTH_SHORT).show();

            }
        });
        DoctorEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetails.this, EditDoctor.class);
                intent.putExtra("docId",docId);
                intent.putExtra("docImg",docImg);
                intent.putExtra("DocName",name.getText());
                intent.putExtra("DocSpecialist",specialist.getText());
                intent.putExtra("DocDes",description.getText());
                intent.putExtra("DocAptFees",fees.getText());
                intent.putExtra("DocSchedule",schedule.getText());
                intent.putExtra("DocLocation",location.getText());
                intent.putExtra("DocCity",city.getText());
                intent.putExtra("DocState",state.getText());
                intent.putExtra("DocPhone",Phone);
                intent.putExtra("DocPincode",Pincode);
                startActivity(intent);
                finish();
            }
        });




    }
}