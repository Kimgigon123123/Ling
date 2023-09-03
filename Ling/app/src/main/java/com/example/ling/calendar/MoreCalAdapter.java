package com.example.ling.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemCalendarInfoBinding;
import com.example.ling.databinding.ItemRecvScheduleBinding;

import java.util.ArrayList;

public class MoreCalAdapter extends RecyclerView.Adapter<MoreCalAdapter.ViewHolder>{

    ArrayList<ScheAddVO> list;
    ItemCalendarInfoBinding binding;

    public MoreCalAdapter(ArrayList<ScheAddVO> list, Context context) {
        this.list = list;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemCalendarInfoBinding binding = ItemCalendarInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoreCalAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        ScheAddVO vo = list.get(i);
        // 다음 두 줄을 추가하여 일정이 없는 경우에도 빈 데이터를 처리 (처리가 안 되는중)
        if (vo.getSche_title() == null || vo.getSche_title().isEmpty()) {
            h.binding.tvCalendarInfoTitle.setText("일정 없음");
            h.binding.imgvCalCategory.setVisibility(View.GONE); // 날짜 숨김
        } else {
            h.binding.tvCalendarInfoTitle.setText("일정: "+ vo.getSche_title());
            h.binding.imgvCalCategory.setImageResource(R.drawable.sample_circle);
        }

        h.binding.imgvBtnMenu.setOnClickListener(v -> {
            PopupMenu popup= new PopupMenu(context, v);//v는 클릭된 뷰를 의미
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.select_menu, popup.getMenu()); // your_popup_menu는 res/menu 폴더에 정의한 메뉴 파일

            // 팝업 메뉴 아이템 클릭 리스너 설정


            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.m1) {// 메뉴 아이템 1 선택 시 동작Y
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = LayoutInflater.from(context).inflate(R.layout.calendar_update, null);
                    builder.setView(dialogView);

                    EditText editText = dialogView.findViewById(R.id.edt_update);
                    Button updateButton = dialogView.findViewById(R.id.btn_cal_update);
                    Button cancelButton = dialogView.findViewById(R.id.btn_cal_update_cancle);

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    updateButton.setOnClickListener(v1 -> {
                        String editedText = editText.getText().toString();

                        CommonConn conn = new CommonConn(context, "sche_update");

                        conn.addParamMap("id", CommonVar.loginInfo.getId());
                        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                        conn.addParamMap("sche_title", editedText);
                        conn.addParamMap("sche_no", list.get(i).getSche_no());

                        conn.onExcute((isResult, data) -> {
                            Toast.makeText(context, "수정이 완료되었습니다. " + editedText, Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                            notifyDataSetChanged();
                        });



                    });

                    cancelButton.setOnClickListener(v1 -> dialog.dismiss());
                    return true;
                } else if (itemId == R.id.m2) {// 메뉴 아이템 2 선택 시 동작
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("일정삭제");
                    alert.setMessage("정말로 삭제하시겠습니까?");

                    alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CommonConn conn = new CommonConn(context, "sche_delete");
                            conn.addParamMap("sche_no", list.get(i).getSche_no());


                            conn.onExcute((isResult, data) -> {
                                    if(isResult){
                                        list.remove(i);

                                        notifyDataSetChanged();
                                    }
                            });
                        }
                    });

                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
                    alert.show();

                    return true;
                    // 다른 메뉴 아이템들도 추가할 수 있음
                }
                return false;
            });

            // 팝업 메뉴 표시
            popup.show();
        });









    }
    private void update_dialog() {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemCalendarInfoBinding binding;

        public ViewHolder(@NonNull ItemCalendarInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
