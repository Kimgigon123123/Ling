package com.example.ling.join;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.MainActivity;
import com.example.ling.databinding.FragmentJoinCompleteBinding;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;



public class JoinCompleteFragment extends Fragment {

    FragmentJoinCompleteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJoinCompleteBinding.inflate(inflater, container, false);

        new Handler().postDelayed(()->{
            binding.viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, binding.viewKonfetti.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);


        } , 1000);


        binding.btnGoHome.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

        });



        return binding.getRoot();

    }
}