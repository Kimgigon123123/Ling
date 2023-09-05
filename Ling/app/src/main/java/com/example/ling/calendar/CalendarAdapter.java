package com.example.ling.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityCalendarBinding;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    Context context;
    ArrayList<ScheAddVO> list;
    static DdayDialog loadingDialog;

    ActivityCalendarBinding binding;

    public CalendarAdapter(ArrayList<ScheAddVO> list, Context context, ActivityCalendarBinding binding) {
        this.list = list;
        this.context = context;
        this.binding = binding;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvScheduleBinding binding = ItemRecvScheduleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date scheDate = null;
        try {
            scheDate = inputFormat.parse(list.get(position).getSche_date());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedTime = outputFormat.format(scheDate);

        h.binding.tvCalendarDate.setText(formattedTime);


        //캘린더 리스트에서 알림 여부 조회
        h.binding.tvCalendarTitle.setText(list.get(position).getSche_title());
        if (list.get(position).getSche_notice() != 0) {
            h.binding.materialSwitch.setChecked(false);
        } else {
            h.binding.materialSwitch.setChecked(true);
        }


        //캘린더 리스트에서 d-day 조회
//        if(list.get(i).getD_day()==0){
//            h.binding.setVisibility(View.GONE);
//        }else{
        int dDay = list.get(position).getD_day();
        String dDayText;
        if (dDay < 10) {
            dDayText = "D-0" + dDay;
        } else {
            dDayText = "D-" + dDay;
        }
        h.binding.tvDday.setText(dDayText);

        if (list.get(position).getD_day() <= 3 && list.get(position).getSche_notice() == 0) {
//            Toast.makeText(context, "d-day가 3일밖에 남지 않은 일정이 있어요!", Toast.LENGTH_SHORT).show();


            DisplayMetrics dm = context.getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
            int width = dm.widthPixels; //디바이스 화면 너비
            int height = dm.heightPixels; //디바이스 화면 높이

            //로딩이미지 gif 형식
            loadingDialog = new DdayDialog(context);
            WindowManager.LayoutParams wm = loadingDialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
            wm.copyFrom(loadingDialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
            wm.width = (int) (width * 0.9);  //화면 너비의 절반
            wm.height = (int) (height * 0.4);

            loadingDialog.show();

            //최초에 접속시 한 번만 다이얼로그 표현 -> 문제 있음 , 일정을 삭제하게 되면 알림 on상태에 있던 모든 것들이 알림 off가 됨
//            list.get(i).setSche_notice(1);
        }
//        }


        //캘린더 리스트에서 일정 삭제
        h.binding.imgvScheDelete.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("일정삭제");
            alert.setMessage("정말로 삭제하시겠습니까?");

            alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CommonConn conn = new CommonConn(context, "sche_delete");
                    conn.addParamMap("sche_no", list.get(position).getSche_no());
                    list.remove(position);
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

        if(0<list.size()){
            binding.tvScheNone.setVisibility(View.GONE);
            if (list.size()>=3){
                return 3;
            }else{
                return list.size();
            }
        } else{
            binding.tvScheNone.setVisibility(View.VISIBLE);
            return list.size();
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
