package com.example.ling.date.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentFestInfoBinding;
import com.squareup.picasso.Picasso;

public class DateDatailInfoFragment extends Fragment {

    FragmentFestInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFestInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        DateInfoVO vo = (DateInfoVO) bundle.getSerializable("vo");


            Picasso.get()
                    .load(vo.getDate_img())
                    .into(binding.imgv);
            binding.tvName.setText(vo.getDate_name());
            binding.tvTel.setVisibility(View.GONE);

            if(vo.getDate_category_code().equals("TO")){
                binding.tvTime.setVisibility(View.GONE);
                // binding.tvName.setText(vo.getDate_name());
                // binding.tvTime.setVisibility(View.GONE);
            }else if(vo.getDate_category_code().equals("FE")){
                binding.tvTime.setText("축제일자 : " + vo.getOpen() + " ~ " + vo.getEnd());
            }else if(vo.getDate_category_code().equals("RE")){
                binding.tvTel.setVisibility(View.VISIBLE);
                binding.tvIntro.setVisibility(View.GONE);
                binding.tvTel.setText("전화번호 : " + vo.getTel());
                binding.tvTime.setText("영업시간 : " + vo.getOpen() + " ~ " + vo.getEnd());
            }
            binding.tvAddress.setText("주소 : " + vo.getDate_address());
            binding.tvIntro.setText("소개 : " + vo.getDate_intro());

            binding.btnAdd.setOnClickListener(v -> {
                CommonConn conn = new CommonConn(getContext(), "date_insertdibs");
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.addParamMap("date_id", vo.getDate_id());
                conn.addParamMap("date_category_code", vo.getDate_category_code());
                conn.onExcute((isResult, data) -> {
                    if(data==null) {
                        Toast.makeText(getContext(), "이미 등록되어있습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "관심목록에 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            });

        return binding.getRoot();


    }
}