package com.example.ling.date.dibs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentDibsInfoBinding;

public class DibsInfoFragment extends Fragment {

    FragmentDibsInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDibsInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle != null) {
            if(bundle.getString("code").equals("TO")) {
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setVisibility(View.GONE);
                binding.tvAddress.setText(bundle.getString("address"));
                binding.tvIntro.setText(bundle.getString("intro"));
            } else if (bundle.getString("code").equals("RE")) {
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setText("09:00" + " ~ " + "21:00");
                binding.tvAddress.setText(bundle.getString("address"));
            } else {
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setText(bundle.get("open") + " ~ " + bundle.getString("end"));
                binding.tvAddress.setText(bundle.getString("address"));
                binding.tvIntro.setText(bundle.getString("intro"));
            }
        }

        return binding.getRoot();
    }
}