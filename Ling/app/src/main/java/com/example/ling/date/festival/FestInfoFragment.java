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
            binding.tvTime.setText(bundle.get("open") + " ~ " + bundle.getString("end"));
            binding.tvAddress.setText(bundle.getString("address"));
            binding.tvIntro.setText(bundle.getString("intro"));
        }

        return binding.getRoot();


    }
}