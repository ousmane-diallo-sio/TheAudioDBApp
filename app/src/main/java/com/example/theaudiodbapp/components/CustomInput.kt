package com.example.theaudiodbapp.components

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.theaudiodbapp.R

class CustomInput(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    val et: EditText
    private val btnCancel: ImageView
    var onTextChange: TextWatcher? = null
        set(value) {
            et.removeTextChangedListener(field)
            et.addTextChangedListener(value)
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_input, this)

        et = findViewById(R.id.etCustomInput)
        btnCancel = findViewById(R.id.btnCancelCustomInput)

        setListeners()
    }

    private fun setListeners() {
        btnCancel.setOnClickListener { et.text.clear() }
    }

}