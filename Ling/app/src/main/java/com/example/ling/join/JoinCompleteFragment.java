package com.example.ling.join;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentInsertMateBinding;
import com.example.ling.databinding.FragmentJoinCompleteBinding;
import com.example.ling.login.Ling_MemberVO;
import com.example.ling.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;



public class JoinCompleteFragment extends Fragment {

    FragmentJoinCompleteBinding binding;
    CommonVar commonVar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinCompleteBinding.inflate(inflater, container, false);

        binding.tvMid.setText(CommonVar.idMap.get("mid"));
        binding.tvFid.setText(    CommonVar.idMap.get("fid"));

        binding.btnGoHome.setOnClickListener(v->{

            CommonConn conn = new CommonConn(getActivity(), "login");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("pw", CommonVar.loginInfo.getPw());
            conn.onExcute((isResult, data) -> {
            if (isResult) {
                CommonVar.loginInfo = new Gson().fromJson(data, Ling_MemberVO.class);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);


            }
        });

        });


        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.tvMid.setText(CommonVar.idMap.get("mid"));
        binding.tvFid.setText(    CommonVar.idMap.get("fid"));


        new Handler().postDelayed(()->{
            binding.viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, binding.viewKonfetti.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);


        } , 1000);
    }
}