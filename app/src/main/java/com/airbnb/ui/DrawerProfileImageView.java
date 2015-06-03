package com.airbnb.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.airbnb.R;

/**
 * Custom view that displays a circular profile picture image with a stroke around it.
 */
public class DrawerProfileImageView extends View {

    private final int mDrawableSize;
    private final Paint mBackgroundPaint;
    private final Matrix mTranslationMatrix = new Matrix();

    private Bitmap mBitmap;
    private Paint mBitmapPaint;

    public DrawerProfileImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawerProfileImageView);
        int resId = ta.getResourceId(R.styleable.DrawerProfileImageView_drawable, 0);
        setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resId));

        mDrawableSize = ta.getDimensionPixelSize(R.styleable.DrawerProfileImageView_drawableSize, 0);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        int strokeColor = ta.getColor(R.styleable.DrawerProfileImageView_strokeColor, Color.WHITE);
        mBackgroundPaint.setColor(strokeColor);
        int strokeWidth = ta.getDimensionPixelSize(R.styleable.DrawerProfileImageView_strokeWidth, 0);
        mBackgroundPaint.setStrokeWidth(strokeWidth);
        ta.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null) {
            return;
        }

        if (mBitmapPaint == null) {
            float aspectRatio = mBitmap.getHeight() / (float) mBitmap.getWidth();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                    mBitmap, mDrawableSize, (int) (mDrawableSize * aspectRatio), true);
            mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            BitmapShader shader = new BitmapShader(
                    scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mBitmapPaint.setShader(shader);
        }

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int r = mDrawableSize / 2;
        canvas.drawCircle(cx, cy, r, mBackgroundPaint);
        int dx = getWidth() / 2 - mDrawableSize / 2;
        int dy = getHeight() / 2 - mDrawableSize / 2;
        mTranslationMatrix.setTranslate(dx, dy);
        mBitmapPaint.getShader().setLocalMatrix(mTranslationMatrix);
        canvas.drawCircle(cx, cy, r, mBitmapPaint);
    }

    private void setImageBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        mBitmapPaint = null;
        invalidate();
    }
}
