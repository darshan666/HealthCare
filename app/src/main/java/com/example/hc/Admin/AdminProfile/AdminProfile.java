package com.example.hc.Admin.AdminProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hc.Login;
import com.example.hc.ProfilePage;
import com.example.hc.R;
import com.example.hc.preferences;
import com.example.hc.profile.UpdateProfile;
import com.google.firebase.auth.FirebaseAuth;


public class AdminProfile extends AppCompatActivity {

    TextView profileUpdate;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        profileUpdate = findViewById(R.id.AdProfileUpdate);
        logout = findViewById(R.id.Adlogout);

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateProfile.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                preferences.clearData(AdminProfile.this);
                finish();
                Toast.makeText(AdminProfile.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}