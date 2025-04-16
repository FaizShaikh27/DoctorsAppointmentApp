package com.example.appointmentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appointmentapp.R;
import com.example.appointmentapp.databinding.ViewholderDateBinding;
import com.example.appointmentapp.databinding.ViewholderTopDoctorBinding;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.TimeViewholder> {
    private final List<String> timeSlots;
    private  int selectedPosition = -1;
    private int lastSelectedPosition = -1;

    public DateAdapter(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public DateAdapter.TimeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderDateBinding binding = ViewholderDateBinding.inflate(LayoutInflater.from(
                parent.getContext()) ,parent, false);
        return new TimeViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.TimeViewholder holder, int position) {
holder.bind(timeSlots.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public class TimeViewholder extends RecyclerView.ViewHolder {
        private final ViewholderDateBinding binding;
        public TimeViewholder(ViewholderDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(String date, int position, DateAdapter adapter){
            String[] dateParts = date.split("/");
            if(dateParts.length == 3){
                binding.dayText.setText(dateParts[0]);
                binding.dateMonthTxt.setText(dateParts[1]+" "+ dateParts[2]);

                Context context = binding.getRoot().getContext();
                if(adapter.selectedPosition==position){
                    binding.mainLayout.setBackgroundResource(R.drawable.blue_btn_bg);
                    binding.dayText.setTextColor(ContextCompat.getColor(context, R.color.white));
                    binding.dateMonthTxt.setTextColor(ContextCompat.getColor(context, R.color.white));
                } else{
                    binding.mainLayout.setBackgroundResource(R.drawable.light_grey_bg);
                    binding.dayText.setTextColor(ContextCompat.getColor(context, R.color.black));
                    binding.dateMonthTxt.setTextColor(ContextCompat.getColor(context, R.color.black));
                }

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.lastSelectedPosition=adapter.selectedPosition;
                        adapter.selectedPosition=position;
                        adapter.notifyItemChanged(adapter.lastSelectedPosition);
                        adapter.notifyItemChanged(adapter.selectedPosition);
                    }
                });
            }
        }
    }
}
