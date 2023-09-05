package com.example.ling.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ling.MainActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCalendarBinding;
import com.example.ling.home.MainVO;
import com.example.ling.photo.FolderVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    ActivityCalendarBinding binding;

    Date date = new Date();

    Window window;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.parseColor("#FFDEEBBD"));
        }

        select();

        binding.imgvCalendarHome.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(intent);
        });

        binding.imgvCalendarAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, CalendarAddActivity.class);
            startActivity(intent);
        });

        binding.lnMoreCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, CalendarFuncActivity.class);
            startActivity(intent);
        });





        String time = mFormat.format(date);
        binding.tvCalendarNow.setText(time);

        //커플별로 create_date를 가져와야 각자의 기념일을 계산할 수 있음
        CommonConn conn = new CommonConn(this, "sche_dday");

        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());

        conn.onExcute((isResult, data) -> {

            ArrayList<ScheAddVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ScheAddVO>>() {
            }.getType());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateString = list.get(0).getCreate_date()+"";
            Date date = inputFormat.parse(createDateString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int currentYear = cal.get(Calendar.YEAR); // 현재 년도
            int nextYear = currentYear + 1; // 다음 년도

            cal.set(nextYear, Calendar.MARCH, 14); // 다음 년도 3월 14일 설정
            Date nextYearMarch14 = cal.getTime();

            cal.set(nextYear, Calendar.FEBRUARY, 14); // 다음 년도 2월 14일 설정
            Date nextYearFeb14 = cal.getTime();

            cal.set(nextYear, Calendar.APRIL, 14); // 다음 년도 4월 14일 설정
            Date nextYearApr14 = cal.getTime();

            cal.set(nextYear, Calendar.MAY, 14); // 다음 년도 5월 14일 설정
            Date nextYearMay14 = cal.getTime();

            cal.set(nextYear, Calendar.JUNE, 14); // 다음 년도 6월 14일 설정
            Date nextYearJune14 = cal.getTime();

            cal.set(nextYear, Calendar.JULY, 14); // 다음 년도 7월 14일 설정
            Date nextYearJuly14 = cal.getTime();


            cal.set(nextYear, Calendar.OCTOBER, 14); // 다음 년도 10월 14일 설정
            Date nextYearOct14 = cal.getTime();

            cal.set(nextYear, Calendar.DECEMBER, 14); // 다음 년도 12월 14일 설정
            Date nextYearDec14 = cal.getTime();



            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();


            cal.setTime(date);

            cal.add(Calendar.DATE, 100); // 100일 후
            Date date100 = cal.getTime();



            if (date100.after(today)) {
                String formattedDate100 = outputFormat.format(date100);
                binding.tv100day.setText(formattedDate100);
            }else  if (today.before(nextYearFeb14)) {
                String formattedDate100 = outputFormat.format(nextYearFeb14);
                binding.tv100day.setText(formattedDate100);
                binding.tv100.setText("발렌타인데이"); // 100일이 지난 경우 텍스트 변경
            }
                else {
                // 다음 년도 4월 14일 계산
                cal.set(Calendar.MONTH, Calendar.JUNE);
                cal.set(Calendar.DAY_OF_MONTH, 14);
                cal.add(Calendar.YEAR, 1);
                nextYearFeb14 = cal.getTime();

                String formattedDateJune14 = outputFormat.format(nextYearFeb14);
                binding.tv100day.setText(formattedDateJune14);
                binding.tv100.setText("키스데이"); // 100일과 다음 년도 2월 14일이 모두 지난 경우 텍스트 변경
            }



            cal.setTime(date); // 초기화

            cal.add(Calendar.DATE, 200); // 200일 후
            Date date200 = cal.getTime();

            if (date200.after(today)) {
                String formattedDate200 = outputFormat.format(date200);
                binding.tv200day.setText(formattedDate200);
            } else if (today.before(nextYearMarch14)) {
                String formattedDate200 = outputFormat.format(nextYearMarch14);
                binding.tv200day.setText(formattedDate200);
                binding.tv200.setText("화이트데이"); // 200일이 지난 경우 텍스트 변경
            } else {
                // 다음 년도 4월 14일 계산
                cal.set(Calendar.MONTH, Calendar.JULY);
                cal.set(Calendar.DAY_OF_MONTH, 14);
                cal.add(Calendar.YEAR, 1);
                nextYearJuly14 = cal.getTime();

                String formattedDateJuly14 = outputFormat.format(nextYearJuly14);
                binding.tv200day.setText(formattedDateJuly14);
                binding.tv200.setText("실버데이"); // 100일과 다음 년도 3월 14일이 모두 지난 경우 텍스트 변경
            }


            cal.setTime(date); // 초기화

            cal.add(Calendar.DATE, 300); // 300일 후
            Date date300 = cal.getTime();


            if (date300.after(today)) {
                String formattedDate300 = outputFormat.format(date300);
                binding.tv300day.setText(formattedDate300);
            }else if (today.before(nextYearApr14)){
                String formattedDate300 = outputFormat.format(nextYearApr14);
                binding.tv300day.setText(formattedDate300);
                binding.tv300.setText("블랙데이"); // 300일이 지난 경우 텍스트 변경
            }else {
                // 다음 년도 4월 14일 계산
                cal.set(Calendar.MONTH, Calendar.OCTOBER);
                cal.set(Calendar.DAY_OF_MONTH, 14);
                cal.add(Calendar.YEAR, 1);
                nextYearOct14 = cal.getTime();

                String formattedDateOct14 = outputFormat.format(nextYearOct14);
                binding.tv300day.setText(formattedDateOct14);
                binding.tv300.setText("와인데이"); // 100일과 다음 년도 4월 14일이 모두 지난 경우 텍스트 변경
            }

            cal.setTime(date); // 초기화

            cal.add(Calendar.DATE, 365); // 365일 후
            Date date365 = cal.getTime();
            if (date365.after(today)) {
                String formattedDate365 = outputFormat.format(date365);
                binding.tv365day.setText(formattedDate365);
            }else if (today.before(nextYearMay14)){
                String formattedDate365 = outputFormat.format(nextYearMay14);
                binding.tv365day.setText(formattedDate365);
                binding.tv365.setText("로즈데이"); // 300일이 지난 경우 텍스트 변경
            }else {
                // 다음 년도 4월 14일 계산
                cal.set(Calendar.MONTH, Calendar.DECEMBER);
                cal.set(Calendar.DAY_OF_MONTH, 14);
                cal.add(Calendar.YEAR, 1);
                nextYearDec14 = cal.getTime();

                String formattedDateDec14 = outputFormat.format(nextYearDec14);
                binding.tv365day.setText(formattedDateDec14);
                binding.tv365.setText("허그데이"); // 100일과 다음 년도 5월 14일이 모두 지난 경우 텍스트 변경
            }


        });
        }


    @Override
    protected void onRestart() {
        super.onRestart();
        select();
    }

    public void select(){
        CommonConn conn = new CommonConn(this, "plus_List");
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());

        conn.onExcute((isResult, data) -> {
            ArrayList<ScheAddVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ScheAddVO>>(){}.getType());
//            Log.d("리스트사이즈", "select: " + list.size());
            //if문으로 list의 사이즈처리 해야함.

            CalendarAdapter adapter = new CalendarAdapter(list,this,binding);



            binding.recvSchedule.setAdapter(adapter);
            binding.recvSchedule.setLayoutManager(new LinearLayoutManager(this));

        });
    }



}