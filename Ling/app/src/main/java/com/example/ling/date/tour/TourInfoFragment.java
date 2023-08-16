package com.example.ling.date.tour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentFestInfoBinding;
import com.example.ling.databinding.FragmentTourInfoBinding;
import com.squareup.picasso.Picasso;

public class TourInfoFragment extends Fragment {

    FragmentTourInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTourInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            String imageUrl=bundle.getString("img");
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgv);
            //binding.imgv.setImageResource(bundle.getString("img"));
            binding.tvName.setText(bundle.getString("name"));
            binding.tvTime.setVisibility(View.GONE);
            binding.tvAddress.setText(bundle.getString("address"));
            binding.tvIntro.setText(bundle.getString("intro"));
        }

        return binding.getRoot();
    }
}