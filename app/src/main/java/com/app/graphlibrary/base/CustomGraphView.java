package com.app.graphlibrary.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class CustomGraphView extends View {
    //  Axis
    protected ArrayList<Float> xAxisList;
    protected ArrayList<Float> yAxisList;
    //  Array List of values of all sides
    protected ArrayList<Float> nodeList;
    protected ArrayList<String> headerList;
    protected ArrayList<String> leftList;
    protected ArrayList<String> rightList;
    protected ArrayList<String> footerList;
    //  Text Sizes of all sides
    protected float headerTextSize;
    protected float footerTextSize;
    protected float leftTextSize;
    protected float rightTextSize;
    protected float nodeTextSize;
    //  Text Color of all sides
    protected int headerTextColor;
    protected int leftTextColor;
    protected int rightTextColor;
    protected int bottomTextColor;
    protected int nodeTextColor;

    protected Paint mHeaderTextPaint;
    protected Paint mFooterTextPaint;
    protected Paint mLeftTextPaint;
    protected Paint mRightTextPaint;

    //Density
    protected float mDensity;
    //  Space between nodes
    protected float mSpace;

    protected float mRadius;
    //  Stroke width
    protected float strokeWidth;


    public CustomGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public CustomGraphView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        xAxisList = new ArrayList<>();
        yAxisList = new ArrayList<>();

        headerTextColor = Color.BLACK;
        leftTextColor = Color.BLACK;
        rightTextColor = Color.BLACK;
        bottomTextColor = Color.BLACK;
        headerTextSize = 50f;
        footerTextSize = 50f;
        leftTextSize = 50f;
        rightTextSize = 50f;
        strokeWidth = 2 * mDensity;
        mDensity = getResources().getDisplayMetrics().density;
        mRadius = 10 * mDensity;
        mSpace = 3 * mDensity;

        initPaint();
    }


    private void initPaint() {
        mHeaderTextPaint = new Paint();
        mFooterTextPaint = new Paint();
        mLeftTextPaint = new Paint();
        mRightTextPaint = new Paint();

        mHeaderTextPaint.setColor(Color.BLACK);
        mHeaderTextPaint.setAntiAlias(true);
        mHeaderTextPaint.setTextSize(headerTextSize);
        mHeaderTextPaint.setTextAlign(Paint.Align.CENTER);


        mFooterTextPaint.setColor(Color.BLACK);
        mFooterTextPaint.setAntiAlias(true);
        mFooterTextPaint.setTextSize(headerTextSize);
        mFooterTextPaint.setTextAlign(Paint.Align.CENTER);
    }
}
