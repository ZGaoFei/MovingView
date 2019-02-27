package com.example.zhaogaofei.moving.ui;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.zhaogaofei.moving.R;
import com.example.zhaogaofei.moving.view.FloatBallView;

public class MoveBallViewActivity extends AppCompatActivity {
    private FloatBallView floatBallView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MoveBallViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_ball_view);

        // floatBallView = findViewById(R.id.float_ball_view);
        floatBallView = new FloatBallView(MoveBallViewActivity.this);
    }
}
