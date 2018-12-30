package com.example.fgdhs.bmi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class record_main extends AppCompatActivity {
    private ListView record;
    private Button return_btn;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items = new ArrayList<>();
    private SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_main);
        int r = getIntent().getExtras().getInt("r");
        record = findViewById(R.id.record);
        return_btn = findViewById(R.id.return_btn);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        record.setAdapter(adapter);
        dbrw = new MyDBHelper(this).getWritableDatabase();
        if(r == 1) {
        Bundle bundle = getIntent().getExtras().getBundle("key");
        Double tall = bundle.getDouble("tall");
        Double body = bundle.getDouble("body");
        Double BMI = bundle.getDouble("BMI");
        Double x = bundle.getDouble("x");
        String pp = bundle.getString("pp");



            dbrw.execSQL("INSERT INTO myTable(bmi,status,tall,body)VALUES(?,?,?,?)", new Object[]{BMI, pp, tall, body});
        }
        Cursor c;

        c = dbrw.rawQuery("SELECT * FROM myTable",null);

        c.moveToFirst();
        items.clear();

        for(int i = 0;i<c.getCount();i++){
            items.add("BMI:"+c.getString(0)+"\t"+c.getString(1)+
                    "\n體重:"+c.getString(3)+"身高:"+c.getString(2));
            c.moveToNext();
        }
        adapter.notifyDataSetChanged();
        c.close();
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
