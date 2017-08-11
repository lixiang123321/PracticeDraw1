package com.hencoder.hencoderpracticedraw1.practice;

import android.graphics.Color;

/**
 * Created by rere on 2017-8-3.
 */

public enum HistogramSingleton {

    INSTANCE;

    private HistogramBean[] mHistogramBeanArray = new HistogramBean[]{
            new HistogramBean("Froyo", "Froyo", 0.1, Color.GRAY),
            new HistogramBean("Gingerbread", "GB", 1, Color.argb(255, 255, 0, 20)),
            new HistogramBean("Ice Cream SandWich", "ICS", 1, Color.GRAY),
            new HistogramBean("Jelly Bean", "JB", 9, Color.argb(255, 0, 255, 20)),
            new HistogramBean("KitKat", "KitKat", 17, Color.BLUE),
            new HistogramBean("Lollpop", "L", 20, Color.RED),
            new HistogramBean("Marshmallow", "M", 9, Color.argb(255, 230, 255, 0)),
    };

    private boolean mHasCalculate = false;

    private void calculate() {
        if (mHasCalculate) {
            return;
        }
        double sum = 0d;
        for (HistogramBean histogramBean : mHistogramBeanArray) {
            sum += histogramBean.getQuotient();
        }

        for (HistogramBean histogramBean : mHistogramBeanArray) {
            histogramBean.setPercent(histogramBean.getQuotient() / sum);
        }
        mHasCalculate = true;
    }

    public HistogramBean[] getHistogramBeanArray() {
        calculate();
        return mHistogramBeanArray;
    }

    public static class HistogramBean {
        // 描述，简要描述，高度
        private String mFullName;

        private String mSimpleName;

        private double mQuotient;

        private double mPercent;

        private int mPieColor;

        public HistogramBean(String fullName, String simpleName, double quotient, int pieColor) {
            mFullName = fullName;
            mSimpleName = simpleName;
            mQuotient = quotient;
            mPieColor = pieColor;
        }

        public double getPercent() {
            return mPercent;
        }

        public void setPercent(double percent) {
            mPercent = percent;
        }

        public double getQuotient() {
            return mQuotient;
        }

        public String getFullName() {
            return mFullName;
        }

        public String getSimpleName() {
            return mSimpleName;
        }

        public int getPieColor() {
            return mPieColor;
        }
    }

    public HistogramBean getMaxBean() {
        int index = 0;
        double max = mHistogramBeanArray[0].getQuotient();
        for (int i = 1; i < mHistogramBeanArray.length; i++) {
            if (mHistogramBeanArray[i].getQuotient() > max) {
                max = mHistogramBeanArray[i].getQuotient();
                index = i;
            }
        }
        return mHistogramBeanArray[index];
    }

}
