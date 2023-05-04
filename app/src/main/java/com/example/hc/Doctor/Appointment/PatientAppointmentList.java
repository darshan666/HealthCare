package com.example.hc.Doctor.Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hc.Model.AppointmentListModel;
import com.example.hc.R;
import com.example.hc.appointment.AppointmentAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientAppointmentList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    ArrayList<AppointmentListModel> AptListDataArrayList;
    patientAppointmentAdaptoer appointmentAdapter;
    AppointmentListModel appointmentListModel;
    //FloatingActionButton floatingActionButtons;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment_list);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.DrvAppointment);
        reference = FirebaseDatabase.getInstance().getReference("Appointments");
        AptListDataArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new patientAppointmentAdaptoer(AptListDataArrayList,this);
        recyclerView.setAdapter(appointmentAdapter);

        String DocId = auth.getUid().toString();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                AptListDataArrayList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String docId = postSnapshot.child("docId").getValue(String.class);
                    if(docId.equals(DocId)){
                        String aptId = postSnapshot.child("id").getValue(String.class);
                        String PatientName=postSnapshot.child("Fullname").getValue(String.class);
                        String Gender=postSnapshot.child("Gender").getValue(String.class);
                        String DOB=postSnapshot.child("Dob").getValue(String.class);
                        String Address=postSnapshot.child("Address").getValue(String.class);
                        String AptDate  =postSnapshot.child("AppointmentDate").getValue(String.class);
                        String AptTime  =postSnapshot.child("AppointmentTime").getValue(String.class);
                        String AptReason=postSnapshot.child("AppointmentReason").getValue(String.class);
                        String City = postSnapshot.child("City").getValue(String.class);
                        String State = postSnapshot.child("State").getValue(String.class);
                        String Pincode = postSnapshot.child("Pincode").getValue(String.class);
                        String PhoneNumber = postSnapshot.child("PhoneNumber").getValue(String.class);

                        AptListDataArrayList.add(new AppointmentListModel(PatientName,Gender,DOB,AptDate,AptTime,AptReason,Address,City,State,Pincode,PhoneNumber,aptId));


                    }

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
        appointmentAdapter = new patientAppointmentAdaptoer(data,getApplicationContext());
        recyclerView.setAdapter(appointmentAdapter);
    }
}