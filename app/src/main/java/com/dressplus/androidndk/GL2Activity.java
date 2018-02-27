package com.dressplus.androidndk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dressplus.androidndk.ndkUtils.HelloGl2;
import com.dressplus.androidndk.views.GL2JNIView;

public class GL2Activity extends AppCompatActivity {

    //private TextView textView;
    private GL2JNIView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_gl2);

        //textView = (TextView) findViewById(R.id.gl2_text);
        //textView.setText(HelloGl2.helloJni());

        mView = new GL2JNIView(getApplication());
        setContentView(mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }
}
