package com.example.ling.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCalendarAddBinding;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAddActivity extends AppCompatActivity {

    ActivityCalendarAddBinding binding;

    Calendar myCalendar = Calendar.getInstance();

    ArrayList<Spinner> list = new ArrayList<>();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private android.widget.Spinner spinner;
    private SpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarAddBinding.inflate(getLayoutInflater());
        binding.imgvCalendarCancel.setOnClickListener(v -> {
            finish();
        });

        binding.tvCalendarSave.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarAddActivity.this, CalendarActivity.class);
            if(binding.edtCalendarTitle.getText().toString().length()==0) {
                Toast.makeText(getApplicationContext(), "일정의 제목을 입력해주세요.", Toast.LENGTH_LONG).show();
            }else if(binding.tvCalendarSche.getText().toString().length()==0){
                Toast.makeText(getApplicationContext(), "날짜를 선택해주세요.", Toast.LENGTH_LONG).show();
            }else{
                insert();
                startActivity(intent);
            }



        });



        Spinner spinner_default = new Spinner();
        spinner_default.setName("유형선택");
        spinner_default.setSpinnerImg(R.drawable.spinner_default);
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



        binding.imgvCalendarCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CalendarAddActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        }

        );

        setContentView(binding.getRoot());
    }

    private void updateLabel(){
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        binding.tvCalendarSche.setText(sdf.format(myCalendar.getTime()));

    }

    public void insert(){
        CommonConn conn = new CommonConn(this, "sche_insert");

        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
        conn.addParamMap("sche_title", binding.edtCalendarTitle.getText().toString());
        conn.addParamMap("sche_date", binding.tvCalendarSche.getText().toString());


        if(binding.pushCheck.isChecked()){
            binding.pushCheck.setChecked((1 != 0));
            conn.addParamMap("sche_notice", 0);
        }else{
            conn.addParamMap("sche_notice", 1);
        }



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                String selectedSpinner = parent.getItemAtPosition(i).toString();
                if(selectedSpinner.equals("결혼기념일")){
                    conn.addParamMap("sche_typecode", "wedding");
                }else if(selectedSpinner.equals("생일")){
                    conn.addParamMap("sche_typecode", "birth");
                }else if(selectedSpinner.equals("출산예정일")){
                    conn.addParamMap("sche_typecode", "childbirth");
                }else if(selectedSpinner.equals("커플여행")){
                    conn.addParamMap("sche_typecode", "travel");
                }else{
                    conn.addParamMap("sche_typecode", "default");
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        conn.onExcute((isResult, data) ->  {

        });

    }


}