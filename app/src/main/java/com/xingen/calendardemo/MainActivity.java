package com.xingen.calendardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.xingen.calendarview.listener.ItemClickListener;
import com.xingen.calendarview.view.DateTableSurfaceView;

import java.util.Calendar;

/**
 * Created by ${xingen} on 2017/9/19.
 * blog:http://blog.csdn.net/hexingen
 */
public class MainActivity extends AppCompatActivity {
   private final  String tag=MainActivity.class.getSimpleName();
    private DateTableSurfaceView dateTableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dateTableView=(DateTableSurfaceView) findViewById(R.id.date_table_view);
        this.dateTableView.setMonth(getCurrentTime());
        this.dateTableView.setItemClickListener(new ItemClickListener() {
            @Override
            public void clickDate(String clickDate) {
                Toast.makeText(getApplicationContext(),"点击了"+clickDate,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getCurrentTime(){
        Calendar calendar=Calendar.getInstance();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.YEAR));
        stringBuilder.append("-");
        int month=calendar.get(Calendar.MONTH);
        stringBuilder.append(month+1>9?month+1:"0"+(month+1));
        String s=stringBuilder.toString();
        Log.i("ss",s);
        return s;
    }
}
