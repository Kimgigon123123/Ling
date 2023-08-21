package com.example.ling.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.ling.databinding.ActivityCalendarFuncBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CalendarFuncActivity extends AppCompatActivity {

    ActivityCalendarFuncBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_func);
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(this, "Today")));
        events.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(this, "Today")));





        com.applandeo.materialcalendarview.CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);


        calendarView.setEvents(events);

        calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());


    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }


    public void select(){
        CommonConn conn = new CommonConn(this, "sche_list");
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {
//            ArrayList<ScheAddVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ScheAddVO>>(){}.getType());
////            Log.d("리스트사이즈", "select: " + list.size());
//            //if문으로 list의 사이즈처리 해야함.
//            CalendarAdapter adapter = new CalendarAdapter(list,this);
//
//            binding.recvSchedule.setAdapter(adapter);
//            binding.recvSchedule.setLayoutManager(new LinearLayoutManager(this));
        });
    }






}