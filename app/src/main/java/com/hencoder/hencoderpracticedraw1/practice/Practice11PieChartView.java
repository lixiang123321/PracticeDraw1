package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private int mWidth;
    private int mHeight;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // title
        float centX = 2f / 5f * mWidth;
        float titleTextSize = 40;
        String titleStr = "饼图";
        paint.setTextSize(titleTextSize);
        float titleWidth = paint.measureText(titleStr);// should setTextSize first, then measureText
        paint.setColor(Color.WHITE);
        canvas.drawText(titleStr, centX - titleWidth / 2f, mHeight - titleTextSize, paint);

        // pie
        float radius = (mHeight - 4 * titleTextSize) / 2;
        float centY = radius + titleTextSize;
        float angleGap = 1;
        float startAngle = 0;
        float sweepAngle;
        float offset = 20;
        RectF rectF = new RectF(centX - radius, centY - radius, centX + radius, centY + radius);

        // line
        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(3);
        float lineFirstLength = 10;
        float lineEndLength = radius * 1.2f;

        // name
        Paint namePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        namePaint.setColor(Color.WHITE);
        namePaint.setTextSize(20);
        float nameGap = 10;

        HistogramSingleton.HistogramBean[] histogramBeanArray = HistogramSingleton.INSTANCE.getHistogramBeanArray();
        int length = histogramBeanArray.length;
        float sweepAngleFix;
        double alpha;
        float startPX;
        float startPY;
        float firstEndPX;
        float firstEndPY;

        for (int i = 0; i < length; i++) {
            sweepAngle = (float) (histogramBeanArray[i].getPercent() * 360);
            paint.setColor(histogramBeanArray[i].getPieColor());
            sweepAngleFix = sweepAngle - angleGap / 2;
            if (length - 1 == i) {
                sweepAngleFix = 360 - angleGap / 2 - startAngle;
            }

            // offset,
            if (length - 2 == i) {
                canvas.translate(-offset, -offset);
            } else if (length - 1 == i) {
                canvas.translate(offset, offset);
            }

            // draw pie
            canvas.drawArc(rectF, startAngle, sweepAngleFix >= 0 ? sweepAngleFix : 0, true, paint);

            // draw line, find the start point
            alpha = startAngle + sweepAngleFix / 2d;
            alpha = alpha / 360 * 2 * Math.PI;
            startPX = (float) (centX + radius * Math.cos(alpha));// cos use Pi, and canvas use 360.
            startPY = (float) (centY + radius * Math.sin(alpha));
            //canvas.drawPoint(startPX, startPY, linePaint);//debug

            firstEndPX = (float) (centX + (radius + lineFirstLength) * Math.cos(alpha));
            firstEndPY = (float) (centY + (radius + lineFirstLength) * Math.sin(alpha));
            // line :first part
            canvas.drawLine(startPX, startPY, firstEndPX, firstEndPY, linePaint);
            if (startPX >= centX) {
                // line second part: in the right side
                canvas.drawLine(firstEndPX, firstEndPY, centX + lineEndLength, firstEndPY, linePaint);
                // name
                canvas.drawText(histogramBeanArray[i].getFullName(), centX + lineEndLength + nameGap, firstEndPY, namePaint);
            } else {
                // line second part: left side
                canvas.drawLine(firstEndPX, firstEndPY, centX - lineEndLength, firstEndPY, linePaint);
                // name
                canvas.drawText(histogramBeanArray[i].getFullName(), centX - lineEndLength - nameGap - namePaint.measureText(histogramBeanArray[i].getFullName()), firstEndPY, namePaint);
            }

            // for next
            startAngle += sweepAngle + angleGap / 2f;
        }

    }
}
