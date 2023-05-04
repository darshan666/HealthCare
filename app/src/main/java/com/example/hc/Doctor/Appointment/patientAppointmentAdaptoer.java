package com.example.hc.Doctor.Appointment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Model.AppointmentListModel;
import com.example.hc.R;
import com.example.hc.appointment.AppointmentAdapter;
import com.example.hc.appointment.ViewAppointmentDetails;

import java.util.ArrayList;

public class patientAppointmentAdaptoer extends RecyclerView.Adapter<patientAppointmentAdaptoer.MyViewHolder>  {

    ArrayList<AppointmentListModel> AptList;
    Context context;

    public patientAppointmentAdaptoer(ArrayList<AppointmentListModel> aptList, Context context) {
        AptList = aptList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.appointmentlist_cardview,parent,false);
        return new patientAppointmentAdaptoer.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AppointmentListModel model = AptList.get(position);
        holder.PatientName.setText(model.getPatientName());
        holder.Gender.setText(model.getGender());
        holder.DOB.setText(model.getDob());
        holder.AptDate.setText(model.getAptDate());
        holder.AptTime.setText(model.getAptTime());
        holder.AptReason.setText(model.getAptReason());
       // setAnimation(holder.itemView,position);
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

    @Override
    public int getItemCount() {
        return AptList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
}

