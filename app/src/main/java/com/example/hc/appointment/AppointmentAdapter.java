package com.example.hc.appointment;

import android.content.Context;
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
        holder.AptReason.setText(model.getAptReason());
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AptListClickInterface.onAppointmentClick(lastPos);
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
        TextView PatientName,Gender,DOB,AptDate,AptReason;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            PatientName = itemView.findViewById(R.id.txtPatientName);
            Gender = itemView.findViewById(R.id.txtGender);
            DOB = itemView.findViewById(R.id.txtDob);
            AptDate = itemView.findViewById(R.id.txtAppointmentDate);
            AptReason = itemView.findViewById(R.id.txtAppointmentReason);
        }
    }

    public interface AppointmentListClickInterface {

        void onAppointmentClick(int lastPos);
    }
}
