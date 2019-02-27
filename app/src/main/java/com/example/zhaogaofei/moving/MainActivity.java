package com.example.zhaogaofei.moving;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zhaogaofei.moving.ui.MoveBallViewActivity;
import com.example.zhaogaofei.moving.ui.MoveViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveViewActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveBallViewActivity.start(MainActivity.this);
            }
        });
    }
}
