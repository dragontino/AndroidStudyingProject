package ru.dragontino.androidtestproject.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.util.TypedValueCompat
import com.google.android.material.button.MaterialButton
import ru.dragontino.androidtestproject.utils.extractParcelable
import ru.dragontino.androidtestproject.utils.getAttributeColor

class FillingByClickRectangle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    private companion object {
        val possibleFilledColors = arrayOf(
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.YELLOW
        )

        private const val SAVED_COLOR_KEY = "FilledAreaColor"
        private const val FILLED_AREA_RATIO_KEY = "FilledAreaRatio"
        private const val INSTANCE_STATE_KEY = "InstanceState"
    }

    private val filledAreaPaint = Paint()
    private val blankPaint = Paint().apply {
        color = context.getAttributeColor(com.google.android.material.R.attr.colorOnBackground)
    }
    private val borderPaint = Paint().apply {
        color = context.getAttributeColor(androidx.appcompat.R.attr.colorPrimary)
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 7.toPx()
    }

    private var filledAreaRatio = 0f


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                performClick()
                true
            }
            MotionEvent.ACTION_UP -> {
                performClick()
                filledAreaPaint.color = getNewColor(filledAreaPaint.color)
                filledAreaRatio = (filledAreaRatio + .1f) % 1f + .1f
                invalidate()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }


    override fun performClick(): Boolean {
        return super.performClick()
    }


    override fun onDraw(canvas: Canvas) {
        val blankHeight = height * (1 - filledAreaRatio)
        val width = width.toFloat()
        val height = height.toFloat()

        canvas.drawRect(
            0f, 0f,
            width, blankHeight,
            blankPaint
        )
        canvas.drawRect(
            0f, blankHeight,
            width, height,
            filledAreaPaint
        )

        canvas.drawLine(
            0f, 0f,
            width, 0f,
            borderPaint
        )
        canvas.drawLine(
            width, 0f,
            width, height,
            borderPaint
        )
        canvas.drawLine(
            width, height,
            0f, height,
            borderPaint
        )
        canvas.drawLine(
            0f, height,
            0f, 0f,
            borderPaint
        )
    }


    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putInt(SAVED_COLOR_KEY, filledAreaPaint.color)
            putFloat(FILLED_AREA_RATIO_KEY, filledAreaRatio)
            putParcelable(INSTANCE_STATE_KEY, super.onSaveInstanceState())
        }
    }


    override fun onRestoreInstanceState(state: Parcelable?) {
        with(state as Bundle) {
            filledAreaPaint.color = getInt(SAVED_COLOR_KEY)
            filledAreaRatio = getFloat(FILLED_AREA_RATIO_KEY)
            super.onRestoreInstanceState(extractParcelable(INSTANCE_STATE_KEY))
        }
    }


    private tailrec fun getNewColor(oldColor: Int): Int {
        return when (val newColor = possibleFilledColors.random()) {
            oldColor -> getNewColor(oldColor)
            else -> newColor
        }
    }


    private fun Int.toPx(): Float = TypedValueCompat.dpToPx(
        /* dpValue = */ toFloat(),
        /* metrics = */ resources.displayMetrics
    )
}