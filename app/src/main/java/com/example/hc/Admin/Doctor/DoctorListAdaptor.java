package com.example.hc.Admin.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hc.Model.Doctor;
import com.example.hc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public  class DoctorListAdaptor extends RecyclerView.Adapter<DoctorListAdaptor.MyViewHolder>  {
    ArrayList<Doctor> doctorListData;
    Context context;
    private int lastPos =0;
    DoctorListClickInterface doctorListClickInterface;


    public DoctorListAdaptor(ArrayList<Doctor> doctorListData, Context context, DoctorListClickInterface doctorListClickInterface) {
        this.doctorListData = doctorListData;
        this.context = context;
        this.doctorListClickInterface = doctorListClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctorlistcardview,parent,false);
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
                int aa = v.getId();
                doctorListClickInterface.onDoctorClick(lastPos);
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


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,specialist,fees;
        CircleImageView doctorImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.txtDocterName);
            specialist = itemView.findViewById(R.id.txtSpecility);
            fees = itemView.findViewById(R.id.txtFees);
            doctorImg = itemView.findViewById(R.id.imgProfiles);
        }
    }

    public interface DoctorListClickInterface{

        void onDoctorClick(int position );
    }
}
