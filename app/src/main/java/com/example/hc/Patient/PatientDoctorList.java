package com.example.hc.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import com.example.hc.R;

import java.util.ArrayList;
import java.util.List;
import com.example.hc.Model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientDoctorList extends AppCompatActivity implements PatientDoctorlistAdaptor.PatientDoctorListClickInterface {
    RecyclerView recyclerView;
    SearchView searchView;
    DatabaseReference reference;
    ArrayList<Doctor> doctorListDataArrayList;
   PatientDoctorlistAdaptor patientDoctorlistAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor_list);

        recyclerView = findViewById(R.id.rvPDoctor);
        searchView = findViewById(R.id.PDSearch);
        searchView.clearFocus();
        reference = FirebaseDatabase.getInstance().getReference("Doctor");
        doctorListDataArrayList = new ArrayList<>();
        patientDoctorlistAdaptor = new PatientDoctorlistAdaptor(doctorListDataArrayList,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientDoctorlistAdaptor);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctorListDataArrayList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String docId=postSnapshot.child("id").getValue(String.class);
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
        patientDoctorlistAdaptor = new PatientDoctorlistAdaptor(data,getApplicationContext(),this);
        recyclerView.setAdapter(patientDoctorlistAdaptor);
    }


    private void filterlist(String text) {
        ArrayList<Doctor> filteredList = new ArrayList<>();
        for (Doctor doctor:doctorListDataArrayList){
            if(doctor.getFullname().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(doctor);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            patientDoctorlistAdaptor.setFilteredList(filteredList);
        }
    }

    @Override
    public void onDoctorClick(int position) {

    }
}