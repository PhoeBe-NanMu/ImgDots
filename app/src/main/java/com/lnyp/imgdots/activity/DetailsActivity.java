package com.lnyp.imgdots.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.PointInfo;

/**
 * Created by leiyang on 2016/11/15.
 */

public class DetailsActivity extends AppCompatActivity {


    TextView infotv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_info);
//        PointInfo pointInfo = getIntent().getParcelableExtra("pointInfo");
        Intent intent = getIntent();
        String ming = intent.getStringExtra("ming");
        String num = intent.getStringExtra("num");
        String info = intent.getStringExtra("info");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("景点名称：" + ming+"\n");
        stringBuilder.append("景点代号：" + num+"\n");
        stringBuilder.append("景点信息：" + info+"\n");

        infotv = (TextView) findViewById(R.id.info);
        infotv.setText(stringBuilder.toString());




    }
}
