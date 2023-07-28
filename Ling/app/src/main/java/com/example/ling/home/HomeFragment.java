package com.example.ling.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.calendar.CalendarActivity;
import com.example.ling.databinding.FragmentHomeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.imgvPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PhotoActivity.class);
            startActivity(intent);
        });

        binding.imgvCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CalendarActivity.class);
            startActivity(intent);
        });

        Date date = new Date();

        String date1 = mFormat.format(date); //날짜1
        String date2 = "2023/07/23"; //날짜2

        Date format1 = null;
        try {
            format1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date format2 = null;
        try {
            format2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
        String diffDays = String.valueOf(diffSec / (24*60*60)); //일자수 차이

        binding.loveDDay.setText(diffDays);




        return binding.getRoot();


    }
}