package com.example.ling.date.festival;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ling.databinding.FragmentFestInfoBinding;

public class FestInfoFragment extends Fragment {

    FragmentFestInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFestInfoBinding.inflate(inflater, container, false);

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