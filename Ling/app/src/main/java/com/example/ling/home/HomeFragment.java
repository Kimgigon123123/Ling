package com.example.ling.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentHomeBinding;
import com.example.ling.store.StoreEtcFragment;
import com.example.ling.store.myinfo.StoreMyinfoVO;
import com.example.ling.testchat.TestChatFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    String couple_num;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CommonConn conn = new CommonConn(getContext(),"select_couple_info");
        conn.addParamMap("id", CommonVar.loginInfo.getId());

        conn.onExcute((isResult, data) -> {
            ArrayList<MainVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<MainVO>>() {}.getType());

            binding.tvMid.setText(list.get(0).mname);
            binding.tvFid.setText(list.get(0).fname);
            binding.tvDay.setText("사귄지 "+list.get(0).day+"일"+"커플번호는 "+list.get(0).couple_num);
            couple_num=list.get(0).couple_num;

        });



        binding = FragmentHomeBinding.inflate(inflater, container, false);

        Glide.with(this).load("http://192.168.0.38/hanul/img//andimg.jpg").into(binding.imgvManProfile);
        binding.imgvPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PhotoActivity.class);
            startActivity(intent);
        });

        binding.imgvCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CalendarActivity.class);
            startActivity(intent);
        });

        binding.imgvManProfile.setOnClickListener(v -> {
//            showDialog();
        });

        binding.imgvLocTracking.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LocTrackingActivity.class);
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

        //김기곤 test chat
        binding.tvTestChat.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager(); // getActivity() 대신 requireActivity()를 사용합니다.
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            TestChatFragment testChatFragment = new TestChatFragment(couple_num); // TestChatFragment로 교체할 프래그먼트 인스턴스 생성
            transaction.replace(R.id.container, testChatFragment); // R.id.container는 프래그먼트가 표시될 레이아웃의 ID입니다.

            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
            transaction.commit();
        });




        return binding.getRoot();


    }

    public void showDialog(){
        String[] dialog_item = {"갤러리", "카메라", "기본이미지"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("사진 업로드 방식");
        builder.setSingleChoiceItems(dialog_item, -1, (dialog, i) -> {
            if(dialog_item[i].equals("갤러리")){
                //갤러리 로직
//                showGallery();
            }else if(dialog_item[i].equals("카메라")){
                //카메라 로직
//                showCamera();
            }else if(dialog_item[i].equals("기본이미지")){

            }
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}