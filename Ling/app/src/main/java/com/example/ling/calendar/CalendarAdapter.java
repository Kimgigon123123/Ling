package com.example.ling.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {


    ArrayList<ScheAddVO> list;
    static DdayDialog loadingDialog;

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

        //캘린더 리스트에서 날짜 조회(형식 변환 후 날짜가 불일치 문제!!!)
//        String calDate = list.get(i).getSche_date()+"";
//        SimpleDateFormat newDate = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
//        SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
//
//        try {
//            Date newFormat = newDate.parse(calDate);
//            String tv_date = oldDate.format(newFormat);

//            h.binding.tvCalendarDate.setText(tv_date);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        h.binding.tvCalendarDate.setText(list.get(i).getSche_date());


        //캘린더 리스트에서 알림 여부 조회
        h.binding.tvCalendarTitle.setText(list.get(i).getSche_title());
        if(list.get(i).getSche_notice() != 0){
            h.binding.materialSwitch.setChecked(false);
        }else{
            h.binding.materialSwitch.setChecked(true);
        }





        //캘린더 리스트에서 d-day 조회
        h.binding.tvDday.setText("D-"+list.get(i).getD_day());

        if (list.get(i).getD_day() <= 3 && list.get(i).getSche_notice() == 0) {
//            Toast.makeText(context, "d-day가 3일밖에 남지 않은 일정이 있어요!", Toast.LENGTH_SHORT).show();


            DisplayMetrics dm = context.getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
            int width = dm.widthPixels; //디바이스 화면 너비
            int height = dm.heightPixels; //디바이스 화면 높이

            //로딩이미지 gif 형식
            loadingDialog = new DdayDialog(context);
            WindowManager.LayoutParams wm = loadingDialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
            wm.copyFrom(loadingDialog.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
            wm.width = (int)(width *0.9);  //화면 너비의 절반
            wm.height = (int)(height *0.4);

            loadingDialog.show();

            //최초에 접속시 한 번만 다이얼로그 표현 -> 문제 있음 , 일정을 삭제하게 되면 알림 on상태에 있던 모든 것들이 알림 off가 됨
            list.get(i).setSche_notice(1);
        }


        //캘린더 리스트에서 일정 삭제
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
