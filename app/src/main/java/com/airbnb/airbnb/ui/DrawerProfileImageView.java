package com.airbnb.airbnb.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.airbnb.airbnb.R;

public class DrawerProfileImageView extends View {

    private final int mDrawableSize;
    private final Paint mBackgroundPaint;

    private Paint mBitmapPaint;

    public DrawerProfileImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawerProfileImageView);
        int resId = ta.getResourceId(R.styleable.DrawerProfileImageView_drawable, 0);
        setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resId));

        mDrawableSize = ta.getDimensionPixelSize(R.styleable.DrawerProfileImageView_drawableSize, 0);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundPaint.setColor(ta.getColor(R.styleable.DrawerProfileImageView_strokeColor, Color.WHITE));
        mBackgroundPaint.setStrokeWidth(ta.getDimensionPixelSize(R.styleable.DrawerProfileImageView_strokeWidth, 0));
        Log.d("Airbnb", "Background paint width " + mBackgroundPaint.getStrokeWidth() + "\tColor: " + mBackgroundPaint.getColor());
        ta.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapPaint == null) {
            return;
        }

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int r = mDrawableSize / 2;
        canvas.drawCircle(cx, cy, r, mBackgroundPaint);
        // TODO: this shader doesn't currently draw the bitmap correctly
        canvas.drawCircle(cx, cy, r, mBitmapPaint);
    }

    private void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            mBitmapPaint = null;
            return;
        }
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint.setShader(shader);
        invalidate();
    }
}
