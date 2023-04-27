package com.example.hc.Admin.Payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.hc.appointment.*;
import com.example.hc.Model.*;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Rozarpay_Payment extends AppCompatActivity implements PaymentResultListener {

    Integer Amount;
    String aptId,patientId;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    Date currentDate = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozarpay_payment);

        Checkout.preload(Rozarpay_Payment.this);

        Amount = Integer.valueOf(getIntent().getStringExtra("Payment"));
        aptId = getIntent().getStringExtra("AptId");
        patientId = getIntent().getStringExtra("patientId");
        startPayment(Amount);


        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void startPayment(int amount) {
        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.ic_launcher);
        checkout.setKeyID("rzp_test_bb3pkwebnYZpxN");
        try {
            JSONObject options = new JSONObject();
            options.put("name",R.string.app_name);
            options.put("describation","Payment for Anything");

            options.put("currency","INR");
            options.put("amount",amount*100);


            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled",true);
            retryObj.put("max_count",4);

            options.put("retryObj",retryObj);

            checkout.open(Rozarpay_Payment.this,options);

        }
        catch (Exception e) {
            Toast.makeText(Rozarpay_Payment.this, "Error in Payment", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {


            String userid = firebaseAuth.getCurrentUser().getUid();
            reference = FirebaseDatabase.getInstance().getReference("Payment").child(userid);

            final AtomicInteger count = new AtomicInteger(0);
            Integer Id = count.incrementAndGet();

            PaymentModel paymentModel = new PaymentModel(s,patientId,aptId, currentDate.toString(),"Online",Amount,"SuccessFull");

            reference.child(s).setValue(paymentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Rozarpay_Payment.this, "Payment SuccessFull", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Rozarpay_Payment.this, "Payment Success"+s, Toast.LENGTH_SHORT).show();




    }

    @Override
    public void onPaymentError(int i, String s) {
            String userid = firebaseAuth.getCurrentUser().getUid();
            reference = FirebaseDatabase.getInstance().getReference("Payment").child(userid);

            final AtomicInteger count = new AtomicInteger(0);
            Integer Id = count.incrementAndGet();

            PaymentModel paymentModel = new PaymentModel(s,"0212","0123", currentDate.toString(),"Online",Amount,"Failed");

            reference.child(s).setValue(paymentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Rozarpay_Payment.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Rozarpay_Payment.this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
}