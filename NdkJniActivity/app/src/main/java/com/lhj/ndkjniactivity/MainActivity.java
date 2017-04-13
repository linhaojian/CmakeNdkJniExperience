package com.lhj.ndkjniactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        NdkJniTest ndk = new NdkJniTest();
        TextView tv1 = (TextView) findViewById(R.id.sample1_text);
        tv1.setText(ndk.testString());
        final CTest ct = new CTest();
        TextView tv2 = (TextView) findViewById(R.id.sample2_text);
        tv2.setText(ct.ctest(100,100)+"");
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testJNI();
            }
        });
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.cInvokingJava();
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native void testJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     *  c++调用java
     */
    private void cppInvokingJava(){
        TextView tv3 = (TextView) findViewById(R.id.sample3_text);
        tv3.setText("c++调用Java :"+new Random().nextInt());
    }

}
