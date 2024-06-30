package com.mina.mvvm.base.presentation.home


import androidx.fragment.app.viewModels
import codes.core.shared.actionChooser.builder.ActionChooserHelper
import codes.core.shared.actionChooser.model.ChooserAction
import com.core.presentation.R
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.getMyString
import com.core.presentation.extensions.hide
import com.core.presentation.extensions.showMessage
import com.core.presentation.extensions.showPrettyPopUp
import com.mina.base.mvvm.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
  FragmentHomeBinding::inflate
) {

  override val viewModel: HomeViewModel by viewModels()

  private lateinit var actions: ArrayList<ChooserAction>

  override fun FragmentHomeBinding.initializeUI() {
    setUpToolBar()

    setActionsDummyData()
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.home)
    binding.includedToolbar.backIv.hide()
  }

  private fun setActionsDummyData() {
    actions = ArrayList()
    actions.apply {
      add(
        ChooserAction(
          name = "First Option",
          isSelected = true
        )
      )

      add(
        ChooserAction(
          name = "Second Option"
        )
      )

      add(
        ChooserAction(
          name = "Third Option"
        )
      )
    }
  }

  override fun registerListeners() {
    binding.apply {
      btnShowPrettyPopUp.setOnClickListener {
        showPrettyPopUp(
          title = "Hello!",
          content = "Hello to my base MVVM project from my pretty pop up helper",
          positiveButtonText = "Okay",
        )
      }

      btnShowActionChooserPopUp.setOnClickListener {
        showActionChooser()
      }
    }
  }

  private fun showActionChooser() {
    ActionChooserHelper.Builder(childFragmentManager)
      .setPopUpBackground(R.drawable.bg_round_pop_up)
      .setTitle("Choose Option")
      .setTitleColorResource(R.color.colorPrimaryDark)
      .setContent("Choose option from the following options")
      .setContentColorResource(R.color.gray)
      .setActions(actions)
      .setItemClick {
        showMessage(it?.name)
      }
      .create()
  }


}