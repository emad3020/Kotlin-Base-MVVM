package core.shared.boardingPager.builder

import android.app.Activity
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.lifecycle.DefaultLifecycleObserver
import core.shared.boardingPager.R
import core.shared.boardingPager.data.BoardingModel
import core.shared.boardingPager.presntation.BoardingFragment

class BoardingPagerBuilder private constructor(builder: Builder) : DefaultLifecycleObserver {


  private var boardingList: List<BoardingModel>
  private var isAutoScroll: Boolean = false
  private var backgroundColor: Int? = R.color.colorDarkGray
  private var scrollingDelay: Long = 2500
  private var completeListener: (() -> Unit)? = null


  init {
    boardingList = builder.boardingList
    isAutoScroll = builder.isAutoScroll
    backgroundColor = builder.backgroundColor
    scrollingDelay = builder.scrollingDelay
    completeListener = builder.completeListener
  }


   fun createFragment() = BoardingFragment().apply {
    setButtonColor(backgroundColor)
    setAutoScroll(isAutoScroll)
    setScrollingDelay(scrollingDelay)
    setBoardingData(boardingList)
    setOnComplete(completeListener)
  }


class Builder {

    internal var boardingList: List<BoardingModel> = listOf()
    internal var isAutoScroll : Boolean = false
    @ColorRes
    internal var backgroundColor: Int? = null
    internal var scrollingDelay: Long = 2500
    internal var completeListener: (() -> Unit)? = null

    fun setBoardingList(boardingList: List<BoardingModel>) : Builder {
      this.boardingList = boardingList
      return this
    }

    fun setAutoScroll(isAutoScroll: Boolean) : Builder {
      this.isAutoScroll = isAutoScroll
      return this
    }

    fun setBackgroundColor(@ColorRes backgroundColor: Int) : Builder {
      this.backgroundColor = backgroundColor
      return this
    }

    fun setScrollingDelay(scrollingDelay: Long) : Builder {
      this.scrollingDelay = scrollingDelay
      return this
    }

    fun setOnComplete(onComplete: () -> Unit) : Builder {
      this.completeListener = onComplete
      return this
    }

    fun build() : BoardingPagerBuilder = BoardingPagerBuilder(this)

  }

}