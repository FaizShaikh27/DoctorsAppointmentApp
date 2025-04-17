package com.example.appointmentapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appointmentapp.R;
import com.example.appointmentapp.databinding.ViewholderDateBinding;
import java.util.List;

public class DateAdapter
        extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {

    /** Listener interface to notify host when a date is tapped */
    public interface OnDateSelectedListener {
        void onDateSelected(String date);
    }

    private final List<String> dates;
    private final OnDateSelectedListener listener;
    private int selectedPosition = -1;

    /** Constructor takes the list of date strings and the listener callback */
    public DateAdapter(List<String> dates, OnDateSelectedListener listener) {
        this.dates = dates;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderDateBinding binding =
                ViewholderDateBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                );
        return new DateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String date = dates.get(position);
        String[] parts = date.split("/");
        holder.binding.dayText.setText(parts[0]);
        holder.binding.dateMonthTxt.setText(parts[1] + " " + parts[2]);

        // Highlight selected item
        holder.binding.mainLayout.setBackgroundResource(
                position == selectedPosition
                        ? R.drawable.blue_btn_bg
                        : R.drawable.light_grey_bg
        );

        // Handle click, update selection, and notify listener
        holder.binding.getRoot().setOnClickListener(v -> {
            int previous = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previous);
            notifyItemChanged(position);
            listener.onDateSelected(date);
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    /** Static ViewHolder for better memory management */
    static class DateViewHolder extends RecyclerView.ViewHolder {
        final ViewholderDateBinding binding;
        DateViewHolder(ViewholderDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
