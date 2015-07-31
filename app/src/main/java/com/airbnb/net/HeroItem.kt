package com.airbnb.net

import android.net.Uri
import android.support.annotation.ColorInt

public class HeroItem(
        val id: Long,
        val title: CharSequence,
        val text: CharSequence?,
        val imageUri: Uri?,
        ColorInt val textBackgroundColor: Int)
