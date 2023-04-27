package com.example.hc.appointment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.R;
import com.example.hc.Model.*;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    ArrayList<AppointmentListModel> AptList;
    Context context;
    int lastPos =-1;
    AppointmentListClickInterface AptListClickInterface;

    public AppointmentAdapter(ArrayList<AppointmentListModel> aptList, Context context, AppointmentListClickInterface aptListClickInterface) {
        AptList = aptList;
        this.context = context;
        AptListClickInterface = aptListClickInterface;
    }

    @NonNull
    @Override
    public AppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.appointmentlist_cardview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.MyViewHolder holder, int position) {
        AppointmentListModel model = AptList.get(position);
        holder.PatientName.setText(model.getPatientName());
        holder.Gender.setText(model.getGender());
        holder.DOB.setText(model.getDob());
        holder.AptDate.setText(model.getAptDate());
        holder.AptTime.setText(model.getAptTime());
        holder.AptReason.setText(model.getAptReason());
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewAppointmentDetails.class);
                i.putExtra("Id",model.getId());
                i.putExtra("PatientName",model.getPatientName());
                i.putExtra("Gender",model.getGender());
                i.putExtra("DOB",model.getDob());
                i.putExtra("PhoneNumber",model.getPhoneNumber());
                i.putExtra("Address",model.getAddress());
                i.putExtra("City",model.getCity());
                i.putExtra("State",model.getState());
                i.putExtra("Pincode",model.getPincode());
                i.putExtra("AptDate",model.getAptDate());
                i.putExtra("AptTime",model.getAptTime());
                i.putExtra("AptReason",model.getAptReason());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });

    }

    private void setAnimation(View itemView,int position){
        if (position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return AptList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView PatientName,Gender,DOB,AptDate,AptReason,AptTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            PatientName = itemView.findViewById(R.id.txtPatientName);
            Gender = itemView.findViewById(R.id.txtGender);
            DOB = itemView.findViewById(R.id.txtDob);
            AptDate = itemView.findViewById(R.id.txtAppointmentDate);
            AptTime = itemView.findViewById(R.id.txtAppointmentTime);
            AptReason = itemView.findViewById(R.id.txtAppointmentReason);
        }
    }

    public interface AppointmentListClickInterface {

        void onAppointmentClick(int lastPos);
    }
}
