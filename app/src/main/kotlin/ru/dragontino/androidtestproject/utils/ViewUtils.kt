package ru.dragontino.androidtestproject.utils

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat

internal fun View.applyWindowInsets(mask: Int) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val padding = insets.getInsets(mask)
        v.setPadding(padding.left, padding.top, padding.right, padding.bottom)
        insets
    }
}


@Suppress("DEPRECATION")
internal inline fun <reified T : Parcelable> Bundle.extractParcelable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }
}


internal fun Context.getAttributeColor(@AttrRes themeAttrId: Int): Int {
    val outValue = TypedValue()
    theme.resolveAttribute(themeAttrId, outValue, true)
    return ContextCompat.getColor(this, outValue.resourceId)
}