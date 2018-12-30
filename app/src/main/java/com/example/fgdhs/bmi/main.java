package com.example.fgdhs.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends AppCompatActivity {
    Button cal_send;
    Button record_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cal_send = (Button) findViewById(R.id.cal_btn);
        cal_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent();
                o.setClass(main.this, cal_main.class);
                startActivity(o);
            }
        });


        record_send = (Button) findViewById(R.id.record_btn);
        record_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r=0;
                Intent p = new Intent();
                p.setClass(main.this, record_main.class);
                p.putExtra("r",r);
                startActivity(p);
            }
        });
    }
}
