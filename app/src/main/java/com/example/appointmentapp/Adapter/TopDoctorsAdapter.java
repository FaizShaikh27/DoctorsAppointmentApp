package com.example.appointmentapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.appointmentapp.Activity.DetailActivity;
import com.example.appointmentapp.Domain.DoctorsModel;
import com.example.appointmentapp.databinding.ViewholderTopDoctorBinding;

import java.util.List;

public class TopDoctorsAdapter extends RecyclerView.Adapter<TopDoctorsAdapter.Viewholder> {
    private final List<DoctorsModel> items;
    private Context context;

    private final String userName;    // ← user’s name



    public TopDoctorsAdapter(List<DoctorsModel> items, String userName) {
        this.items = items;
        this.userName = userName;
    }

    @NonNull
    @Override
    public TopDoctorsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderTopDoctorBinding binding = ViewholderTopDoctorBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        );
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopDoctorsAdapter.Viewholder holder, int position) {
        DoctorsModel doctorsModel = items.get(position);
        holder.binding.nameTxt.setText(doctorsModel.getName());
        holder.binding.specialTxt.setText(doctorsModel.getSpecial());
        holder.binding.ratingTxt.setText(doctorsModel.getRating()+"");
        holder.binding.patiensTxt.setText(doctorsModel.getPatiens()+" Years");

        Glide.with(holder.itemView.getContext()).load(doctorsModel.getPicture()).apply(new RequestOptions().transform(new CenterCrop())).into(holder.binding.img);



        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", doctorsModel);
                intent.putExtra("userName", userName);
                // (optional) also pass the doctor’s name explicitly
                intent.putExtra("doctorName", doctorsModel.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ViewholderTopDoctorBinding binding;

        public Viewholder(ViewholderTopDoctorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
