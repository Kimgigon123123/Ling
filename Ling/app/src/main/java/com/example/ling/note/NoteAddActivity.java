package com.example.ling.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityNoteAddBinding;
import com.example.ling.databinding.ActivityNoteBinding;

public class NoteAddActivity extends AppCompatActivity {


    ActivityNoteAddBinding binding;

    String selectedValue = ""; // 선택된 값 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNoteAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.radioButton1.setChecked(true);

        selectedValue = "C";


        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button1) {
                    selectedValue = "C"; // 라디오 1이 선택된 경우
                } else if (checkedId == R.id.radio_button2) {
                    selectedValue = "P"; // 라디오 2가 선택된 경우
                }
            }
        });


        binding.btnComplete.setOnClickListener(v->{
            CommonConn conn = new CommonConn(this, "note_insert");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
            conn.addParamMap("content",binding.edtContent.getText().toString());
            conn.addParamMap("view_range",selectedValue);
            conn.onExcute((isResult, data) -> {
                finish();
                Toast.makeText(this, "작성완료", Toast.LENGTH_SHORT).show();

            });
        });

    }
}