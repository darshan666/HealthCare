package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.example.hc.Admin.Doctor.DoctorListAdaptor;
import com.example.hc.Model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SpecialistList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpecialistAdapter specialistAdapter;
    DatabaseReference reference;
    ArrayList<Doctor> doctorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_list);


        recyclerView = findViewById(R.id.specialList);
        doctorList = new ArrayList<>();
        specialistAdapter = new SpecialistAdapter(doctorList);
        recyclerView.setAdapter(specialistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference("Doctor");

//        Query query = reference.orderByChild("Specialist").equalTo("Surgeon");
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                doctorList.clear();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    Doctor doctor = snapshot1.getValue(Doctor.class);
//                    String Sp = doctor.getSpecialist().toString();
//                    if(Sp.equals("Surgeon")){
//                        doctorList.add(doctor);
//                    }
////                    doctorList.add(doctor);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctorList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String docId = postSnapshot.child("Id").getValue(String.class);
                    String name=postSnapshot.child("Fullname").getValue(String.class);
                    String city=postSnapshot.child("City").getValue(String.class);
                    String desc=postSnapshot.child("Describation").getValue(String.class);

                    String fees=postSnapshot.child("Fees").getValue(String.class);
                    String fullName=postSnapshot.child("Fullname").getValue(String.class);
                    String PhoneNumber=postSnapshot.child("PhoneNumber").getValue(String.class);

                    String Pincode=postSnapshot.child("Pincode").getValue(String.class);
                    String Schedule=postSnapshot.child("Schedule").getValue(String.class);
                    String Specialist=postSnapshot.child("Specialist").getValue(String.class);
                    String State=postSnapshot.child("State").getValue(String.class);
                    String id=postSnapshot.child("id").getValue(String.class);
                    String profileUrl=postSnapshot.child("profileUrl").getValue(String.class);
                    if(Specialist == "Surgeon"){
                        doctorList.add(new Doctor(docId,name,desc,Specialist,fees,profileUrl));
                    }


                }
                setData(doctorList);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        reference.addListenerForSingleValueEvent(valueEventListener);

    }

    private void setData(List<Doctor> doctorList) {
        specialistAdapter = new SpecialistAdapter(doctorList);
        recyclerView.setAdapter(specialistAdapter);
    }

}