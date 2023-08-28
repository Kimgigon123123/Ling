package com.example.ling.capsule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityCapsuleAddBinding;
import com.example.ling.databinding.ActivityStorePurchaseBinding;
import com.example.ling.store.ImagePagerAdapter;
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
    String value = "B";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCapsuleAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       CapsuleAddAdapter capsuleAddAdapter = new CapsuleAddAdapter(this);
        binding.viewPager.setAdapter(capsuleAddAdapter);

        binding.btnCancel.setOnClickListener(v->{
            finish();
        });

        // 왼쪽 버튼 클릭 시
        binding.btnLeft.setOnClickListener(v -> {
            int currentItem = binding.viewPager.getCurrentItem();
            if (currentItem > 0) {
                binding.viewPager.setCurrentItem(currentItem - 1, true);
            } else {
                // 최대값일 때 처음으로 돌아감
                binding.viewPager.setCurrentItem(capsuleAddAdapter.getCount() - 1, true);
            }
        });

        // 오른쪽 버튼 클릭 시
        binding.btnRight.setOnClickListener(v -> {
            int currentItem = binding.viewPager.getCurrentItem();
            if (currentItem < capsuleAddAdapter.getCount() - 1) {
                binding.viewPager.setCurrentItem(currentItem + 1, true);
            } else {
                // 최대값일 때 처음으로 돌아감
                binding.viewPager.setCurrentItem(0, true);
            }
        });

        // ViewPager 페이지 변경 이벤트 리스너 설정
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 페이지 스크롤 중 호출됨
            }

            @Override
            public void onPageSelected(int position) {
                // 페이지 선택 시 호출됨

                switch (position) {
                    case 0:
                        value = "B";

                        break;
                    case 1:
                        value = "R";

                        break;
                    case 2:
                        value = "Y";

                        break;
                    // ... (더 많은 케이스 추가 가능)
                }
                // 받은 값(value)를 원하는 곳에 활용
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 페이지 스크롤 상태 변경 시 호출됨
            }
        });



        binding.edtTitle.setHint("연인에게 보내는 타임캡슐");

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
            conn.addParamMap("color",value);

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