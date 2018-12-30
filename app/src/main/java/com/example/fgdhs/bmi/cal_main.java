package com.example.fgdhs.bmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class cal_main extends AppCompatActivity {
    Button calculation;
    EditText tall;
    EditText body;
    EditText year;
    Button cal_return;
    private SQLiteDatabase dbrw;

    int sex=0;
    double BMI,x,z;
    int y,n;
    String pp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_main);


        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.boy:
                        sex = 1;
                        y=25;
                        break;
                    case R.id.girl:
                        sex = 0;
                        y=22;
                        break;
                }
            }
        });
        cal_return = (Button) findViewById(R.id.button3);
        cal_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calculation = (Button) findViewById(R.id.calculation);
        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tall = (EditText)findViewById(R.id.tall);
                body = (EditText)findViewById(R.id.body);
                year = (EditText)findViewById(R.id.year);
                if (tall.length()<1||body.length()<1||year.length()<1)
                {
                    Toast.makeText(cal_main.this,"欄位請勿留空",Toast.LENGTH_SHORT).show();
                }
                else {
                    double tall_tem = Integer.parseInt(tall.getText().toString());
                    double body_temp = Integer.parseInt(body.getText().toString());
                    double year_temp = Integer.parseInt(year.getText().toString());



                    double tall_temp = tall_tem / 100;

                    BMI = (body_temp / (tall_temp * tall_temp));
                    x = (1.2 * BMI) + (0.23 * year_temp - 5.4) - (10.8 * sex);
                    z = tall_temp * y;
                    if (BMI < 18.5) {
                        pp = "體重過輕";
                        n=1;
                    } else if ((18.5 <= BMI) && (BMI < 24)) {
                        pp = "正常";
                        n=2;
                    } else if ((24 <= BMI) && (BMI < 27)) {
                        pp = "過重";
                        n=3;
                    } else if ((27 <= BMI) && (BMI < 30)) {
                        pp = "輕度肥胖";
                        n=4;
                    } else if ((30 <= BMI) && (BMI < 35)) {
                        pp = "中度肥胖";
                        n=5;
                    } else if (BMI <= 35) {
                        pp = "重度肥胖";
                        n=6;
                    }
                    DecimalFormat df = new DecimalFormat("##.00");
                    BMI=Double.parseDouble(df.format(BMI));
                    x=Double.parseDouble(df.format(x));
                    z=Double.parseDouble(df.format(z));

                    final Bundle bundle = new Bundle();
                    bundle.putDouble("tall",tall_tem);
                    bundle.putDouble("body",body_temp);
                    bundle.putDouble("BMI",BMI);
                    bundle.putDouble("x",x);
                    bundle.putString("pp", pp);


                    final String[] list_item = {"BMI:"+BMI,"標準體重:"+z,"狀態:"+pp,"體脂:"+x};
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(cal_main.this);
                    dialog.setTitle("結果");
                    dialog.setItems(list_item, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int r =1;
                            Intent l = new Intent();
                            l.setClass(cal_main.this,record_main.class);
                            l.putExtra("key",bundle);
                            l.putExtra("r",r);
                            startActivity(l);

                        }
                    });

                    dialog.show();


                }
            }
        });
    }

}
