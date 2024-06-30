package com.core.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.util.AttributeSet
import android.view.View
import androidx.core.text.clearSpans
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.core.presentation.R
import com.core.presentation.listeners.ViewStatusListener
import com.google.android.material.button.MaterialButton
import kotlin.math.max

class CoreButton : MaterialButton, DefaultLifecycleObserver, ViewStatusListener {

  private var loadingProgressDrawable: CircularProgressDrawable? = null
  private var spannableText: SpannableString? =null
  private var buttonText: String? =null
  private var loadingText: String? = ""
  private var loadingColor: Int? = null
  private var onClickCallbacks: OnClickListener? = null

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    setUpCustomAttr(attrs)
  }

  constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr) {
    setUpCustomAttr(attrs)
  }

  private fun setUpCustomAttr(attrs: AttributeSet) {
    val a = context.theme.obtainStyledAttributes(
      attrs,
      R.styleable.CoreButton,
      0, 0
    )

    try {
      loadingText = a.getString(R.styleable.CoreButton_loadingText)
      loadingColor = a.getColor(R.styleable.CoreButton_loadingColor, Color.WHITE)

      if (loadingText == null)
        loadingText = context.resources.getString(R.string.loading)
      loadingText += " "
    } finally {
      a.recycle()
    }
  }

  override fun setOnClickListener(onClickListener: View.OnClickListener?) {
    onClickCallbacks = onClickListener
    super.setOnClickListener(onClickCallbacks)
  }

  private fun initDrawableSpan(): DynamicDrawableSpan  = object : DynamicDrawableSpan() {
    override fun getSize(
      paint: Paint,
      text: CharSequence?,
      start: Int,
      end: Int,
      fontMetricsInt: Paint.FontMetricsInt?
    ): Int {
      val rect = loadingProgressDrawable?.bounds
      val fontMetrics = paint.fontMetrics

      // Calculate the font height
      val fontHeight = fontMetrics.bottom - fontMetrics.top

      // Adjust the drawable size to be less than the font height
      val drawableSize = max(fontHeight, (rect?.bottom?.minus(rect.top)?.toFloat() ?: 0f))

      val centerY = fontMetrics.top + fontHeight / 2

      //adjust font metrics to fit the drawable size
      if (fontMetricsInt != null) {
        fontMetricsInt.ascent = (centerY - drawableSize / 2).toInt()
        fontMetricsInt.descent = (centerY + drawableSize / 2).toInt()
        fontMetricsInt.top = fontMetricsInt.ascent
        fontMetricsInt.bottom = fontMetricsInt.descent
      }

      return rect?.width()?.plus(16) ?: 0
    }

    override fun draw(
      canvas: Canvas,
      text: CharSequence?,
      start: Int,
      end: Int,
      x: Float,
      top: Int,
      y: Int,
      bottom: Int,
      paint: Paint
    ) {
      canvas.save()
      val fontMetrics = paint.fontMetrics

      // calculate the font height which will be the drawable size
      val fontHeight = fontMetrics.bottom - fontMetrics.top

      // adjust canvas vertically to draw drawable on text vertical center
      val centerY = y + fontMetrics.bottom - fontHeight / 2
      val transY = centerY - (loadingProgressDrawable?.bounds?.height()?.toFloat() ?: 0f) / 2

      // adjust canvas horizontally to draw drawable with defined margin from text
      canvas.translate(x + 16, transY)
      loadingProgressDrawable?.draw(canvas)
      canvas.restore()
    }
    override fun getDrawable() = loadingProgressDrawable
  }

  private fun setButtonLoading() {
    if (loadingProgressDrawable == null) {
      loadingProgressDrawable = CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.DEFAULT)
        setColorSchemeColors(loadingColor ?: Color.WHITE)
      }
      val size = loadingProgressDrawable?.centerRadius?.plus(
        loadingProgressDrawable?.strokeWidth?.times(2) ?: 0f
      )?.toInt() ?: 0

      loadingProgressDrawable?.setBounds(0,0,size,size)
      loadingProgressDrawable?.callback = object : Drawable.Callback {
        override fun invalidateDrawable(who: Drawable) {
          this@CoreButton.invalidate()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}

        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
      }
    }

    val dynamicDrawableSpan = initDrawableSpan()
    spannableText = SpannableString(loadingText)
    spannableText?.setSpan(dynamicDrawableSpan, spannableText?.length?.minus(1)!!,
      spannableText?.length ?: 0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
  }

  override fun showLoading() {
    super.setOnClickListener(null)
    setButtonLoading()
    buttonText = text.toString()
    text = spannableText
    loadingProgressDrawable?.start()
  }

  override fun hideLoading() {
    if(loadingProgressDrawable?.isRunning == true){
      loadingProgressDrawable?.stop()
      buttonText?.let {
        text = it
      }
      onClickCallbacks?.let {
        super.setOnClickListener(it)
      }
    }
  }

  override fun onDestroy(owner: LifecycleOwner) {
    onClickCallbacks = null
    loadingProgressDrawable?.stop()
    loadingProgressDrawable = null
    loadingText = null
    spannableText?.clearSpans()
    buttonText = null
    super.onDestroy(owner)
  }
}