package com.example.ling.testchat;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentTestChatBinding;
import com.example.ling.home.MainVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class TestChatFragment extends Fragment {

    FragmentTestChatBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTestChatBinding.inflate(inflater, container, false);

        if (!CommonVar.loginInfo.getId().equals("admin")) {
            CommonConn conn = new CommonConn(getContext(), "select_chat");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());

            conn.onExcute((isResult, data) -> {

                ArrayList<ChatVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ChatVO>>() {
                }.getType());

                binding.recvChat.setAdapter(new TestChatAdapter(list, getContext()));
                binding.recvChat.setLayoutManager(new LinearLayoutManager(getContext()));

            });

            binding.btnChat.setOnClickListener(v -> {

                CommonConn conn2 = new CommonConn(getContext(), "insert_chat");
                conn2.addParamMap("id", CommonVar.loginInfo.getId());
                conn2.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                conn2.addParamMap("chat_content", binding.edtChat.getText().toString());

                conn2.onExcute((isResult, data) -> {

                    CommonConn conn3 = new CommonConn(getContext(), "select_chat");
                    conn3.addParamMap("id", CommonVar.loginInfo.getId());
                    conn3.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());

                    conn3.onExcute((isResult3, data3) -> {

                        ArrayList<ChatVO> list = new Gson().fromJson(data3, new TypeToken<ArrayList<ChatVO>>() {
                        }.getType());

                        binding.recvChat.setAdapter(new TestChatAdapter(list, getContext()));
                        binding.recvChat.setLayoutManager(new LinearLayoutManager(getContext()));

                    });


                });

            });


            CommonConn conn4 = new CommonConn(getContext(), "select_couple_name");
            conn4.addParamMap("id", CommonVar.loginInfo.getId());
            conn4.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());

            conn4.onExcute((isResult, data) -> {

                ArrayList<ChatVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<ChatVO>>() {
                }.getType());

                binding.tvChat.setText(list.get(0).getCouple_name() + "님과의 채팅");

            });

        }
            return binding.getRoot();
        }



}