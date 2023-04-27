package com.example.hc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Admin.Doctor.DoctorDetails;
import com.example.hc.Model.Doctor;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.MyViewHolder> {
    List<Doctor> doctorList;

    public SpecialistAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlistcardview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Doctor doctorData = doctorList.get(position);
        holder.name.setText(doctorData.getFullname());
        holder.specialist.setText(doctorData.getSpecialist());
        holder.fees.setText("Rs."+doctorData.getFees());

        Picasso.get().load(doctorData.getImageUrl()).into(holder.doctorImg);

        }


    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,specialist,description,fees,schedule,location,city,state;
        CircleImageView doctorImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.txtDocterName);
            specialist = itemView.findViewById(R.id.txtSpecility);
            fees = itemView.findViewById(R.id.txtFees);
            doctorImg = itemView.findViewById(R.id.imgProfiles);
        }
    }
}
