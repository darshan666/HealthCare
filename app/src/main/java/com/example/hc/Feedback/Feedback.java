package com.example.hc.Feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class Feedback extends AppCompatActivity {

    TextInputLayout feedback ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback = findViewById(R.id.feedback);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Feedback");


    }

    public void feedbackOnClickButton(View view) {
        String Feedback = feedback.getEditText().getText().toString();

        if(!Feedback.isEmpty()){
            feedback.setError(null);
            feedback.setErrorEnabled(false);

            String Feedback_ = feedback.getEditText().getText().toString();

            String FeedbackId = reference.push().getKey();

            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",FeedbackId);
            hashMap.put("Message",Feedback_);

            reference.child(FeedbackId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Feedback.this, "Feedback Send", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Feedback.this, "Feedback Sent Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
        else {
            feedback.setError("Please enter feedback .");
        }
    }
}