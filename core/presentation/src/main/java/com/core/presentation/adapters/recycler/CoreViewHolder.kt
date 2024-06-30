package com.core.presentation.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.core.presentation.extensions.addRippleEffect

abstract class CoreViewHolder<DataModel, VB : ViewBinding>(
  parent: ViewGroup,
  inflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
  val binding: VB = inflater(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

  init {
    binding.apply {
      root.isClickable = true
      root.addRippleEffect()
    }
  }

  abstract fun onBind(item: DataModel)
}