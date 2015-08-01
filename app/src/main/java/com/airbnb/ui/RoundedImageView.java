package com.airbnb.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.airbnb.R;

/**
 * Custom view that displays a circular profile picture image with a stroke around it.
 */
public class RoundedImageView extends ImageView {

    private final int mStrokeWidth;
    private final Paint mStrokePaint = new Paint();

    private Bitmap mRoundedBitmap;

    public RoundedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
        int strokeColor = ta.getColor(R.styleable.RoundedImageView_strokeColor, Color.WHITE);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.RoundedImageView_strokeWidth, (int) dpToPx(2));
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setColor(strokeColor);
        ta.recycle();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        Drawable drawable = getDrawable();

        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        if (mRoundedBitmap == null) {
            Bitmap bitmap =  ((BitmapDrawable) drawable).getBitmap();
            int scaledWidth;
            int scaledHeight;
            if (getWidth() > getHeight()) {
                scaledWidth = getWidth() - 2 * mStrokeWidth;
                scaledHeight = (int) (scaledWidth * (getHeight() / (float) getWidth()));
            } else {
                scaledHeight = getHeight() - 2 * mStrokeWidth;
                scaledWidth = (int) (scaledHeight * (getWidth() / (float) getHeight()));
            }

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);


            mRoundedBitmap = getCircleBitmap(scaledBitmap);
        }

        canvas.drawBitmap(mRoundedBitmap, mStrokeWidth, mStrokeWidth, null);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth() / 2, getHeight() / 2) - mStrokeWidth, mStrokePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRoundedBitmap = null;
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.WHITE);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    private float dpToPx(float dp) {
        Resources res = getContext().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}