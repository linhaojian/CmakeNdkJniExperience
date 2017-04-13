package com.lhj.ndkjniactivity;

/**
 * Created by Administrator on 2017/4/12.
 */

public class NdkJniTest {
    public native String testString();

    static {
        System.loadLibrary("test-lib");
    }
}
