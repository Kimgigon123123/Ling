package com.example.ling.capsule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.ActivityCapsuleAddBinding;
import com.example.ling.databinding.ActivityStorePurchaseBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CapsuleAddActivity extends AppCompatActivity {


     ActivityCapsuleAddBinding binding;

     Calendar calendar;
    SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCapsuleAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String todayDate = dateFormat.format(calendar.getTime());
        binding.tvDate.setText(todayDate);

        binding.btnDate.setOnClickListener(v->{
            swhowDatePickerDialog();
        });
    }
    private void swhowDatePickerDialog(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        binding.tvDate.setText(selectedDate);
                    }
                },
                year,month,day
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }
}