package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.example.hc.Admin.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity {
    Button forgetPassword,login,register;
    TextInputLayout email,password;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.getReference().child("User").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int usertype = snapshot.getValue(Integer.class);

                    if(usertype == 1){
                        Toast.makeText(Login.this, "Admin Login Successfully.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,AdminDashboard.class);
                        startActivity(intent);
                        finish();

                    }
                    else if(usertype ==0){
                        Toast.makeText(Login.this, "User Login Successfully.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "User Does not exists!.",Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Login.this, "Invalid User!.",Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        //send notification
        FirebaseMessaging.getInstance().subscribeToTopic("LoginNotification");


        forgetPassword = findViewById(R.id.forgetpassword);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.L_registerBtn);

        email = findViewById(R.id.L_email);
        password = findViewById(R.id.L_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getEditText().getText().toString();
                String Password = password.getEditText().getText().toString();

                if (!Email.isEmpty()){
                    email.setError(null);
                    email.setErrorEnabled(false);
                    if(!Password.isEmpty()) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        final String Emaildata = email.getEditText().getText().toString();
                        final String PasswordData = password.getEditText().getText().toString();

                        LoginUsers(Emaildata,PasswordData);

                    }else {
                        password.setError("Please Enter Password.");
                    }
                }
                else {
                    email.setError("Please Enter Email.");
                }

            }
        });
//register page redirect
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
//forget password page redirect
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(intent);
            }
        });
    }

    private void LoginUsers(String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            firebaseDatabase.getReference().child("User").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int usertype = snapshot.getValue(Integer.class);

                                    if(usertype ==0){
                                        Toast.makeText(Login.this, "User Login Successfully.",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this,Home.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if(usertype == 1){
                                        Toast.makeText(Login.this, "Admin Login Successfully.",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this,AdminDashboard.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            // Sign in success, update UI with the signed-in user's information
//                            Toast.makeText(Login.this, "Login Successfully.",Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(getApplicationContext(),Home.class);
//                            startActivity(intent);
//                            finish();

                           // FirebaseUser user = firebaseAuth.getCurrentUser();

                        }
//                        else {
//                            // If sign in fails, display a message to the user.
//
//                            Toast.makeText(Login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
    }
}