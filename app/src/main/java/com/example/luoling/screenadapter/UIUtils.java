package com.example.luoling.screenadapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {
    private static volatile UIUtils instance;

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (UIUtils.class) {
                if (instance == null) {
                    instance = new UIUtils(context);
                }
            }
        }
        return instance;
    }

    public static final int STANDARD_WIDTH = 1080;
    public static final int STANDARD_HEIGHT = 1920;
    private int systemBarHeight;

    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";

    //实际设备分辨率   480   800
    public float displayMetricsWidth;
    public float displayMetricsHeight;
    private DisplayMetrics displayMetrics;

    //初化化
    private UIUtils(Context context) {
        //获取屏幕的真实宽高
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0F || displayMetricsHeight == 0.0F) {
            Display display = windowManager.getDefaultDisplay();
            display.getMetrics(displayMetrics);
            //获取到状态框的高度48
            systemBarHeight = getSystemBarHeight(context);
            //处理真实宽高的问题
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {//横
                this.displayMetricsWidth = (float) displayMetrics.heightPixels;
                this.displayMetricsHeight = (float) displayMetrics.widthPixels - systemBarHeight;
            } else {//竖
                this.displayMetricsWidth = (float) displayMetrics.widthPixels;
                this.displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHeight;
            }
        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context, "com.android.internal.R$dimen", "system_bar_height", 48);
    }

    private int getNavgtionBarHeight(Context context){
        return getValue(context,"com.android.internal.R$dimen","navigation_bar_height",0);
    }

    /**
     * @param context
     * @return
     */
    private int getValue(Context context, String attrGroupClass, String attrName, int defValue) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object obj = e.newInstance();
            Field field = e.getField(attrName);
            //获取到的是一个ID
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e1) {
            return defValue;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean haveNavgtion(Display display){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        int heightDisplay = displayMetrics.heightPixels;
        int widthDisplay = displayMetrics.widthPixels;

        DisplayMetrics contentDisplayMetrics = new DisplayMetrics();
        display.getMetrics(contentDisplayMetrics);
        int contentDisplayW = contentDisplayMetrics.heightPixels;
        int contentDisplayH = contentDisplayMetrics.widthPixels;

        //屏幕内容高度  显示内容的屏幕
        //哪一方大于0   就有导航栏
        int w = widthDisplay - contentDisplayW;
        int h = heightDisplay - contentDisplayH;

        return w > 0 || h > 0;
    }


    //开始获取缩放以后的结果
    public float getWidth(float width) {
        return width * this.displayMetricsWidth / STANDARD_WIDTH;
    }

    public float getHeight(float heigth) {
        return heigth * this.displayMetricsHeight / (STANDARD_HEIGHT - systemBarHeight);
    }

    public int getWidth(int width) {
        return (int) (width * this.displayMetricsWidth / STANDARD_WIDTH);
    }

    public int getHeight(int heigth) {
        return (int) (heigth * this.displayMetricsHeight / (STANDARD_HEIGHT - systemBarHeight));
    }

    private  float dp2px(Context context, int dp, DisplayMetrics displayMetrics){
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,displayMetrics);
        return value;
    }
}
