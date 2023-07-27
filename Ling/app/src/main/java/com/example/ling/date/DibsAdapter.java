package com.example.ling.date;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.databinding.ItemRecvDibsBinding;

public class DibsAdapter extends RecyclerView.Adapter<DibsAdapter.ViewHolder> {

    ItemRecvDibsBinding binding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvDibsBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.imgvDibs.setImageResource(R.drawable.ic_launcher_background);
        h.binding.tvName.setText("이름");
        h.binding.tvAddr.setText("주소");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemRecvDibsBinding binding;

        public ViewHolder(@NonNull ItemRecvDibsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
