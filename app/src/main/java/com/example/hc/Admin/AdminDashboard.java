package com.example.hc.Admin;
import com.example.hc.appointment.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hc.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hc.Admin.Doctor.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminDashboard extends AppCompatActivity {
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        nav = findViewById(R.id.bottomNavbarss);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuHome:

                        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(AdminDashboard.this, "Home Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.search:

                        Intent intent1 = new Intent(getApplicationContext(), DoctorList.class);
                        startActivity(intent1);
                        //finish();

                        Toast.makeText(AdminDashboard.this, "Search Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.list:

                        Intent intent2 = new Intent(getApplicationContext(), AppointmentList.class);
                        startActivity(intent2);

                        Toast.makeText(AdminDashboard.this, "Appoitment Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.account:
                        Intent intent3 = new Intent(getApplicationContext(), AdminDashboard.class);
                        startActivity(intent3);

                        Toast.makeText(AdminDashboard.this, "Profile Page", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                }
                return true;
            }
        });
    }



    public void AddDoctorOnClick(View view) {
        Toast.makeText(this, "Doctor page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminDashboard.this, DoctorList.class);
        startActivity(intent);
        //finish();
    }

    public void AppointmentOnClick(View view) {
        Toast.makeText(this, "Appointment page", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(AdminDashboard.this, AddDoctor.class);
        startActivity(intent1);
        //finish();
    }

    public void PatientListOnClick(View view) {
        Toast.makeText(this, "Patient page", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(AdminDashboard.this, AddDoctor.class);
        startActivity(intent2);
        finish();
    }

    public void ReportOnClick(View view) {
        Toast.makeText(this, "Report page", Toast.LENGTH_SHORT).show();
        Intent intent3 = new Intent(AdminDashboard.this, AddDoctor.class);
        startActivity(intent3);
        finish();
    }

    public void FeedbackOnClick(View view) {
        Toast.makeText(this, "Feedback page", Toast.LENGTH_SHORT).show();
        Intent intent4 = new Intent(AdminDashboard.this, AddDoctor.class);
        startActivity(intent4);
        finish();
    }

    public void PaymentOnClick(View view) {
        Toast.makeText(this, "Payment page", Toast.LENGTH_SHORT).show();
        Intent intent5 = new Intent(AdminDashboard.this, AddDoctor.class);
        startActivity(intent5);
        finish();
    }
}