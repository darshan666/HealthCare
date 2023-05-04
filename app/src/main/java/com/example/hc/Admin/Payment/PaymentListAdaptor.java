package com.example.hc.Admin.Payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Model.*;
import com.example.hc.R;

import java.util.ArrayList;

public class PaymentListAdaptor extends RecyclerView.Adapter<PaymentListAdaptor.MyViewHolder>{
    ArrayList<PaymentModel> paymentList;
    Context context;

    public PaymentListAdaptor(ArrayList<PaymentModel> paymentList, Context context) {
        this.paymentList = paymentList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paymentlist_cardview,parent,false);
        return new PaymentListAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PaymentModel paymentModel = paymentList.get(position);
        holder.status.setText(paymentModel.getStatus());
        holder.price.setText(String.valueOf(paymentModel.getAmount()));

    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView status,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.PayStatus);
            price = itemView.findViewById(R.id.PayPrice);

        }
    }
}
