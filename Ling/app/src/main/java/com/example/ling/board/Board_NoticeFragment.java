package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentBoardNoticeBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Board_NoticeFragment extends Fragment {


    FragmentBoardNoticeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardNoticeBinding.inflate(inflater, container, false);
        select();


        binding.boardSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    // 엔터 키가 눌렸을 때 클릭 이벤트를 처리
                    select();
                    v.performClick();

                    return true;
                }
                return false;
            }
        });

        return binding.getRoot();
    }

    public void select(){
        CommonConn conn = new CommonConn(getContext(), "board.noticeselect");
        conn.addParamMap("board_cd" , "NOTICE");
        conn.addParamMap("keyword", binding.boardSearch.getText().toString());
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            Board_NoticeAdapter adapter = new Board_NoticeAdapter(list);
            binding.recvNotice.setAdapter(adapter);
            binding.recvNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

}