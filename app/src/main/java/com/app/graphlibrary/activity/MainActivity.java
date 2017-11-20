package com.app.graphlibrary.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.app.graphlibrary.R;
import com.app.graphlibrary.line.LineChartView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChartView lineChartView = (LineChartView) findViewById(R.id.lcv);
        ArrayList<Float> f = new ArrayList<Float>();
        for (int i = 0; i < 100; i++) {
            f.add(i, (float) (Math.random() * 1000 + (-100)));
        }

        lineChartView.setNodeList(f);
        lineChartView.setLineWidth(20);
        lineChartView.setNodeRadius(5);
        lineChartView.setLineColor(Color.RED);
        lineChartView.setNodeColor(Color.GREEN);
        lineChartView.setNodeTextColor(Color.BLACK);
        lineChartView.setMinimumWidth((f.size() * 150) + 150);
        lineChartView.invalidate();
    }
}
