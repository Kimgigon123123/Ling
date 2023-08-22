package com.example.ling.capsule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCapsuleAddBinding;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.testchat.ChatVO;
import com.example.ling.testchat.TestChatAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        binding.edtTitle.setHint("커플이름님에게 보내는 타임캡슐");

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String todayDate = dateFormat.format(calendar.getTime());
        binding.tvDate.setText(todayDate);

        binding.btnDate.setOnClickListener(v->{
            swhowDatePickerDialog();
        });

        binding.btnOk.setOnClickListener(v->{
            CommonConn conn = new CommonConn(this, "capsule_insert");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
            conn.addParamMap("tc_title",binding.edtTitle.getText().toString());
            conn.addParamMap("tc_content",binding.edtContent.getText().toString());
            conn.addParamMap("tc_date",binding.tvDate.getText().toString());

            conn.onExcute((isResult, data) -> {

                Toast.makeText(this, "작성완료", Toast.LENGTH_SHORT).show();
                finish();
            });
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