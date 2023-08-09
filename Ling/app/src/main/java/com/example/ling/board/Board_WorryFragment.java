package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.FragmentBoardFreeBinding;
import com.example.ling.databinding.FragmentBoardWorryBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Board_WorryFragment extends Fragment {

    FragmentBoardWorryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardWorryBinding.inflate(inflater, container, false);
        select();


        return binding.getRoot();
    }
    public void select(){
        CommonConn conn = new CommonConn(getContext(), "board.worryselect");
        conn.onExcute((isResult, data) -> {
            ArrayList<BoardVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<BoardVO>>(){}.getType());

            Board_WorryAdapter adapter = new Board_WorryAdapter(list);
            binding.recvWorry.setAdapter(adapter);
            binding.recvWorry.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}