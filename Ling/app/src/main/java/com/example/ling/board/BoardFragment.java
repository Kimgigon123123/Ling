package com.example.ling.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentBoardBinding;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class BoardFragment extends Fragment {

    FragmentBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();




        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("게시판",R.drawable.shape));
        menuItems.add(new MenuItem("공지사항",R.drawable.shape));
        menuItems.add(new MenuItem("자유 게시판",R.drawable.shape));
        menuItems.add(new MenuItem("고민 상담소",R.drawable.shape));
        menuItems.add(new MenuItem("짝궁 놀이터",R.drawable.shape));
        binding.navigationDrawer.setMenuItemList(menuItems);
        binding.navigationDrawer.setAppbarTitleTV("게시판");


        binding.navigationDrawer.setOnMenuItemClickListener(i -> {
            //changeFragment(i);

            if (i == 0){
                getChildFragmentManager().beginTransaction().replace(R.id.container , new BoardMainFragment() ).commit();
                binding.navigationDrawer.setAppbarTitleTV("게시판");
            }else if(i == 1){
                getChildFragmentManager().beginTransaction().replace(R.id.container , new Board_CommonFragment("NOTICE")   ).commit();
                binding.navigationDrawer.setAppbarTitleTV("공지사항");

            }else if(i == 2){
                getChildFragmentManager().beginTransaction().replace(R.id.container , new Board_CommonFragment("FREE")    ).commit();
                binding.navigationDrawer.setAppbarTitleTV("자유 게시판");
            }else if(i == 3){
                getChildFragmentManager().beginTransaction().replace(R.id.container , new Board_CommonFragment("WORRY")     ).commit();
                binding.navigationDrawer.setAppbarTitleTV("고민 상담소");
            }else if(i == 4){
                getChildFragmentManager().beginTransaction().replace(R.id.container , new Board_CommonFragment("PLAY")    ).commit();
                binding.navigationDrawer.setAppbarTitleTV("짝궁 놀이터");
            }
        });

        changeFragment(0);
    }

    public void changeFragment(int i){
        //메뉴를 하나씩 미룸.  (홈 추가 시 )

        binding.navigationDrawer.clickItem(i);

    }
}