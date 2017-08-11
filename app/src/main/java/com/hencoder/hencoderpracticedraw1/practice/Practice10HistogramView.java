package com.hencoder.hencoderpracticedraw1.practice;

import com.hencoder.hencoderpracticedraw1.practice.HistogramSingleton.HistogramBean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private int mWidth;
    private int mHeight;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // draw text:直方图
        String title = "直方图";
        float titleTextSize = 40;
        paint.setTextSize(titleTextSize);
        paint.setColor(Color.WHITE);
        float titleWidth = paint.measureText(title);
        canvas.drawText(title, mWidth / 2 - titleWidth / 2, mHeight - titleTextSize, paint);

        //
        float gap = 2 * titleTextSize;
        float lineStrokeWidth = 3;

        // draw vertical line
        float vLineX0 = 120;
        float vLineY0 = 20;
        float vLineY1 = mHeight - titleTextSize * 2 - gap;
        paint.setStrokeWidth(lineStrokeWidth);
        canvas.drawLine(vLineX0, vLineY0, vLineX0, vLineY1, paint);

        // draw horizontal line
        canvas.drawLine(vLineX0, vLineY1, mWidth - vLineX0, vLineY1, paint);
        float totalHLineWidth = mWidth - vLineX0 * 2;
        float hLineHeight = vLineY1;
        float hLineStart = vLineX0;

        HistogramBean[] histogramBeanArray = HistogramSingleton.INSTANCE.getHistogramBeanArray();

        // draw name and histogram
        int beanCount = histogramBeanArray.length;
        float eachSpace = totalHLineWidth / beanCount;
        float simpleNameTextSize = 0.5f * titleTextSize;
        paint.setTextSize(simpleNameTextSize);

        float histogramEachSideGap = 0.1f * eachSpace;
        double maxQuotient = HistogramSingleton.INSTANCE.getMaxBean().getQuotient();
        double maxHeigth = (hLineHeight - vLineY0) * 0.8;

        // 1. findCenter point, start Point = eachSpace / 2;
        float iCenterPoint = hLineStart - eachSpace / 2;
        float iSimpleNameWidth;
        float iXStart;
        float iXEnd;
        Paint histogramPaint = new Paint();
        histogramPaint.setColor(Color.GREEN);
        for (int i = 0; i < beanCount; i++) {
            HistogramBean histogramBean = histogramBeanArray[i];

            iCenterPoint += eachSpace;

            // draw name
            iSimpleNameWidth = paint.measureText(histogramBean.getSimpleName());
            canvas.drawText(histogramBean.getSimpleName(), iCenterPoint - iSimpleNameWidth / 2, hLineHeight + lineStrokeWidth + simpleNameTextSize, paint);

            // draw histogram
            iXStart = iCenterPoint - eachSpace / 2 + histogramEachSideGap;
            iXEnd = iCenterPoint + eachSpace / 2 - histogramEachSideGap;
            canvas.drawRect(iXStart, (float) (hLineHeight - lineStrokeWidth - histogramBean.getQuotient() / maxQuotient * maxHeigth), iXEnd, hLineHeight - lineStrokeWidth, histogramPaint);
        }

    }


}
