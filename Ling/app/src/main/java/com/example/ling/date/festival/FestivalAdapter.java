package com.example.ling.date.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvFestactBinding;
import com.example.ling.date.DateInfoVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.ViewHolder> {

    Context context;
    ArrayList<DateInfoVO> list;

    public FestivalAdapter(Context context, ArrayList<DateInfoVO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecvFestactBinding binding=null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRecvFestactBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        String imageUrl=list.get(i).getDate_img();
        Picasso.get()
                .load(imageUrl)
                .into(h.binding.imgvFestival);
        //h.binding.imgvFav2.setVisibility(View.INVISIBLE);
        h.binding.tvFname.setText(list.get(i).getDate_name());
        h.binding.tvFaddr.setText(list.get(i).getDate_address());
        h.binding.btnAdd.setText(list.get(i).getDibs());
        h.binding.btnAdd.setOnClickListener(v -> {
            if (h.binding.btnAdd.getText().equals("♡")) {
                CommonConn conn = new CommonConn(context, "date_insertdibs");
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.addParamMap("date_id", list.get(i).getDate_id());
                conn.addParamMap("date_category_code", list.get(i).getDate_category_code());
                conn.onExcute((isResult, data) -> {
                    h.binding.btnAdd.setText("♥");
                    Toast.makeText(context, "관심목록에 등록되었습니다.", Toast.LENGTH_SHORT).show();
                });
            } else if(h.binding.btnAdd.getText().equals("♥")) {
                CommonConn conn = new CommonConn(context, "date_deletedibs");
                conn.addParamMap("date_id", list.get(i).getDate_id());
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.onExcute((isResult, data) -> {
                    h.binding.btnAdd.setText("♡");
                    Toast.makeText(context, "관심목록에서 제거되었습니다.", Toast.LENGTH_SHORT).show();
                });
            }
        });
        h.binding.lnFestival.setOnClickListener(v -> {
            Intent intent = new Intent(context, FestDetailActivity.class);
            intent.putExtra("img", list.get(i).getDate_img());
            intent.putExtra("name", list.get(i).getDate_name());
            intent.putExtra("address", list.get(i).getDate_address());
            intent.putExtra("intro", list.get(i).getDate_intro());
            intent.putExtra("open", list.get(i).getOpen());
            intent.putExtra("end", list.get(i).getEnd());
            intent.putExtra("lan", list.get(i).getLan());
            intent.putExtra("lng", list.get(i).getLng());
            context.startActivity(intent);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRecvFestactBinding binding;

        public ViewHolder(@NonNull ItemRecvFestactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
