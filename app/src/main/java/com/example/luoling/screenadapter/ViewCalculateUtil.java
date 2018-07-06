package com.example.luoling.screenadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 48608 on 2017/11/20.
 */

public class ViewCalculateUtil {
    //获取调用层传入的值进行设置
    public static void setViewLayoutParam(View view, int width,int heigth,int topMargin,int bottomMargin,int leftMargin,int rightMargin){
        ViewGroup.MarginLayoutParams layoutParams= (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.width=UIUtils.getInstance(view.getContext().getApplicationContext()).getWidth(width);
        layoutParams.height=UIUtils.getInstance(view.getContext().getApplicationContext()).getHeight(heigth);
        //Height：是竖着缩放
        //Width:是横着缩放
        layoutParams.topMargin=UIUtils.getInstance(view.getContext().getApplicationContext()).getHeight(topMargin);
        layoutParams.bottomMargin=UIUtils.getInstance(view.getContext().getApplicationContext()).getHeight(bottomMargin);
        layoutParams.leftMargin=UIUtils.getInstance(view.getContext().getApplicationContext()).getWidth(leftMargin);
        layoutParams.rightMargin=UIUtils.getInstance(view.getContext().getApplicationContext()).getWidth(rightMargin);
        view.setLayoutParams(layoutParams);

    }

}











