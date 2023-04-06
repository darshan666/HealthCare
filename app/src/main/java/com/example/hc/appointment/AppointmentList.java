package com.example.hc.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hc.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


import android.os.Bundle;


import com.example.hc.Model.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;



public class AppointmentList extends AppCompatActivity implements AppointmentAdapter.AppointmentListClickInterface{

    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<AppointmentListModel> AptListDataArrayList;
    //RelativeLayout BottomSheet;
    AppointmentAdapter appointmentAdapter;
    AppointmentListModel appointmentListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        recyclerView = findViewById(R.id.rvAppointment);
        reference = FirebaseDatabase.getInstance().getReference("Appointments");
        AptListDataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(AptListDataArrayList,this,this);
        recyclerView.setAdapter(appointmentAdapter);
       // BottomSheet = findViewById(R.id.idRLDocBSheet);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AptListDataArrayList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String PatientName=postSnapshot.child("Fullname").getValue(String.class);
                    String Gender=postSnapshot.child("Gender").getValue(String.class);
                    String DOB=postSnapshot.child("Dob").getValue(String.class);

                    String AptDate  =postSnapshot.child("AppointmentDateTimes").getValue(String.class);
                    String AptReason=postSnapshot.child("AppointmentReason").getValue(String.class);

                    AptListDataArrayList.add(new AppointmentListModel(PatientName,Gender,DOB,AptDate,AptReason));

                }
                setData(AptListDataArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        reference.addListenerForSingleValueEvent(valueEventListener);


    }

    private void setData(ArrayList<AppointmentListModel> data) {
        appointmentAdapter = new AppointmentAdapter(data,getApplicationContext(),this);
        recyclerView.setAdapter(appointmentAdapter);
    }

    public void onAppointmentClick(int position) {
        //displayBottomSheet(doctorListDataArrayList.get(position));
    }

//    private void displayBottomSheet(Doctor doctor){
//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//
//        View layout = LayoutInflater.from(this).inflate(R.layout.doctor_bottom_sheet_dialog,BottomSheet);
//        bottomSheetDialog.setContentView(layout);
//        bottomSheetDialog.setCancelable(false);
//        bottomSheetDialog.setCanceledOnTouchOutside(true);
//        bottomSheetDialog.show();
//
//        TextView DoctorName = layout.findViewById(R.id.idDoctorName);
//        TextView Describation = layout.findViewById(R.id.idDescribation);
//        TextView Specilist = layout.findViewById(R.id.idSpecilist);
//        TextView Fees = layout.findViewById(R.id.idFees);
//        CircleImageView DocImg = layout.findViewById(R.id.idDoctorImg);
//        Button AddAppointment = layout.findViewById(R.id.idBtnBookAppointment);
//        Button ViewDetails = layout.findViewById(R.id.idBtnViewDetails);
//        DoctorName.setText(doctor.getName());
//        Describation.setText(doctor.getName());
//        Specilist.setText(doctor.getSpeciality());
//        Fees.setText("Rs."+ doctor.getFees());
//        Picasso.get().load(doctor.getImageUrl()).into(DocImg);
//
//        AddAppointment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DoctorList.this,BookAppointment.class);
//                //i.putExtra("Doctor",Doctor);
//                startActivity(i);
//
//            }
//        });
//
//        ViewDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(DoctorList.this, AddDoctor.class);
//                startActivity(i);
//            }
//        });
//
//    }


}