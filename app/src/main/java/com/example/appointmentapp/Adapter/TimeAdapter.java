package com.example.appointmentapp.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appointmentapp.R;
import com.example.appointmentapp.databinding.ViewholderTimeBinding;

import java.util.List;

public class TimeAdapter
        extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    /** Callback interface to notify when the user taps a time slot */
    public interface OnTimeSelectedListener {
        void onTimeSelected(String time);
    }

    private final List<String> times;
    private final OnTimeSelectedListener listener;
    private int selectedPosition = -1;

    /** Receive the list of times and the host’s listener implementation */
    public TimeAdapter(List<String> times, OnTimeSelectedListener listener) {
        this.times = times;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderTimeBinding binding = ViewholderTimeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new TimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder holder, int position) {
        String time = times.get(position);
        holder.binding.timeTxt.setText(time);

        // Highlight selected slot
        holder.binding.timeTxt.setBackgroundResource(
                position == selectedPosition
                        ? R.drawable.blue_btn_bg
                        : R.drawable.light_grey_bg
        );

        // Handle click: update selection and notify host
        holder.binding.getRoot().setOnClickListener(v -> {
            int previous = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previous);
            notifyItemChanged(position);
            listener.onTimeSelected(time);
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    /** Static ViewHolder for better performance & to avoid memory leaks */
    static class TimeViewHolder extends RecyclerView.ViewHolder {
        final ViewholderTimeBinding binding;
        TimeViewHolder(ViewholderTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
