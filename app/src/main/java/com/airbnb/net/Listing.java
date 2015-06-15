package com.airbnb.net;

import android.net.Uri;

public class Listing {

    public final long id;
    public final CharSequence title;
    public final CharSequence text;
    public final Uri imageUri;
    public final Uri hostImageUri;

    public Listing(long id, CharSequence title, CharSequence text, Uri imageUri, Uri hostImageUri) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageUri = imageUri;
        this.hostImageUri = hostImageUri;
    }


}
