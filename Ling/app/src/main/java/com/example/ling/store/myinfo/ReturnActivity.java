package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.databinding.ActivityDeliveryBinding;
import com.example.ling.databinding.ActivityReturnBinding;
import com.example.ling.store.StoreCoFragment;

public class ReturnActivity extends AppCompatActivity {

    ActivityReturnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityReturnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnCancle.setOnClickListener(v->{
            finish();
        });

        binding.btnReturn.setOnClickListener(v->{
            Toast.makeText(this, "환불처리되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ReturnActivity.this, MainActivity.class);
            intent.putExtra("return","return");
            startActivity(intent);
        });

    }
}