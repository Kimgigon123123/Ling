package com.example.ling.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.databinding.FragmentHomeBinding;
import com.ramotion.fluidslider.FluidSlider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        showFluiderSlide();


        return binding.getRoot();


    }


    Handler handler = new Handler();
    public void showFluiderSlide(){

        int max = 45 ;
        int min = 10 ;
        int total = max - min ;

        FluidSlider slider = binding.fluider;

        slider.setPositionListener(pos -> {
            slider.setBubbleText(min + ( total * pos) + "");
            return null;
        });





        new Thread(new Runnable() {

          //  int i = 0;
            float progressStatus = 0.1f;

            public void run() {
                while (progressStatus < 1f) {
                    progressStatus += doWork();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            slider.setPosition(0.1f);
                            //bar.setProgress(progressStatus);
                           // i++;
                        }
                    });
                }
            }
            private float doWork() {

                return 0.1f;
            }

        }).start();

        slider.setStartText("0");
        slider.setEndText("100");

        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Log.d("D", "setBeginTrackingListener");
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Log.d("D", "setEndTrackingListener");
                return Unit.INSTANCE;
            }
        });

// Or Java 8 lambda
        slider.setPositionListener(pos -> {
            final String value = String.valueOf( (int)((1 - pos) * 100) );
            slider.setBubbleText(value);
            return Unit.INSTANCE;
        });
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