package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentBoardMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class BoardMainFragment extends Fragment {
    FragmentBoardMainBinding binding;

    BoardFragment fragment ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardMainBinding.inflate(getLayoutInflater() , container , false);
        fragment = (BoardFragment) getParentFragment();
        binding.tvNoticeMore.setOnClickListener(v->{
            fragment.changeFragment(1);
        });
        binding.tvFreeMore.setOnClickListener(v->{
            fragment.changeFragment(2);
        });
        binding.tvWorryMore.setOnClickListener(v->{
            fragment.changeFragment(3);
        });
        binding.tvPlayMore.setOnClickListener(v->{
            fragment.changeFragment(4);
        });
        Noticeselect();
        Freeselect();
        Worryselect();
        Playselect();
        return binding.getRoot();
    }

    public void Noticeselect(){
        CommonConn conn = new CommonConn(getContext(), "board.noticeselect");
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            BoardMain_NoticeAdapter adapter = new BoardMain_NoticeAdapter(list , fragment);
            binding.recvNotice.setAdapter(adapter);
            binding.recvNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
    public void Freeselect(){
        CommonConn conn = new CommonConn(getContext(), "board.freeselect");
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            BoardMain_FreeAdapter adapter = new BoardMain_FreeAdapter(list);
            binding.recvFree.setAdapter(adapter);
            binding.recvFree.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
    public void Worryselect(){
        CommonConn conn = new CommonConn(getContext(), "board.worryselect");
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            BoardMain_WorryAdapter adapter = new BoardMain_WorryAdapter(list);
            binding.recvWorry.setAdapter(adapter);
            binding.recvWorry.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
    public void Playselect(){
        CommonConn conn = new CommonConn(getContext(), "board.playselect");
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            BoardMain_PlayAdapter adapter = new BoardMain_PlayAdapter(list);
            binding.recvPlay.setAdapter(adapter);
            binding.recvPlay.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}