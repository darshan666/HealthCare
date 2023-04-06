package com.example.hc.Admin.Payment;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hc.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Rozarpay_Payment extends AppCompatActivity implements PaymentResultListener {

    Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozarpay_payment);

        Checkout.preload(Rozarpay_Payment.this);


        payment = findViewById(R.id.AptPayment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });


    }

    private void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.ic_launcher);
        checkout.setKeyID("rzp_test_bb3pkwebnYZpxN");
        try {
            JSONObject options = new JSONObject();
            options.put("name",R.string.app_name);
            options.put("describation","Payment for Anything");

            options.put("currency","INR");
            options.put("amount","100");


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
        Toast.makeText(Rozarpay_Payment.this, "Payment Success"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
}