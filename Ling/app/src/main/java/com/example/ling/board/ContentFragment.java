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
    String board_no ;

    public ContentFragment(String board_no) {
        this.board_no = board_no;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater,container,false);
        NoticeContentselect();
        return binding.getRoot();
    }
    public void NoticeContentselect(){
        CommonConn conn = new CommonConn(getContext(), "board.content");
        conn.addParamMap("id",board_no );
        conn.onExcute((isResult, data) -> {
            BoardVO boardvo = new Gson().fromJson(data, new TypeToken<BoardVO>(){}.getType());

            Board_Notice_ContentAdapter adapter = new Board_Notice_ContentAdapter(boardvo);
            binding.recvNoticeContent.setAdapter(adapter);
            binding.recvNoticeContent.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}