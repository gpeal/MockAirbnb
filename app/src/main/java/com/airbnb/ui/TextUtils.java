package com.airbnb.ui;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;

public class TextUtils {

    /**
     * Takes a price string with the currency character as the first character in the string
     * and formats it such that the currency character is smaller and top aligned.
     */
    public static CharSequence formatPrice(CharSequence price) {
        Spannable span = new SpannableString(price);
        float relativeTextSize = 0.85f;
        span.setSpan(new RelativeSizeSpan(relativeTextSize), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new TopAlignmentSpan(relativeTextSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    /**
     * Text span to align the smaller $ in a listing's title view to the top of the text.
     */
    private static final class TopAlignmentSpan extends MetricAffectingSpan {
        private final double mRatio;

        /**
         * @param relativeSize The relative size of the dollar sign compared to normal text.
         */
        public TopAlignmentSpan(double relativeSize) {
            mRatio = 1 - relativeSize;
        }

        @Override
        public void updateDrawState(TextPaint paint) {
            paint.baselineShift += (int) (paint.ascent() * mRatio);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            paint.baselineShift += (int) (paint.ascent() * mRatio);
        }
    }
}
