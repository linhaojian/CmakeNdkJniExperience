package com.lhj.ndkjniactivity;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CTest {
    public native int ctest(int a,int b);
    public native void CInvokingJava();

    static {
        System.loadLibrary("testc-lib");
    }

    /**
     *  c调用java
     */
    public void ctestInvokingJava(String str){
        Log.e("linhaojian","str : "+str);
    }

}
