package com.example.luoling.screenadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

        ViewCalculateUtil.setViewLayoutParam(txt1,1040,80,10,0,20,20);
        ViewCalculateUtil.setViewLayoutParam(txt2,400,400,50,0,0,0);
    }
}
