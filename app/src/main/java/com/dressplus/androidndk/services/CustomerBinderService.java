package com.dressplus.androidndk.services;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dressplus.androidndk.R;

public class CustomerBinderService extends Service{

    private IBinder mBinder = new LocalBinder();
    private View view;

    @Override
    public void onCreate() {
        super.onCreate();
        view = LayoutInflater.from(getApplication()).inflate(R.layout.service_view, null);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("yangxinyu  ","onBind");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.e("yangxinyu  ","onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("yangxinyu  ","onUnbind");
        return true;
    }

    public class LocalBinder extends Binder {

        //  创建一个 view 显示界面上 相当于 桌面歌词
        public void createView(){

            Log.e("yangxinyu  ","createView");
            WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            params.width = metrics.widthPixels / 3;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;

            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_SECURE;

            params.gravity = Gravity.RIGHT | Gravity.TOP;
            params.dimAmount = 0.45f;
            //以屏幕左上角为原点，设置x、y初始值
//            params.x = 170;
//            params.y = 280;

            //ImageView tv = new ImageView(CustomerBinderService.this);
            //tv.setImageResource(R.mipmap.jiege);

            wm.addView(view, params);
            Log.e("yangxinyu  ","createView22222");
        }

    }
}
