package com.example.appointmentapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appointmentapp.Domain.CategoryModel;
import com.example.appointmentapp.Domain.DoctorsModel;
import com.example.appointmentapp.Repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainRepository repository;

    public MainViewModel() {
        this.repository = new MainRepository();
    }
    public LiveData<List<CategoryModel>> loadCategory(){return repository.loadCategory();}

    public LiveData<List<DoctorsModel>> loadDoctors(){
        return repository.loadDoctor();
    }
}
