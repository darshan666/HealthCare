package com.example.hc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Admin.ViewAppointments.*;
import com.example.hc.Model.Doctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopDoctorAdaptor extends RecyclerView.Adapter<TopDoctorAdaptor.MyViewHolder> {

    ArrayList<Doctor> doctorListData;
    Context context;

    public TopDoctorAdaptor(ArrayList<Doctor> doctorListData, Context context) {
        this.doctorListData = doctorListData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topdoctorscardview,parent,false);
        return new TopDoctorAdaptor.MyViewHolder(view);
    }

//    private void setAnimation(View itemView,int position){
//        if (position > 0){
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//            itemView.setAnimation(animation);
//            this = position;
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Doctor doctorData = doctorListData.get(position);
        holder.name.setText(doctorData.getFullname());
        holder.specialist.setText(doctorData.getSpecialist());

        Picasso.get().load(doctorData.getImageUrl()).into(holder.doctorImg);
        //setAnimation(holder.itemView,Position);
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

    @Override
    public int getItemCount() {
        return doctorListData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,specialist;
        CircleImageView doctorImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.TDName);
            specialist = itemView.findViewById(R.id.TDSpecialist);
            doctorImg = itemView.findViewById(R.id.TDImg);
        }
    }
}
