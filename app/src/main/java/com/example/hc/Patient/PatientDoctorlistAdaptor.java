package com.example.hc.Patient;

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


import com.example.hc.Admin.ViewAppointments.*;
import com.example.hc.Model.Doctor;
import com.example.hc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientDoctorlistAdaptor extends RecyclerView.Adapter<PatientDoctorlistAdaptor.MyViewHolder> {
    ArrayList<Doctor> doctorListData;
//    List<Doctor> doctorList;
    Context context;
    private int lastPos =0;
    PatientDoctorListClickInterface pdoctorListClickInterface;

    public PatientDoctorlistAdaptor(ArrayList<Doctor> doctorListData, Context context, PatientDoctorlistAdaptor.PatientDoctorListClickInterface doctorListClickInterface) {
        this.doctorListData = doctorListData;
        this.context = context;
        this.pdoctorListClickInterface = doctorListClickInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdoctorlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int Position) {
        Doctor doctorData = doctorListData.get(Position);
        holder.name.setText(doctorData.getFullname());
        holder.specialist.setText(doctorData.getSpecialist());
        holder.fees.setText("Rs."+doctorData.getFees());

        Picasso.get().load(doctorData.getImageUrl()).into(holder.doctorImg);
        setAnimation(holder.itemView,Position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, ViewDoctorDetails.class);
                intent.putExtra("docId",doctorData.getDocId());
                intent.putExtra("DoctorImg",doctorData.getImageUrl());
                intent.putExtra("Fullname",doctorData.getFullname());
                intent.putExtra("Specialist",doctorData.getSpecialist());
                intent.putExtra("Description",doctorData.getDescription());
                intent.putExtra("Fees",doctorData.getFees());
                intent.putExtra("Location",doctorData.getLocation());
                intent.putExtra("City",doctorData.getCity());
                intent.putExtra("State",doctorData.getState());
                intent.putExtra("Schedule",doctorData.getSchedule());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    private void setAnimation(View itemView,int position){
        if (position > lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return doctorListData.size();
    }

    public void setFilteredList(ArrayList<Doctor> filteredList) {
        this.doctorListData = filteredList;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,specialist,fees;
        CircleImageView doctorImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.PDFullname);
            specialist = itemView.findViewById(R.id.PDSpecilist);
            fees = itemView.findViewById(R.id.PDFees);
            doctorImg = itemView.findViewById(R.id.PDImage);
        }
    }

    public interface PatientDoctorListClickInterface{

        void onDoctorClick(int position );
    }
}
