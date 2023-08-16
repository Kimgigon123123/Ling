package com.example.ling.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
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


        h.binding.imgvScheDelete.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("일정삭제");
            alert.setMessage("정말로 삭제하시겠습니까?");

            alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CommonConn conn = new CommonConn(context, "sche_delete");
                    conn.addParamMap("sche_no", list.get(i).getSche_no());
                    list.remove(i);
                    notifyDataSetChanged();
                    conn.onExcute(new CommonConn.JswCallBack() {


                        @Override
                        public void onResult(boolean isResult, String data) {

                        }
                    });
                }
            });

            alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            alert.show();

        });

    }

    @Override
    public int getItemCount() {
        if(list.size()<3){
            return list.size();
        }else{
            return 3;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemRecvScheduleBinding binding;
        public ViewHolder(@NonNull ItemRecvScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
