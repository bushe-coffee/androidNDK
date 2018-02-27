package com.dressplus.androidndk.ndkUtils;


public class HelloOpenCV {

    static {
        System.loadLibrary("hello-openCV");
    }

    public static native String helloOpenCVJni();
}
