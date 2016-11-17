package com.lnyp.imgdots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.leiyang.csumap.Dijkstra;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.utils.Utils;

/**
 * Created by leiyang on 2016/11/15.
 */

public class ShowPathActivity extends AppCompatActivity {
    TextView StartPlace;
    TextView EndPlace;
    TextView ShowPlace;

    Dijkstra[] dijkstras = Utils.dijkstras;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_path);

        Intent intent = getIntent();
        String mStartPlace = intent.getStringExtra("mStartPlace");
        String mEndPlace = intent.getStringExtra("mEndPlace");

        StartPlace = (TextView) findViewById(R.id.mStartPlace);
        EndPlace = (TextView) findViewById(R.id.mEndPlace);
        ShowPlace = (TextView) findViewById(R.id.mShowPlace);
        StartPlace.setText(mStartPlace);
        EndPlace.setText(mEndPlace);


        String result = dijkstras[0].start();
//        String result = new Dijkstra().start();
        Log.i("info",""+result);
        ShowPlace.setText(result);



    }
}
