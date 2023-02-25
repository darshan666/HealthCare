package com.example.hc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePage extends AppCompatActivity {
    Button logout;
    TextView txtEmail;
    FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null){
            txtEmail.setText(currentUser.getEmail());
        }
        else {
            startActivity(new Intent(this,Login.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        logout = findViewById(R.id.logout);
        txtEmail = findViewById(R.id.emailtext);
        firebaseAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                Toast.makeText(ProfilePage.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}