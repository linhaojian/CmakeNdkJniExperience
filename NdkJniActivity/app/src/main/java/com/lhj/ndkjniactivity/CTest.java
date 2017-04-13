package com.lhj.ndkjniactivity;

import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CTest {
    public native int ctest(int a,int b);
    public native void cInvokingJava();

    static {
        System.loadLibrary("testc-lib");
    }

    /**
     *  c调用java
     */
    private void ctestInvokingJava(String str){
        Log.e("lhj",""+str);
    }

}
