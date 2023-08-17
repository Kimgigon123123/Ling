package com.example.ling.date.dibs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentDibsInfoBinding;
import com.squareup.picasso.Picasso;

public class DibsInfoFragment extends Fragment {

    FragmentDibsInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDibsInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle != null) {
            if(bundle.getString("code").equals("TO")) {
                String imageUrl=bundle.getString("img");
                Picasso.get()
                        .load(imageUrl)
                        .into(binding.imgv);
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setVisibility(View.GONE);
                binding.tvAddress.setText(bundle.getString("address"));
                binding.tvIntro.setText(bundle.getString("intro"));
            } else if (bundle.getString("code").equals("RE")) {
                String imageUrl=bundle.getString("img");
                Picasso.get()
                        .load(imageUrl)
                        .into(binding.imgv);
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setText(bundle.getString("open") + " ~ " + bundle.getString("end"));
                binding.tvAddress.setText(bundle.getString("address"));
            } else {
                String imageUrl=bundle.getString("img");
                Picasso.get()
                        .load(imageUrl)
                        .into(binding.imgv);
                binding.tvName.setText(bundle.getString("name"));
                binding.tvTime.setText(bundle.get("open") + " ~ " + bundle.getString("end"));
                binding.tvAddress.setText(bundle.getString("address"));
                binding.tvIntro.setText(bundle.getString("intro"));
            }
        }

        return binding.getRoot();
    }
}