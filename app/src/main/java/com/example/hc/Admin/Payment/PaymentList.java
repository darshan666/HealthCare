package com.example.hc.Admin.Payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hc.Admin.Doctor.DoctorListAdaptor;
import com.example.hc.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import com.example.hc.Model.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentList extends AppCompatActivity {
    RecyclerView payRecyclerView;
    DatabaseReference reference;
    ArrayList<PaymentModel> paymentModelList;
    PaymentListAdaptor paymentListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);

        payRecyclerView = findViewById(R.id.rvPayment);
        reference = FirebaseDatabase.getInstance().getReference("Payment").child("4E6rZR6QOLXXprqwqAudFwY5aqm1");
        paymentModelList = new ArrayList<>();
        payRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentListAdaptor = new PaymentListAdaptor(paymentModelList,this);
        payRecyclerView.setAdapter(paymentListAdaptor);


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                paymentModelList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String paymentId=postSnapshot.child("paymentId").getValue(String.class);
                    String pateintId=postSnapshot.child("pateintId").getValue(String.class);
                    String aptId=postSnapshot.child("aptId").getValue(String.class);
                    String paymentDate=postSnapshot.child("paymentDate").getValue(String.class);
                    String type=postSnapshot.child("type").getValue(String.class);
                    Integer amount = postSnapshot.child("amount").getValue(Integer.class);
                    String status=postSnapshot.child("status").getValue(String.class);
                    paymentModelList.add(new PaymentModel(paymentId, pateintId, aptId, paymentDate, type, amount, status));

                }
                setData(paymentModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        reference.addListenerForSingleValueEvent(valueEventListener);
    }
    private void setData(ArrayList<PaymentModel> data){
        paymentListAdaptor = new PaymentListAdaptor(data,getApplicationContext());
        payRecyclerView.setAdapter(paymentListAdaptor);
    }
}