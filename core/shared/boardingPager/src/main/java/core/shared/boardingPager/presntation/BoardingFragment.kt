package core.shared.boardingPager.presntation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout.LayoutParams
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.core.utils.providers.ResourceProvider
import core.shared.boardingPager.R
import core.shared.boardingPager.adapter.BoardingAdapter
import core.shared.boardingPager.data.BoardingModel
import core.shared.boardingPager.databinding.FragmentBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BoardingFragment : Fragment() {


  @Inject
  lateinit var resourceProvider: ResourceProvider
  private var _binding: FragmentBoardingBinding? = null

  // Next Button Color
  private var buttonColor: Int? = null
  private var isAutoScroll: Boolean = false
  private var scrollDelay: Long = 2500
  private var isFirstScroll = true
  private var boardingList: List<BoardingModel> = listOf()
  private var completeListener: (() -> Unit)? = null

  val binding: FragmentBoardingBinding
    get() = checkNotNull(_binding)

  private var adapter: BoardingAdapter? = null
  private var pageChangedCallback: OnPageChangeCallback ? = null
  private var currentItem = 0

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentBoardingBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setUpViews()
    createPagerIndicator()
    setAutoScrolling()
  }

  private fun setUpViews() {

    buttonColor?.let {
      binding.btnNext.backgroundTintList = resourceProvider.getColorStateList(it)
    }

    binding.apply {
      btnNext.setOnClickListener {
        if (boardingList.count() - 1 == currentItem) {
          completeListener?.invoke()
        } else {
          currentItem++
        }

        boardingPager.setCurrentItem(currentItem, true)
      }
    }

    adapter = BoardingAdapter(resourceProvider)

    adapter?.submitList(boardingList)
    binding.boardingPager.adapter = adapter
    pageChangedCallback = object : OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        currentItem = position
        if (isFirstScroll) {
          isFirstScroll = false
          return
        }
        createPagerIndicator()
        binding.btnNext.text = if (position != boardingList.count() - 1) {
          resourceProvider.getString(R.string.next)
        } else {
          resourceProvider.getString(R.string.open_app)
        }
      }
    }
    pageChangedCallback?.let {
      binding.boardingPager.registerOnPageChangeCallback(it)
    }

  }

  private fun createPagerIndicator() {
    binding.pagerIndicatorContainer.removeAllViews()

    boardingList.forEachIndexed { index, boardingModel ->
      val textView = TextView(requireContext())
      textView.apply {
        val layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(5, 0, 5, 7)
        this.layoutParams = layoutParams
        text = "•"
        textSize = if (index == currentItem) 45f else 28f
        includeFontPadding = false
        if (index == currentItem) {
          setTextColor(resourceProvider.getColor(boardingModel.indicatorActiveColor))
        } else {
          setTextColor(resourceProvider.getColor(boardingModel.indicatorInactiveColor))
        }
      }

      binding.pagerIndicatorContainer.addView(textView)
      binding.pagerIndicatorContainer.bringToFront()
    }
  }

  private fun setAutoScrolling() {
    if (isAutoScroll) {
      lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
          while (true) {
            delay(scrollDelay)
            if (boardingList.count() - 1 == currentItem) {
              currentItem = 0
            } else {
              currentItem++
            }

            if (currentItem == 0)
              binding.boardingPager.setCurrentItem(currentItem, false)
            else
              binding.boardingPager.setCurrentItem(currentItem, true)
          }
        }
      }
    }
  }

  fun setAutoScroll(isAutoScroll: Boolean) {
    this.isAutoScroll = isAutoScroll
  }

  fun setScrollingDelay(scrollDelay: Long) {
    this.scrollDelay = scrollDelay
  }

  fun setButtonColor(@ColorRes colorRes: Int?) {
    buttonColor = colorRes
  }

  fun setBoardingData(list: List<BoardingModel>) {
    boardingList = list
  }

  fun setOnComplete(onComplete: (() -> Unit)?) {
    this.completeListener = onComplete
  }

  override fun onDestroy() {
    adapter = null
    pageChangedCallback?.let {
      binding.boardingPager.unregisterOnPageChangeCallback(it)
    }
    pageChangedCallback = null
    super.onDestroy()
  }

}