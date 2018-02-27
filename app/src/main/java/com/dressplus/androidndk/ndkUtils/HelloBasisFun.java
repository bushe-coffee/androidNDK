package com.dressplus.androidndk.ndkUtils;

public class HelloBasisFun {

    static {
        System.loadLibrary("hello-basis");
    }

    public static native int handleArray(int data[]);
}
