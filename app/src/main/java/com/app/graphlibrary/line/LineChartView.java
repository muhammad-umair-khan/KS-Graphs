package com.app.graphlibrary.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.app.graphlibrary.base.CustomGraphView;

import java.util.ArrayList;

/**
 * Created by umair.shafique on 10/10/2017.
 */

public class LineChartView extends CustomGraphView {
    private int nodeColor;
    private int lineColor;
    private int lineWidth;
    private Paint mNodePaint;
    private Paint mNodeTextPaint;
    private Paint mLinePaint;
    private int numOfNodes;
    private int mHeaderTextMargin;
    private Float mMaxYHeight;

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LineChartView(Context context) {
        super(context);
        init();
    }

    private void init() {
        nodeColor = Color.BLACK;
        lineColor = Color.BLUE;
        mHeaderTextMargin = 200;
        mNodePaint = new Paint();
        mLinePaint = new Paint();
        mNodeTextPaint = new Paint();

        mNodePaint.setColor(Color.BLACK);
        mNodePaint.setAntiAlias(true);
        mNodePaint.setTextSize(headerTextSize);
        mNodePaint.setTextAlign(Paint.Align.CENTER);

        mNodeTextPaint.setColor(Color.BLACK);
        mNodeTextPaint.setAntiAlias(true);
        mNodeTextPaint.setTextSize(headerTextSize);
        mNodeTextPaint.setTextAlign(Paint.Align.CENTER);

        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setTextSize(headerTextSize);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setNodeList(ArrayList<Float> nodeList) {
        this.nodeList = nodeList;
        numOfNodes = nodeList.size();
    }

    public void setHeaderList(ArrayList<String> headerList) {
        this.headerList = headerList;
    }

    public void setFooterList(ArrayList<String> footerList) {
        this.footerList = footerList;
    }

    public void setNodeColor(int color) {
        nodeColor = color;
    }

    public void setLineColor(int color) {
        lineColor = color;
    }

    public void setNodeTextColor(int color) {
        nodeTextColor = color;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setNodeRadius(float radius) {
        this.mRadius = radius * mDensity;
//        this.mSpace = (mRadius + 3) * mDensity;
    }

//    public void setNodeSpace(float space) {
//        this.mSpace = (mRadius + space) * mDensity;
//    }

    public void setHeaderTextColor(int color) {
        mHeaderTextPaint.setColor(color);
    }

    public void setFooterTextColor(int color) {
        mFooterTextPaint.setColor(color);
    }

    public void setHeaderTextSize(float size) {
        mHeaderTextPaint.setTextSize(size);
    }

    public void setFooterTextSize(float size) {
        mFooterTextPaint.setTextSize(size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setXAxis();
        calculateYAxisValues();
        drawChart(canvas);
    }

    private void setXAxis() {
        try {
            xAxisList.add(150f);
            for (int i = 1; i < numOfNodes; i++) {
                xAxisList.add((float) ((i + 1) * 150));
            }
        } catch (Exception e) {
        }
    }

    private void calculateYAxisValues() {
        try {
            float minTemp = nodeList.get(0);
            float maxTemp = nodeList.get(0);
            for (float item : nodeList) {
                if (item < minTemp) {
                    minTemp = item;
                }
                if (item > maxTemp) {
                    maxTemp = item;
                }
            }
            float min = 0;
            if (minTemp < 0)
                min = 0 - minTemp;

            for (int i = 0; i < numOfNodes; i++) {
                yAxisList.add((nodeList.get(i) + min));
            }

//            find max height
            mMaxYHeight = yAxisList.get(0);
            for (int i = 0; i < yAxisList.size(); i++) {
                for (int j = i + 1; j < yAxisList.size(); j++) {
                    if (yAxisList.get(j) > mMaxYHeight)
                        mMaxYHeight = yAxisList.get(j);
                }
            }

        } catch (Exception e) {
        }
    }

    /**
     * @param canvas
     */
    private void drawChart(Canvas canvas) {
        try {
            mLinePaint.setColor(lineColor);
            mLinePaint.setStrokeWidth(lineWidth);
            mNodePaint.setColor(nodeColor);
            mNodeTextPaint.setColor(nodeTextColor);

            for (int i = 0; i < numOfNodes; i++) {
                if (i < numOfNodes - 1) {
                    mLinePaint.setPathEffect(null);
                    canvas.drawLine(xAxisList.get(i), yAxisList.get(i) + mHeaderTextMargin, xAxisList.get(i + 1), yAxisList.get(i + 1) + mHeaderTextMargin, mLinePaint);
                }

                canvas.drawCircle(xAxisList.get(i), yAxisList.get(i) + mHeaderTextMargin, mRadius, mNodePaint);

                drawNodeText(canvas, mNodeTextPaint, i);

                if (headerList != null && headerList.size() > 0)
                    drawHeaderText(canvas, mHeaderTextPaint, i);

                if (footerList != null && footerList.size() > 0)
                    drawFooterText(canvas, mFooterTextPaint, i);
            }
        } catch (Exception e) {
            Log.i("dad", "");
        }
    }

    /**
     * @param canvas
     * @param textPaint
     * @param index
     */
    private void drawNodeText(Canvas canvas, Paint textPaint, int index) {
        canvas.drawText(String.valueOf(nodeList.get(index)), xAxisList.get(index), (yAxisList.get(index) + mHeaderTextMargin) - (mRadius + 10), textPaint);
    }

    /**
     * @param canvas
     * @param textPaint
     * @param index
     */
    private void drawHeaderText(Canvas canvas, Paint textPaint, int index) {
        canvas.drawText(headerList.get(index), xAxisList.get(index), 50, textPaint);
    }

    /**
     * @param canvas
     * @param textPaint
     * @param index
     */
    private void drawFooterText(Canvas canvas, Paint textPaint, int index) {
        canvas.drawText(footerList.get(index), xAxisList.get(index), mMaxYHeight + mHeaderTextMargin + headerTextSize + mRadius, textPaint);
    }

    /**
     * @param canvas
     * @param textPaint
     * @param index
     */
    private void drawLeftText(Canvas canvas, Paint textPaint, int index) {
        canvas.drawText(leftList.get(index), xAxisList.get(index) - 50, yAxisList.get(index), textPaint);
    }

    /**
     * @param canvas
     * @param textPaint
     * @param index
     */
    private void drawRightText(Canvas canvas, Paint textPaint, int index) {
        canvas.drawText(rightList.get(index), 50, yAxisList.get(index), textPaint);
    }
}
