package com.example.theaudiodbapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.theaudiodbapp.R

class TrackItem(context: Context, private val attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val tvIndex: TextView
    private val tvTitle: TextView
    private val vDivider: View

    init {
        LayoutInflater.from(context).inflate(R.layout.track_item, this)

        tvIndex = findViewById(R.id.tvIndexTrackItem)
        tvTitle = findViewById(R.id.tvTitleTrackItem)
        vDivider = findViewById(R.id.vDividerTrackItem)

        handleAttrs()
    }

    private fun handleAttrs() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleRow)

        val index = typedArray.getInt(R.styleable.TitleRow_indexRow, 0)
        val title = typedArray.getString(R.styleable.TitleRow_titleRow)

        tvIndex.text = index.toString()
        tvTitle.text = title
    }

    fun setIndex(index: Int) { tvIndex.text = index.toString() }

    fun setTitle(title: String) { tvTitle.text = title }

    fun setDividerVisible(visible: Boolean) {
        vDivider.visibility = if (visible) VISIBLE else GONE
    }

}