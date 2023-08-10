package com.example.ling.date.restaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentFestInfoBinding;
import com.example.ling.databinding.FragmentRestInfoBinding;

public class RestInfoFragment extends Fragment {

    FragmentRestInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            binding.tvName.setText(bundle.getString("name"));
            binding.tvTime.setText("영업시간 : " + "09:00" + " ~ " + "21:00");
            binding.tvAddress.setText("주소 : " + bundle.getString("address"));
        }

        return binding.getRoot();
    }
}