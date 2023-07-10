package com.example.theaudiodbapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.theaudiodbapp.R
import com.example.theaudiodbapp.utils.Helpers

class ResourceLink(context: Context, private val attrs: AttributeSet?): LinearLayout(context, attrs) {

    var linkType: LinkType = LinkType.ARTIST
        set(value) {
            field = value
            when(value) {
                LinkType.ARTIST -> {
                    tvSubtitle.visibility = GONE
                    ivOverviewContainer.radius = Helpers.convertDpToPx(100)
                }
                LinkType.ALBUM -> {
                    tvSubtitle.visibility = VISIBLE
                    ivOverviewContainer.radius = Helpers.convertDpToPx(10)
                }
            }

        }

    private val ivOverviewContainer: CardView
    val ivOverview: ImageView
    val tvTitle: TextView
    val tvSubtitle: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.resource_link, this)

        ivOverview = findViewById(R.id.ivResourceLink)
        ivOverviewContainer = findViewById(R.id.cvIvResourceLink)
        tvTitle = findViewById(R.id.tvTitleResourceLink)
        tvSubtitle = findViewById(R.id.tvSubtitleResourceLink)

        handleAttrs()
    }

    private fun handleAttrs() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ResourceLink)

        val title = typedArray.getString(R.styleable.ResourceLink_title)
        val subtitle = typedArray.getString(R.styleable.ResourceLink_subtitle)
        val image = typedArray.getDrawable(R.styleable.ResourceLink_image)
        linkType = typedArray.getInt(R.styleable.ResourceLink_linkType, 0).let {
            LinkType.values()[it]
        }

        tvTitle.text = title
        tvSubtitle.text = subtitle
        ivOverview.setImageDrawable(image)

        typedArray.recycle()
    }

}

enum class LinkType {
    ARTIST,
    ALBUM
}