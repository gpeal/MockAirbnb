package com.airbnb.net

import android.net.Uri


public data class ListingItem(
        val id: Long,
        val host: CharSequence,
        val title: CharSequence,
        val description: CharSequence,
        val imageUri: Uri?,
        val hostImageUri: Uri?)
