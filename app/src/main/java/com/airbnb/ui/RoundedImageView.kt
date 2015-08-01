package com.airbnb.ui

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView

import com.airbnb.R

/**
 * Custom view that displays a circular profile picture image with a stroke around it.
 */
public class RoundedImageView @jvmOverloads constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : ImageView(context, attrs, defStyle) {

    private val mStrokeWidth: Int
    private val mStrokePaint = Paint()

    private var mRoundedBitmap: Bitmap? = null

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView)
        val strokeColor = ta.getColor(R.styleable.RoundedImageView_strokeColor, Color.WHITE)
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.RoundedImageView_strokeWidth, dpToPx(2f).toInt())
        mStrokePaint.setStyle(Paint.Style.STROKE)
        mStrokePaint.setStrokeWidth(mStrokeWidth.toFloat())
        mStrokePaint.setColor(strokeColor)
        ta.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        val drawable = getDrawable()

        if (drawable !is BitmapDrawable || getWidth() == 0 || getHeight() == 0) {
            return
        }

        if (mRoundedBitmap == null) {
            val scaledBitmap = getScaledBitmap(drawable.getBitmap())
            mRoundedBitmap = getCircleBitmap(scaledBitmap)
        }

        canvas.drawBitmap(mRoundedBitmap, mStrokeWidth.toFloat(), mStrokeWidth.toFloat(), null)
        val x = getWidth() / 2f
        val y = getHeight() / 2f
        val r = Math.min(getWidth() / 2f, getHeight() / 2f) - mStrokeWidth
        canvas.drawCircle(x, y, r, mStrokePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mRoundedBitmap = null
    }

    private fun getScaledBitmap(bitmap: Bitmap): Bitmap {
        val scaledWidth: Int
        val scaledHeight: Int
        if (getWidth() > getHeight()) {
            scaledWidth = getWidth() - 2 * mStrokeWidth
            scaledHeight = (scaledWidth * (getHeight() / getWidth().toFloat())).toInt()
        } else {
            scaledHeight = getHeight() - 2 * mStrokeWidth
            scaledWidth = (scaledHeight * (getWidth() / getHeight().toFloat())).toInt()
        }

        return Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false)
    }

    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(0, 0, bitmap.getWidth(), bitmap.getHeight())
        val rectF = RectF(rect)

        paint.setAntiAlias(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(Color.WHITE)
        canvas.drawOval(rectF, paint)

        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)

        bitmap.recycle()

        return output
    }

    private fun dpToPx(dp: Float): Float {
        val res = getContext().getResources()
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics())
    }
}