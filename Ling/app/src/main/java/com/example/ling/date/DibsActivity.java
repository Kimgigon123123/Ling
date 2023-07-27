package com.example.ling.date;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ling.databinding.ActivityDibsBinding;

public class DibsActivity extends AppCompatActivity {

    ActivityDibsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDibsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recvDibs.setAdapter(new DibsAdapter());
        binding.recvDibs.setLayoutManager(new GridLayoutManager(this, 2));

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

    }

}

// 드롭다운 메뉴
/*
*  bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = (count + 1) % 3;

                switch(count){
                    case 0:
                        base.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        base.setBackgroundColor(Color.BLUE);
                        break;
                    case 2:
                        base.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        });
* */