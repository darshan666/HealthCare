package com.example.hc.Patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hc.Admin.Doctor.DoctorListAdaptor;
import com.example.hc.Model.Doctor;
import com.example.hc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    public PatientDoctorlistAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pdoctorlist,parent,false);
        return new PatientDoctorlistAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientDoctorlistAdaptor.MyViewHolder holder, int Position) {
        Doctor doctorData = doctorListData.get(Position);
        holder.name.setText(doctorData.getFullname());
        holder.specialist.setText(doctorData.getSpecialist());
        holder.fees.setText("Rs."+doctorData.getFees());
        Picasso.get().load(doctorData.getImageUrl()).into(holder.doctorImg);
        setAnimation(holder.itemView,Position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aa = v.getId();
                pdoctorListClickInterface.onDoctorClick(lastPos);
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
