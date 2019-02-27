package com.example.zhaogaofei.moving.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.zhaogaofei.moving.R;
import com.example.zhaogaofei.moving.view.FloatView;

public class MoveViewActivity extends AppCompatActivity {


    public static void start(Context context) {
        context.startActivity(new Intent(context, MoveViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);

        initView();
    }

    private void initView() {
        final FloatView floatView = findViewById(R.id.float_view);

    }
}
