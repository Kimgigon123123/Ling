package com.example.ling.date.festival;

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

public class FestInfoFragment extends Fragment {

    FragmentFestInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFestInfoBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();
        if(bundle !=null){
            String imageUrl=bundle.getString("img");
            Picasso.get()
                    .load(imageUrl)
                    .into(binding.imgv);
            binding.tvName.setText(bundle.getString("name"));
            binding.tvTime.setText("축제일자 : " + bundle.get("open") + " ~ " + bundle.getString("end"));
            binding.tvAddress.setText("주소 : " + bundle.getString("address"));
            binding.tvIntro.setText("소개 : " + bundle.getString("intro"));
            binding.btnAdd.setOnClickListener(v -> {
                CommonConn conn = new CommonConn(getContext(), "date_insertdibs");
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.addParamMap("date_id", bundle.getInt("date_id", -1));
                conn.addParamMap("date_category_code", bundle.getString("date_category_code"));
                conn.onExcute((isResult, data) -> {
                    if(data==null) {
                        Toast.makeText(getContext(), "이미 등록되어있습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "관심목록에 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }

        return binding.getRoot();


    }
}