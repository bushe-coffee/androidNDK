package com.dressplus.androidndk.ndkUtils;


public class HelloTest {

    static {
        System.loadLibrary("hello-test");
    }

    public static native String helloJni();
}
