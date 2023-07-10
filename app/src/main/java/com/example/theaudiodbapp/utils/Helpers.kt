package com.example.theaudiodbapp.utils

import android.content.res.Resources

object Helpers {

    fun convertDpToPx(px: Int): Float {
        return (px * Resources.getSystem().displayMetrics.density)
    }

}