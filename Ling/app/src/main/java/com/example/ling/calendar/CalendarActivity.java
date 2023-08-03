package com.example.ling.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ling.MainActivity;
import com.example.ling.databinding.ActivityCalendarBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    ActivityCalendarBinding binding;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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




        Date date = new Date();
        String time = mFormat.format(date);
        binding.tvCalendarNow.setText(time);


        SimpleDateFormat anniversary = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = anniversary.parse("2023-07-23");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        Calendar cal4 = Calendar.getInstance();
        cal1.setTime(date); // 시간 설정
        cal2.setTime(date);
        cal3.setTime(date);
        cal4.setTime(date);
        cal1.add(Calendar.DATE, 100); // 일 연산
        cal2.add(Calendar.DATE, 200); // 일 연산
        cal3.add(Calendar.DATE, 300); // 일 연산
        cal4.add(Calendar.DATE, 400); // 일 연산


        String hundred = anniversary.format(cal1.getTime());
        String two_hundred = anniversary.format(cal2.getTime());
        String three_hundred = anniversary.format(cal3.getTime());
        String first_anniversary = anniversary.format(cal4.getTime());
        binding.tv100day.setText(hundred);
        binding.tv200day.setText(two_hundred);
        binding.tv300day.setText(three_hundred);
        binding.tv365day.setText(first_anniversary);
    }
}