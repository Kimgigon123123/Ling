package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentBoardNoticeBinding;
import com.example.ling.databinding.FragmentContentBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ContentFragment extends Fragment {

    FragmentContentBinding binding;
    String board_no;
    String board_cd;

    public ContentFragment(String board_no, String board_cd) {
        this.board_no = board_no;
        this.board_cd = board_cd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);
        if (board_cd.equals("NOTICE")||board_cd.equals("PLAY")) {
            NoticeContentselect();
        } else {
            UserContentselect();
        }
        return binding.getRoot();
    }

    public void NoticeContentselect() {
        CommonConn conn = new CommonConn(getContext(), "board.content");
        conn.addParamMap("id", board_no);
        conn.addParamMap("board_cd", board_cd);
        conn.onExcute((isResult, data) -> {
            BoardVO boardvo = new Gson().fromJson(data, new TypeToken<BoardVO>() {
            }.getType());

            Board_Notice_ContentAdapter adapter = new Board_Notice_ContentAdapter(boardvo);
            binding.recvContent.setAdapter(adapter);
            binding.recvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }


    //
    public void UserContentselect() {
        CommonConn conn = new CommonConn(getContext(), "board.usercontent");
        conn.addParamMap("id", board_no);
        conn.addParamMap("board_cd", board_cd);
        conn.onExcute((isResult, data) -> {
            BoardVO boardvo = new Gson().fromJson(data, new TypeToken<BoardVO>() {
            }.getType());

            Board_User_ContentAdapter adapter = new Board_User_ContentAdapter(boardvo);
            binding.recvContent.setAdapter(adapter);
            binding.recvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}