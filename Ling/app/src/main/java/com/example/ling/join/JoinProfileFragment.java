package com.example.ling.join;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentJoinProfileBinding;
import com.example.ling.login.Ling_MemberVO;
import com.google.gson.Gson;

import java.util.Calendar;


public class JoinProfileFragment extends Fragment {

    FragmentJoinProfileBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinProfileBinding.inflate(inflater, container, false);



        binding.joinBirth.setOnClickListener(v->{
            DatePickerDialog dialog = new DatePickerDialog(getContext());
            Calendar calendar = Calendar.getInstance();
            long currentDate = calendar.getTimeInMillis();
            calendar.add(Calendar.YEAR, -10);

            dialog.getDatePicker().updateDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            dialog.getDatePicker().setMaxDate(currentDate);
            dialog.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                   binding.joinBirth.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
               }
            });
            dialog.show();

        });

        binding.btnNext.setOnClickListener(v->{
            if((binding.joinName.getText().toString().length()<1
                    || binding.joinPhone.getText().toString().length()<1
                    || binding.joinEmail.getText().toString().length()<1)
                    && (binding.radioMen.isChecked() || binding.radioWomen.isChecked())
                    && binding.joinBirth.getText().toString().length()<1){
                Toast.makeText(getActivity(), "빈칸없이 입력해주세요.", Toast.LENGTH_SHORT).show();

            }
                CommonConn conn = new CommonConn(getActivity(), "register");
                CommonVar.loginInfo.setName(binding.joinName.getText().toString());
                if (binding.radioMen.isChecked()) {
                    CommonVar.loginInfo.setGender("남");
                } else if (binding.radioWomen.isChecked()) {
                    CommonVar.loginInfo.setGender("여");
                }
                CommonVar.loginInfo.setBirth(binding.joinBirth.getText().toString());
                CommonVar.loginInfo.setPhone(binding.joinPhone.getText().toString());
                CommonVar.loginInfo.setEmail(binding.joinEmail.getText().toString());

                conn.addParamMap("dto", new Gson().toJson(CommonVar.loginInfo));

                conn.onExcute((isResult, data) -> {
                    if (isResult) {
                        if (data.equals("1")) {
                            ((JoinActivity) getActivity()).changeTab(2);
                        } else {
                            Toast.makeText(getActivity(), "아이디 중복확인 혹인 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        });



        return binding.getRoot();
    }

}