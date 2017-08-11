package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private static final String TAG = Practice9DrawPathView.class.getSimpleName();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        Path path = new Path();

        float x = 400, y = 250, r = 80;

        RectF rectF = new RectF(x - r, y - r, x + r, y + r);//x, y
        path.addArc(rectF, 135, 225);
        RectF rectF2 = new RectF(x + r, y - r, x + 3 * r, y + r);//x+2r, y
        path.arcTo(rectF2, 180, 225);// addArc is wrong.
        /*
        path.addCircle(x, y, r, Path.Direction.CW);
        path.addCircle(x + 2 * r, y, r, Path.Direction.CW);
        */
        path.lineTo(x + r, y + 2.5f * r);

        canvas.drawPath(path, new Paint());

    }

}
