package com.mina.mvvm.base.presentation.intro.tutorial

import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.navigateSafe
import com.mina.base.mvvm.R
import com.mina.base.mvvm.databinding.FragmentTutorialBinding
import core.shared.boardingPager.builder.BoardingPagerBuilder
import core.shared.boardingPager.data.BoardingModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialFragment : BaseFragment<FragmentTutorialBinding, TutorialViewModel>(
  FragmentTutorialBinding::inflate
) {

  override val viewModel: TutorialViewModel by viewModels()

  override fun FragmentTutorialBinding.initializeUI() {
    setUpAppTutorial()
  }

  private fun setUpAppTutorial() {
    val boardingData = ArrayList<BoardingModel>()
    boardingData.apply {
      add(
        BoardingModel(
          message = R.string.first_boarding_message,
          boardingAnimImage = R.raw.lottie_anime,
          contentColor = com.core.presentation.R.color.colorAccent,
          indicatorActiveColor = com.core.presentation.R.color.colorAccent
        )
      )

      add(
        BoardingModel(
          message = R.string.first_boarding_message,
          boardingAnimImage = R.raw.lottie_anime,
          contentColor = com.core.presentation.R.color.colorPrimary,
          indicatorActiveColor = com.core.presentation.R.color.colorAccent
        )
      )

      add(
        BoardingModel(
          message = R.string.first_boarding_message,
          boardingAnimImage = R.raw.lottie_anime,
          contentColor = com.core.presentation.R.color.colorPrimaryDark,
          indicatorActiveColor = com.core.presentation.R.color.colorAccent
        )
      )
    }

    val boardingBuilder = BoardingPagerBuilder.Builder()
      .setBoardingList(boardingData)
      .setAutoScroll(true)
      .setBackgroundColor(com.core.presentation.R.color.colorPrimaryDark)
      .setOnComplete {
        navigateSafe(
          TutorialFragmentDirections.actionOpenIntroFragment()
        )
      }.build()

    requireActivity().supportFragmentManager.commit {
      setReorderingAllowed(true)
      replace(
        R.id.tutorial_container,
        boardingBuilder.createFragment(),
        "Boarding Fragment"
      )
    }
  }
}