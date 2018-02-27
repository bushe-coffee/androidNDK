package com.dressplus.androidndk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dressplus.androidndk.ndkUtils.HelloBasisFun;
import com.dressplus.androidndk.ndkUtils.HelloTest;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView view = (TextView) findViewById(R.id.main_text);
        textView = (TextView) findViewById(R.id.show_basis_result);
        view.setText(HelloTest.helloJni());
    }

    public void OpenGL2Activity(View view) {
        startActivity(new Intent(this, GL2Activity.class));
    }


    public void handleArrayToSum(View view) {
        int datas[] = {12,23,34,45,56,67,78,89,90};
        String res = "result : " + HelloBasisFun.handleArray(datas);
        textView.setText(res);
    }

    public void handleBitmap(View view) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open("nomal_1.jpg"));
        Log.e("xiaobo", bitmap.toString());
        if (bitmap != null) {

        }
    }
}
