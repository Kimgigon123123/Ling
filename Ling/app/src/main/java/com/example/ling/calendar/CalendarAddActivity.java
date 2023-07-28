package com.example.ling.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ling.R;
import com.example.ling.databinding.ActivityCalendarAddBinding;

import java.util.ArrayList;

public class CalendarAddActivity extends AppCompatActivity {
    
    ActivityCalendarAddBinding binding;
    private android.widget.Spinner spinner;
    private SpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarAddBinding.inflate(getLayoutInflater());
        ArrayList<Spinner> list = new ArrayList<>();

        Spinner spinner_default = new Spinner();
        spinner_default.setName("유형선택");
        list.add(spinner_default);

        Spinner wedding = new Spinner();
        wedding.setName("결혼기념일");
        wedding.setSpinnerImg(R.drawable.wedding);
        list.add(wedding);

        Spinner birth = new Spinner();
        birth.setName("생일");
        birth.setSpinnerImg(R.drawable.birth);
        list.add(birth);

        Spinner childBirth = new Spinner();
        childBirth.setName("출산예정일");
        childBirth.setSpinnerImg(R.drawable.childbirth);
        list.add(childBirth);

        Spinner couple_travel = new Spinner();
        couple_travel.setName("커플여행");
        couple_travel.setSpinnerImg(R.drawable.couple_travel);
        list.add(couple_travel);

        spinner = binding.spinner;
        adapter = new SpinnerAdapter(CalendarAddActivity.this, list);
        spinner.setAdapter(adapter);
        setContentView(binding.getRoot());
    }
}