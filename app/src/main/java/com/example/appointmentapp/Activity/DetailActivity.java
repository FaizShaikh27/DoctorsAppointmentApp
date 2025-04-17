package com.example.appointmentapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.appointmentapp.Adapter.DateAdapter;
import com.example.appointmentapp.Adapter.TimeAdapter;
import com.example.appointmentapp.Domain.AppointmentModel;
import com.example.appointmentapp.Domain.DoctorsModel;
import com.example.appointmentapp.R;
import com.example.appointmentapp.databinding.ActivityDetailBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DateAdapter.OnDateSelectedListener, TimeAdapter.OnTimeSelectedListener {
    private ActivityDetailBinding binding;
    private DoctorsModel item;

    private String selectedDate = "";
    private String selectedTime = "";
    private String userName;
    private String doctorName;

    FirebaseDatabase database;
    DatabaseReference reference;

    Button book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        item=(DoctorsModel) getIntent().getSerializableExtra("object");
        userName    = getIntent().getStringExtra("userName");
        doctorName  = getIntent().getStringExtra("doctorName");
        if (userName == null)   userName   = "Unknown User";
        if (doctorName == null) doctorName = item.getName();

        setVariable();
        InitDate();
        InitTime();

        book = findViewById(R.id.actionBook);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("appointments");

                AppointmentModel appointmentModel = new AppointmentModel(userName, doctorName, selectedDate, selectedTime);

                // Generate a unique key for each appointment
                String appointmentId = reference.push().getKey();

                if (appointmentId != null) {
                    reference.child(appointmentId).setValue(appointmentModel);
                    Toast.makeText(DetailActivity.this, "Appointment Booked", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Failed to book appointment", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InitTime() {
        binding.timeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.timeView.setAdapter(new TimeAdapter(generateTimeSlots(), this));
    }

    private void InitDate() {
        binding.dateView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.dateView.setAdapter(new DateAdapter(generateDates(), this));
    }

    public static List<String> generateTimeSlots(){
        List<String> timeSlots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        for (int i = 0; i < 24; i+=2) {
            LocalTime time = LocalTime.of(i, 0);
            timeSlots.add(time.format(formatter));
        }
        return timeSlots;
    }
    public static List<String> generateDates(){
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM");
        for (int i = 0; i < 7; i++) {
            dates.add(today.plusDays(i).format(formatter));
            
        }
        return dates;
    }

    private void setVariable() {

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Glide.with(DetailActivity.this).load(item.getPicture()).into(binding.img);

        binding.addressTxt.setText(item.getAddress());
        binding.nameTxt.setText(item.getName());
        binding.specialTxt.setText(item.getSpecial());
        binding.patiensTxt.setText(item.getPatiens()+"");
        binding.experienceTxt.setText(item.getExperiense()+" Years");
    }

    @Override
    public void onDateSelected(String date) {
        selectedDate = date;

    }

    @Override
    public void onTimeSelected(String time) {
        selectedTime = time;
    }
}