package com.example.appointmentapp.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appointmentapp.Adapter.AppointmentsAdapter;
import com.example.appointmentapp.Domain.AppointmentModel;
import com.example.appointmentapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView appointmentsRecyclerView;
    private AppointmentsAdapter appointmentsAdapter;
    private List<AppointmentModel> appointmentList;
    private DatabaseReference databaseReference;
    private String userName;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointments);

        appointmentsRecyclerView = findViewById(R.id.appointmentsRecyclerView);
        appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        appointmentList = new ArrayList<>();
        appointmentsAdapter = new AppointmentsAdapter(appointmentList);
        appointmentsRecyclerView.setAdapter(appointmentsAdapter);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        userName = getIntent().getStringExtra("userName");
        if (userName == null) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("appointments").child(userName);
        fetchAppointments();
    }

    private void fetchAppointments() {
        FirebaseDatabase.getInstance().getReference("appointments")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        appointmentList.clear();
                        for (DataSnapshot appointmentSnapshot : snapshot.getChildren()) {
                            AppointmentModel appointment = appointmentSnapshot.getValue(AppointmentModel.class);
                            if (appointment != null && appointment.getUserName().equals(userName)) {
                                appointmentList.add(appointment);
                            }
                        }
                        appointmentsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(AppointmentsActivity.this, "Failed to load appointments", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
