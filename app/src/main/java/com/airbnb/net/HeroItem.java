package com.airbnb.net;

import android.net.Uri;

public class HeroItem {

    public CharSequence title;
    public CharSequence text;
    public Uri imageUri;

    public HeroItem(CharSequence title, CharSequence text, Uri imageUri) {
        this.title = title;
        this.text = text;
        this.imageUri = imageUri;
    }
}
