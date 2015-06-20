package com.airbnb.net;

import android.net.Uri;

public class Listing {

    public final long id;
    public final CharSequence host;
    public final CharSequence title;
    public final CharSequence description;
    public final Uri imageUri;
    public final Uri hostImageUri;

    public Listing(long id, CharSequence host, CharSequence title, CharSequence description, Uri imageUri, Uri hostImageUri) {
        this.host = host;
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
        this.hostImageUri = hostImageUri;
    }


}
