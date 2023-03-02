package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    Button register,login;
    TextInputLayout fullname,username,email,phone,password;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register = findViewById(R.id.registerbtn);
        login = findViewById(R.id.loginOnClick);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);



    }

    public  void  loginOnClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }

    public void registerOnClickButton(View view) {
        String Fullname = fullname.getEditText().getText().toString();
        String Username = username.getEditText().getText().toString();
        String Email = email.getEditText().getText().toString();
        String Mobile = phone.getEditText().getText().toString();
        String Password = password.getEditText().getText().toString();

        if(!Fullname.isEmpty()){
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            if(!Username.isEmpty()){
                username.setError(null);
                username.setErrorEnabled(false);
                if(!Email.isEmpty()){
                    email.setError(null);
                    email.setErrorEnabled(false);
                    if(!Mobile.isEmpty()){
                        phone.setError(null);
                        phone.setErrorEnabled(false);
                        if(!Password.isEmpty()){
                            password.setError(null);
                            password.setErrorEnabled(false);
                                if(!Email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){

                                    String Fullname_ = fullname.getEditText().getText().toString();
                                    String Username_ = username.getEditText().getText().toString();
                                    String Email_ = email.getEditText().getText().toString();
                                    String Mobile_ = phone.getEditText().getText().toString();
                                    String Password_ = password.getEditText().getText().toString();

                                    firebaseAuth = FirebaseAuth.getInstance();
                                    //create user
                                    firebaseAuth.createUserWithEmailAndPassword(Email_,Password_).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful())
                                            {
                                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                                String userid = firebaseUser.getUid();

                                                //enter data to real time database
                                                reference = FirebaseDatabase.getInstance().getReference("Registered Users").child(userid);
                                                HashMap<String,String> hashMap = new HashMap<>();
                                                hashMap.put("id",userid);
                                                hashMap.put("username",Username_);
                                                hashMap.put("name",Fullname_);
                                                hashMap.put("mobile",Mobile_);
                                                hashMap.put("imageUrl","default");
                                               // DataStore dataStore = new DataStore(Fullname_,Username_,Mobile_,"default");
                                                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText(getApplicationContext(), "Register SuccessFully", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(getApplicationContext(),Home.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "Registeration Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else {
                                    email.setError("Invalid Email");
                                }

                        }
                        else {
                            password.setError("Please enter password.");
                        }
                    }
                    else {
                        phone.setError("Please enter Mobile number.");
                    }
                }
                else {
                    email.setError("Please enter Email.");
                }
            }
            else {
                username.setError("Please enter Username.");
            }
        }
        else {
            fullname.setError("Please enter fullName.");
        }
    }
}