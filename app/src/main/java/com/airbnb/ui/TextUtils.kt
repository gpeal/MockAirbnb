package com.airbnb.ui

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.text.style.RelativeSizeSpan

public object TextUtils {

    /**
     * Takes a price string with the currency character as the first character in the string
     * and formats it such that the currency character is smaller and top aligned.
     */
    public fun formatPrice(price: CharSequence): CharSequence {
        val span = SpannableString(price)
        val relativeTextSize = 0.85f
        span.setSpan(RelativeSizeSpan(relativeTextSize), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(TopAlignmentSpan(relativeTextSize.toDouble()), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return span
    }

    /**
     * Text span to align the smaller $ in a listing's title view to the top of the text.
     *
     * @param relativeSize The relative size of the dollar sign compared to normal text.
     */
    private class TopAlignmentSpan (relativeSize: Double) : MetricAffectingSpan() {
        private val mRatio: Double

        init {
            mRatio = 1 - relativeSize
        }

        override fun updateDrawState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * mRatio).toInt()
        }

        override fun updateMeasureState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * mRatio).toInt()
        }
    }
}
