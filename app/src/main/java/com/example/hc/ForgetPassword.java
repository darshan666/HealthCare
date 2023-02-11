package com.example.hc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPassword extends AppCompatActivity {

   private  Button forgetBtn;
   private TextInputLayout email;
   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        email = findViewById(R.id.F_email);

        forgetBtn = findViewById(R.id.forgetBtn);

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getEditText().getText().toString();

                if(!Email.isEmpty())
                {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    if(!Email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
                        String Email_ = email.getEditText().getText().toString();

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.sendPasswordResetEmail(Email_).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(ForgetPassword.this, "Please check your email inbox for reset password link", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(),Login.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(ForgetPassword.this, "Something want wrong", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                    }
                    else
                    {
                        email.setError("Invalid Email");
                    }
                }
                else {
                    email.setError("Email is Empty");
                }
            }
        });
    }
}