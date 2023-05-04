package com.example.hc.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hc.Admin.AdminProfile.*;
import com.example.hc.appointment.*;

import com.example.hc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import com.example.hc.Doctor.Appointment.*;
public class DoctorDashboard extends AppCompatActivity {
    ImageSlider imageSlider;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        imageSlider = findViewById(R.id.DimgSlider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.s1,null));
        slideModels.add(new SlideModel(R.drawable.s2,null));
        slideModels.add(new SlideModel(R.drawable.s3,null));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        //image click listener
        imageSlider.setItemClickListener(new  ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(DoctorDashboard.this, "Something Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        // BottomBar Buttons
        nav = findViewById(R.id.DbottomNavbarss);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.DmenuHome:

                        Intent intent = new Intent(getApplicationContext(),DoctorDashboard.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(DoctorDashboard.this, "Home Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Dchat:

                        Intent intent1 = new Intent(getApplicationContext(), DoctorDashboard.class);
                        startActivity(intent1);
                        //finish();

                        Toast.makeText(DoctorDashboard.this, "Chat Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Dlist:

                        Intent intent2 = new Intent(DoctorDashboard.this, PatientAppointmentList.class);
                        startActivity(intent2);

                        Toast.makeText(DoctorDashboard.this, "Appoitment Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Daccount:
                        Intent intent3 = new Intent(getApplicationContext(), AdminProfile.class);
                        startActivity(intent3);

                        Toast.makeText(DoctorDashboard.this, "Profile Page", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                }
                return true;
            }
        });
    }
}