package com.example.ling.note;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityNoteModifyBinding;
import com.example.ling.databinding.ActivityStorePurchaseBinding;

public class NoteModifyActivity extends AppCompatActivity {

    ActivityNoteModifyBinding binding;

    String selectedValue = ""; // 선택된 값 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityNoteModifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int note_id = intent.getIntExtra("note_id",0);
        String content = intent.getStringExtra("content");
        String view_range = intent.getStringExtra("view_range");
        selectedValue = view_range;


                if (view_range .equals("C")) {

                    binding.radioButton1.setChecked(true);
                    binding.radioButton2.setEnabled(false);

                } else if (view_range .equals("P")) {

                    binding.radioButton2.setChecked(true);
                }

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


        binding.edtContent.setText(content);

        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

                binding.btnComplete.setOnClickListener(v->{


                    CommonConn conn = new CommonConn(this, "update_note");
                    conn.addParamMap("content",binding.edtContent.getText().toString());
                    conn.addParamMap("view_range",selectedValue);
                    conn.addParamMap("note_id",note_id);

                    conn.onExcute((isResult, data) -> {
                        finish();
                        Toast.makeText(this, "수정완료", Toast.LENGTH_SHORT).show();
                    });

        });
                binding.btnDelete.setOnClickListener(v->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("확인", (dialog, which) -> {
                                // 확인 버튼을 눌렀을 때 실행되는 코드
                                CommonConn conn = new CommonConn(this, "delete_note");
                                conn.addParamMap("note_id", note_id);

                                conn.onExcute((isResult, data) -> {
                                    finish();
                                    Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show();
                                });
                            })
                            .setNegativeButton("취소", (dialog, which) -> {
                                // 취소 버튼을 눌렀을 때 실행되는 코드
                                dialog.dismiss(); // 대화 상자 닫기
                            })
                            .show();
                });

    }


}