package com.airbnb.net;

import android.net.Uri;

public class Listing {

    public CharSequence title;
    public CharSequence text;
    public Uri imageUri;
    public Uri hostImageUri;

    public Listing(CharSequence title, CharSequence text, Uri imageUri, Uri hostImageUri) {
        this.title = title;
        this.text = text;
        this.imageUri = imageUri;
        this.hostImageUri = hostImageUri;
    }
}
