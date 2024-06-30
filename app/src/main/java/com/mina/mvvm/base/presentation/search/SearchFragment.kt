package com.mina.mvvm.base.presentation.search

import androidx.fragment.app.viewModels
import com.core.presentation.R
import com.core.presentation.base.BaseFragment
import com.core.presentation.extensions.getMyString
import com.core.presentation.extensions.hide
import com.mina.base.mvvm.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
  FragmentSearchBinding::inflate
) {

  override val viewModel: SearchViewModel by viewModels()

  override fun FragmentSearchBinding.initializeUI() {
    setUpToolBar()
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.search)
    binding.includedToolbar.backIv.hide()
  }
}