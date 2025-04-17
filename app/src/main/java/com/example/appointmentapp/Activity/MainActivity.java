package com.example.appointmentapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appointmentapp.Adapter.CategoryAdapter;
import com.example.appointmentapp.Adapter.TopDoctorsAdapter;
import com.example.appointmentapp.R;
import com.example.appointmentapp.ViewModel.MainViewModel;
import com.example.appointmentapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    ImageView redirectAppointmentsPage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new MainViewModel();
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            binding.textView.setText("Hi, " + name);
        }

        redirectAppointmentsPage = findViewById(R.id.showAppointments);

        redirectAppointmentsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AppointmentsActivity.class);
                i.putExtra("userName" , name);
                startActivity(i);
            }
        });

        initCategory();
        initTopDoctors(name);

    }

    private void initTopDoctors(String userName) {
        binding.progressBarDoctor.setVisibility(View.VISIBLE);
        viewModel.loadDoctors().observe(this, doctorsModels -> {
            binding.doctorView.setLayoutManager(
                    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            );
            // 2️⃣ Provide userName to the adapter
            binding.doctorView.setAdapter(
                    new TopDoctorsAdapter(doctorsModels, userName)
            );
            binding.progressBarDoctor.setVisibility(View.GONE);
        });
    }

    private void initCategory() {
        binding.progressBarCat.setVisibility(View.VISIBLE);

        viewModel.loadCategory().observe(this, categoryModels -> {
            binding.catView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            binding.catView.setAdapter(new CategoryAdapter(categoryModels));
            binding.progressBarCat.setVisibility(View.GONE);
        });
    }
}