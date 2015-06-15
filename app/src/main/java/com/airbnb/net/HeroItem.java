package com.airbnb.net;

import android.net.Uri;
import android.support.annotation.ColorInt;

public class HeroItem {

    public final long id;
    public final CharSequence title;
    public final CharSequence text;
    public final Uri imageUri;
    @ColorInt public final int textBackgroundColor;


    public HeroItem(long id, CharSequence title, CharSequence text, Uri imageUri, @ColorInt int textBackgroundColor) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageUri = imageUri;
        this.textBackgroundColor = textBackgroundColor;
    }
}
