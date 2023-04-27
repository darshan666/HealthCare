package com.example.hc.Admin.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Admin.Doctor.AddDoctor;
import com.example.hc.Model.Doctor;
import com.example.hc.R;
import com.example.hc.appointment.BookAppointment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public  class DoctorList extends AppCompatActivity  {

    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<Doctor> doctorListDataArrayList;
    RelativeLayout BottomSheet;
    DoctorListAdaptor doctorListAdapter;
    FloatingActionButton floatingActionButton;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recyclerView = findViewById(R.id.rvDoctor);
        reference = FirebaseDatabase.getInstance().getReference("Doctor");
        doctorListDataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorListAdapter = new DoctorListAdaptor(doctorListDataArrayList,this);
        recyclerView.setAdapter(doctorListAdapter);
        //BottomSheet = findViewById(R.id.idRLDocBSheet);
        floatingActionButton = findViewById(R.id.floatingAddDoctor);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorList.this, AddDoctor.class);
                startActivity(i);
            }
        });


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctorListDataArrayList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String docId=postSnapshot.child("Id").getValue(String.class);
                    String name=postSnapshot.child("Fullname").getValue(String.class);
                    String city=postSnapshot.child("City").getValue(String.class);
                    String desc=postSnapshot.child("Describation").getValue(String.class);
                    String fees=postSnapshot.child("Fees").getValue(String.class);
                    String location = postSnapshot.child("Location").getValue(String.class);
                    String PhoneNumber=postSnapshot.child("PhoneNumber").getValue(String.class);
                    String Pincode=postSnapshot.child("Pincode").getValue(String.class);
                    String Schedule=postSnapshot.child("Schedule").getValue(String.class);
                    String Specialist=postSnapshot.child("Specialist").getValue(String.class);
                    String State=postSnapshot.child("State").getValue(String.class);
                    String profileUrl=postSnapshot.child("profileUrl").getValue(String.class);
                    doctorListDataArrayList.add(new Doctor(docId,name,Specialist,desc,PhoneNumber,location,city, State, Pincode, fees, Schedule, profileUrl));

                }
                setData(doctorListDataArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };


        reference.addListenerForSingleValueEvent(valueEventListener);

    }
    private void setData(ArrayList<Doctor> data){
        doctorListAdapter = new DoctorListAdaptor(data,getApplicationContext());
        recyclerView.setAdapter(doctorListAdapter);
    }
}
