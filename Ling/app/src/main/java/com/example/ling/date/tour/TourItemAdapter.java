package com.example.ling.date.tour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvTourBinding;

public class TourItemAdapter extends RecyclerView.Adapter<TourItemAdapter.ViewHolder> {

    ItemRecvTourBinding binding;
    Context context;

    public TourItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvTourBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvTour.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvTour.setText("지역");
       h.binding.cvTour.setOnClickListener(v -> {
           Intent intent = new Intent(context, TourActivity.class);
           context.startActivity(intent);
       });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvTourBinding binding;

        public ViewHolder(@NonNull ItemRecvTourBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
