package com.example.ling.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCalendarBinding;
import com.example.ling.databinding.ActivityCalendarFuncBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class CalendarFuncActivity extends AppCompatActivity {

    ActivityCalendarFuncBinding binding;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarFuncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<EventDay> events = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.parseColor("#595959"));
        }


        select();

        com.applandeo.materialcalendarview.CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);


        calendarView.setEvents(events);

        //체크할 수 없는 날짜 메소드 호출
//        calendarView.setDisabledDays(getDisabledDays());


    }


        //체크할 수 없는 날짜 생성
//    private List<Calendar> getDisabledDays() {
//        Calendar firstDisabled = DateUtils.getCalendar();
//        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);
//
//        Calendar secondDisabled = DateUtils.getCalendar();
//        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);
//
//        Calendar thirdDisabled = DateUtils.getCalendar();
//        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);
//
//        List<Calendar> calendars = new ArrayList<>();
//        calendars.add(firstDisabled);
//        calendars.add(secondDisabled);
//        calendars.add(thirdDisabled);
//        return calendars;
//    }




    public void select() {
        CommonConn conn = new CommonConn(this, "sche_list");
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {
            ArrayList<ScheAddVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ScheAddVO>>(){}.getType());

            // CalendarView에 표시할 이벤트 리스트 생성
            List<EventDay> events = new ArrayList<>();

            Calendar today = Calendar.getInstance();
            Drawable todayDrawable = DrawableUtils.getToday(this);
            events.add(new EventDay(today, todayDrawable));

            for (ScheAddVO item : list) {
                // 이벤트 날짜 가져오기 (예시로 item.getSche_date() 가 날짜 데이터라 가정)
                Calendar eventDate = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                try {
                    Date date = sdf.parse(item.getSche_date());
                    eventDate.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 이벤트 날짜에 원하는 Drawable 적용
                Drawable eventDrawable = DrawableUtils.schedule(this);

                // 이벤트 추가
                Calendar calendar = Calendar.getInstance();
                events.add(new EventDay(eventDate, eventDrawable));
                events.add(new EventDay(calendar, DrawableUtils.getToday(this)));

            }

            // CalendarView에 이벤트 설정
            com.applandeo.materialcalendarview.CalendarView calendarView = findViewById(R.id.calendarView);
            calendarView.setEvents(events);


            calendarView.setOnDayClickListener(eventDay -> {

                // 클릭한 날짜의 일정을 필터링하여 RecyclerView에 표시
                Calendar clickedDate = eventDay.getCalendar();
                ArrayList<ScheAddVO> filteredList = new ArrayList<>();
                for (ScheAddVO item : list) {
                    Calendar eventDate = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    try {
                        Date date = sdf.parse(item.getSche_date());
                        eventDate.setTime(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (eventDate.get(Calendar.YEAR) == clickedDate.get(Calendar.YEAR) &&
                            eventDate.get(Calendar.MONTH) == clickedDate.get(Calendar.MONTH) &&
                            eventDate.get(Calendar.DAY_OF_MONTH) == clickedDate.get(Calendar.DAY_OF_MONTH)) {
                        filteredList.add(item);
                    }
                }

                // 필터링된 일정을 RecyclerView에 표시
                MoreCalAdapter adapter = new MoreCalAdapter(filteredList, this);
                binding.recvScheduleInfo.setAdapter(adapter);
                binding.recvScheduleInfo.setLayoutManager(new LinearLayoutManager(this));
            });
        });
    }




}