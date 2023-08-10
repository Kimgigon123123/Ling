package com.example.ling.date.tour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentFestInfoBinding;
import com.example.ling.databinding.FragmentTourInfoBinding;

public class TourInfoFragment extends Fragment {

    FragmentTourInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTourInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            binding.tvName.setText(bundle.getString("name"));
            binding.tvTime.setText("기간 : " + bundle.get("open") + " ~ " + bundle.getString("end"));
            binding.tvAddress.setText("주소 : " + bundle.getString("address"));
            binding.tvIntro.setText("행사내용 : " + bundle.getString("intro"));
        }

        return binding.getRoot();
    }
}