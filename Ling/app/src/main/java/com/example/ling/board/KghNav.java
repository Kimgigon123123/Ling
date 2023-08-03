package com.example.ling.board;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

public class KghNav extends SNavigationDrawer {
    public void clickItem(int pos){
        menuLL.getChildAt(pos).performClick();
        //return menuLL.getChildAt(pos);
    }

    public KghNav(Context context) {
        super(context);
    }

    public KghNav(Context context, AttributeSet attrs) {
        super(context , attrs);
    }

}
