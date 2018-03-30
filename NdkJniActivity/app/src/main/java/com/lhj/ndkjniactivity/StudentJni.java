package com.lhj.ndkjniactivity;

/**
 * 在Native层返回一个复杂对象(即一个类咯)
 */

public class StudentJni {
    static {
        System.loadLibrary("object-lib");
    }

    public native Student nativeGetStudentInfo();

    public native String getString();
}
