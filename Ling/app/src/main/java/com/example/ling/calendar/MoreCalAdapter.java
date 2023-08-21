package com.example.ling.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.databinding.ItemCalendarInfoBinding;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.util.ArrayList;

public class MoreCalAdapter extends RecyclerView.Adapter<MoreCalAdapter.ViewHolder>{

    ArrayList<ScheAddVO> list;

    public MoreCalAdapter(ArrayList<ScheAddVO> list, Context context) {
        this.list = list;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemCalendarInfoBinding binding = ItemCalendarInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoreCalAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        ScheAddVO vo = list.get(i);
        // 다음 두 줄을 추가하여 일정이 없는 경우에도 빈 데이터를 처리
        if (vo.getSche_title() == null || vo.getSche_title().isEmpty()) {
            h.binding.tvCalendarInfoTitle.setText("일정 없음");
            h.binding.tvCalendarInfoDate.setVisibility(View.GONE); // 날짜 숨김
        } else {
            h.binding.tvCalendarInfoTitle.setText("-일정: "+ vo.getSche_title());
            h.binding.tvCalendarInfoDate.setText("-날짜: " + vo.getSche_date());
        }


//        if(vo.getSche_date()==)

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemCalendarInfoBinding binding;

        public ViewHolder(@NonNull ItemCalendarInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
