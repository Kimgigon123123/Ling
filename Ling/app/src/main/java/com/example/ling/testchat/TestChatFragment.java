package com.example.ling.testchat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentTestChatBinding;


public class TestChatFragment extends Fragment {


    FragmentTestChatBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentTestChatBinding.inflate(inflater,container,false);
      binding.recvChat.setAdapter(new TestChatAdapter(getContext()));
      binding.recvChat.setLayoutManager(new LinearLayoutManager(getContext()));

      return binding.getRoot();
    }
}