package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hc.Admin.Doctor.DoctorListAdaptor;
import com.example.hc.Patient.PatientDoctorList;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.hc.appointment.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.hc.*;
import com.example.hc.Model.*;
public class Home extends AppCompatActivity {

    ImageSlider imageSlider;
    BottomNavigationView nav;

    ImageView surgeon,pediatrics,ophthalmologist,dermatology,orthopedics,obstetrical;

    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<Doctor> doctorListDataArrayList;
    TopDoctorAdaptor topDoctorAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        //image slider
        imageSlider = findViewById(R.id.imgSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.s1,null));
        slideModels.add(new SlideModel(R.drawable.s2,null));
        slideModels.add(new SlideModel(R.drawable.s3,null));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        //image click listener
        imageSlider.setItemClickListener(new  ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(Home.this, "Something Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Specialist
        surgeon = findViewById(R.id.surgeon);
        pediatrics =findViewById(R.id.pediatrics);
        ophthalmologist =findViewById(R.id.ophthalmologist);
        dermatology =findViewById(R.id.dermatology);
        orthopedics =findViewById(R.id.orthopedics);
        obstetrical =findViewById(R.id.obstetrical);

        //surgeon
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent intent = new Intent(Home.this,SpecialistList.class);
                        startActivity(intent);
                Toast.makeText(Home.this, "Surgen ", Toast.LENGTH_SHORT).show();
            }
        });

        pediatrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Home.this,SpecialistList.class);
//                startActivity(intent);
                Toast.makeText(Home.this, "pediatrics ", Toast.LENGTH_SHORT).show();
            }
        });

        ophthalmologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Home.this,SpecialistList.class);
//                startActivity(intent);
                Toast.makeText(Home.this, "ophthalmologist ", Toast.LENGTH_SHORT).show();
            }
        });

        dermatology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Home.this,SpecialistList.class);
//                startActivity(intent);
                Toast.makeText(Home.this, "dermatology ", Toast.LENGTH_SHORT).show();
            }
        });

        orthopedics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Home.this,SpecialistList.class);
//                startActivity(intent);
                Toast.makeText(Home.this, "orthopedics ", Toast.LENGTH_SHORT).show();
            }
        });
        obstetrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Home.this,SpecialistList.class);
//                startActivity(intent);
                Toast.makeText(Home.this, "obstetrical ", Toast.LENGTH_SHORT).show();
            }
        });


        //top doctors
        recyclerView = findViewById(R.id.rvTopDoctor);
        reference = FirebaseDatabase.getInstance().getReference("Doctor");
        doctorListDataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topDoctorAdaptor = new TopDoctorAdaptor(doctorListDataArrayList,this);
        recyclerView.setAdapter(topDoctorAdaptor);

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


    // BottomBar Buttons
        nav = findViewById(R.id.bottomNavbarss);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuHome:

                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Home.this, "Home Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.search:

                        Intent intent1 = new Intent(getApplicationContext(), PatientDoctorList.class);
                        startActivity(intent1);
                        //finish();

                        Toast.makeText(Home.this, "Search Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.list:

                        Intent intent2 = new Intent(getApplicationContext(), BookAppointment.class);
                        startActivity(intent2);

                        Toast.makeText(Home.this, "Appoitment Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.account:
                        Intent intent3 = new Intent(getApplicationContext(),ProfilePage.class);
                        startActivity(intent3);

                        Toast.makeText(Home.this, "Profile Page", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                }
                return true;
            }
        });
    }

    private void setData(ArrayList<Doctor> data) {
        topDoctorAdaptor = new TopDoctorAdaptor(data,getApplicationContext());
        recyclerView.setAdapter(topDoctorAdaptor);
    }
}