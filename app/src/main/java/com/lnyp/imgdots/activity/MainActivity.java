package com.lnyp.imgdots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lnyp.imgdots.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        startActivity(new Intent(this, ImageBrowseActivity.class));
    }

    public void btn1Click(View view){
        Intent intent = new Intent(this,SpacePageActivity.class);
        startActivity(intent);
    }

    public void btn2Click(View view){
        startActivity(new Intent(this, PoupWindowDemoActivity.class));
    }
}
