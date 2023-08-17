package com.example.ling.date.restaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentFestInfoBinding;
import com.example.ling.databinding.FragmentRestInfoBinding;
import com.squareup.picasso.Picasso;

public class RestInfoFragment extends Fragment {

    FragmentRestInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            String imageUrl=bundle.getString("img");
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgv);
            binding.tvName.setText(bundle.getString("name"));
            binding.tvTel.setText("전화번호 : " + bundle.getString("tel"));
            binding.tvTime.setText("영업시간 : " + bundle.getString("open") + " ~ " + bundle.getString("end"));
            binding.tvAddress.setText("주소 : " + bundle.getString("address"));
        }

        return binding.getRoot();
    }
}