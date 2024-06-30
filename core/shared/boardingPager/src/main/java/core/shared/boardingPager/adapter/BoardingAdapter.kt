package core.shared.boardingPager.adapter

import android.view.ViewGroup
import core.shared.boardingPager.adapter.BoardingAdapter.BoardingViewHolder
import com.core.presentation.adapters.recycler.CoreListAdapter
import com.core.presentation.adapters.recycler.CoreViewHolder
import com.core.utils.providers.ResourceProvider
import core.shared.boardingPager.data.BoardingModel
import core.shared.boardingPager.databinding.BoardingItemRowBinding

internal class BoardingAdapter(
  private val resourceProvider: ResourceProvider
) : CoreListAdapter<BoardingModel, BoardingViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingViewHolder {
    return BoardingViewHolder(parent)
  }


  inner class BoardingViewHolder(parent: ViewGroup) :
    CoreViewHolder<BoardingModel,BoardingItemRowBinding>(parent,BoardingItemRowBinding::inflate) {

    override fun onBind(item: BoardingModel) {
      binding.apply {
        txtBoardingContent.text = resourceProvider.getString(item.message)
        imgBoardingAnimation.setAnimation(item.boardingAnimImage)
        txtBoardingContent.setTextColor(
          resourceProvider.getColor(item.contentColor)
        )
      }

    }
  }
}