package com.dressplus.androidndk.ndkUtils;

/**
 * Created by bushetianzhen on 2018/1/18.
 */

public class HelloGl2 {

    static {
        System.loadLibrary("hello-gl2");
    }

    public static native String helloJni();

    public static native void drawFrame();

    public static native void initData(int width, int height);

}
