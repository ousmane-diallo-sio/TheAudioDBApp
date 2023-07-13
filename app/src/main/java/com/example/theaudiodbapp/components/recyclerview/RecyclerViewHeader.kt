package com.example.theaudiodbapp.components.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.theaudiodbapp.R

class RecyclerViewHeader(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val tvHeader: TextView
    var headerType: HeaderType = HeaderType.ARTISTS
        set(value) {
            field = value
            tvHeader.text = value.getStringValue(context)
        }

    constructor(context: Context, header: HeaderType) : this(context, null) {
        headerType = header
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.recycler_view_header, this)

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        tvHeader = findViewById(R.id.tvRecyclerViewHeader)
    }

    @SuppressLint("SetTextI18n")
    fun addCounter(context: Context, count: Int) {
        tvHeader.text = "${headerType.getStringValue(context)} ($count)"
        Log.w("RecyclerViewHeader", "addCounter: ${tvHeader.text}")
    }
}

enum class HeaderType {
    ARTISTS,
    ALBUMS,
    TITLES,
    POPULAR_TITLES;

    fun getStringValue(context: Context): String {
        return when (this) {
            ARTISTS -> context.getString(R.string.artists)
            ALBUMS -> context.getString(R.string.albums)
            TITLES -> context.getString(R.string.titles)
            POPULAR_TITLES -> context.getString(R.string.most_popular_titles)
        }
    }
}