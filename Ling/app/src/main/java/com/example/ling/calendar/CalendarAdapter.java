package com.example.ling.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {


    ArrayList<ScheAddVO> list;

    public CalendarAdapter(ArrayList<ScheAddVO> list,Context context) {
        this.list = list;
    }


    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvScheduleBinding binding = ItemRecvScheduleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.binding.tvCalendarTitle.setText(list.get(i).getSche_title());
        if(list.get(i).getSche_notice() != 0){
            h.binding.materialSwitch.setChecked(true);
        }else{
            h.binding.materialSwitch.setChecked(false);
        }
        h.binding.tvCalendarDate.setText(list.get(i).getSche_date()+"");
        h.binding.tvDday.setText("D-"+list.get(i).getD_day());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemRecvScheduleBinding binding;
        public ViewHolder(@NonNull ItemRecvScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
