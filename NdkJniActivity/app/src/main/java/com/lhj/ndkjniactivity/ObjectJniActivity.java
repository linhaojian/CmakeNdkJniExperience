package com.lhj.ndkjniactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ObjectJniActivity extends Activity {
    Button objectget_btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        objectget_btn = (Button) findViewById(R.id.objectget_btn);
        text = (TextView) findViewById(R.id.text);
        objectget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentJni studentJni = new StudentJni();
                Student student = studentJni.nativeGetStudentInfo();
                text.setText(student.toString());
            }
        });
    }




}
