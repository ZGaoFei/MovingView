package com.example.zhaogaofei.moving;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public final class Utils {
    private Utils() {
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("zgf", "status bar height is: " + result);
        return result;
    }

    public static int[] getScreenWidthAndHeight(Context context) {
        int[] screenParams = new int[2];
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenParams[0] = display.getWidth();
        screenParams[1] = display.getHeight();
        return screenParams;
    }
}
