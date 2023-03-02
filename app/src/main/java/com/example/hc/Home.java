package com.example.hc;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ImageSlider imageSlider;
    BottomNavigationView nav;

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


    // BottomBar Buttons
        nav = findViewById(R.id.bottomNavbar);

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

                        Intent intent1 = new Intent(getApplicationContext(),Dashboad.class);
                        startActivity(intent1);
                        //finish();

                        Toast.makeText(Home.this, "Search Page", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.list:

                        Intent intent2 = new Intent(getApplicationContext(),AppointmentPage.class);
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

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
//            txtEmail.setText(currentUser.getEmail());
        }
        else {
            startActivity(new Intent(this,Login.class));
            finish();
        }
    }
}