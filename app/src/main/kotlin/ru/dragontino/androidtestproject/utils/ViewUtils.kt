package ru.dragontino.androidtestproject.utils

import android.view.View
import androidx.core.view.ViewCompat

internal fun View.applyWindowInsets(mask: Int) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val padding = insets.getInsets(mask)
        v.setPadding(padding.left, padding.top, padding.right, padding.bottom)
        insets
    }
}