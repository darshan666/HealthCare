package com.example.hc.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.R;
import com.example.hc.appointment.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.hc.Model.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;



public class AppointmentList extends AppCompatActivity implements AppointmentAdapter.AppointmentListClickInterface{

    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<AppointmentListModel> AptListDataArrayList;
    AppointmentAdapter appointmentAdapter;
    AppointmentListModel appointmentListModel;
    FloatingActionButton floatingActionButtons;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rvAppointment);
        reference = FirebaseDatabase.getInstance().getReference("Appointments").child(auth.getUid());
        AptListDataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(AptListDataArrayList,this,this);
        recyclerView.setAdapter(appointmentAdapter);

//        floatingActionButtons = findViewById(R.id.floatingBookAppointment);
//
//        floatingActionButtons.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AppointmentList.this, BookAppointment.class);
//                startActivity(i);
//                finish();
//
//            }
//        });

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AptListDataArrayList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String aptId = postSnapshot.child("id").getValue(String.class);
                    String PatientName=postSnapshot.child("Fullname").getValue(String.class);
                    String Gender=postSnapshot.child("Gender").getValue(String.class);
                    String DOB=postSnapshot.child("Dob").getValue(String.class);
                    String Address=postSnapshot.child("Address").getValue(String.class);
                    String AptDate  =postSnapshot.child("AppointmentDate").getValue(String.class);
                    String AptTime  =postSnapshot.child("AppointmentTime").getValue(String.class);
                    String AptReason=postSnapshot.child("AppointmentReason").getValue(String.class);
                    String City = postSnapshot.child("City").getValue(String.class);
                    String State = postSnapshot.child("State").getValue(String.class);
                    String Pincode = postSnapshot.child("Pincode").getValue(String.class);
                    String PhoneNumber = postSnapshot.child("PhoneNumber").getValue(String.class);

                    AptListDataArrayList.add(new AppointmentListModel(PatientName,Gender,DOB,AptDate,AptTime,AptReason,Address,City,State,Pincode,PhoneNumber,aptId));

                }
                setData(AptListDataArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        reference.addListenerForSingleValueEvent(valueEventListener);


    }

    private void setData(ArrayList<AppointmentListModel> data) {
        appointmentAdapter = new AppointmentAdapter(data,getApplicationContext(),this);
        recyclerView.setAdapter(appointmentAdapter);
    }

    public void onAppointmentClick(int position) {
        //displayBottomSheet(doctorListDataArrayList.get(position));
    }

//


}