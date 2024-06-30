package com.core.presentation.adapters.recycler

import androidx.recyclerview.widget.DiffUtil
import com.core.presentation.models.BaseModel

class AdapterDiffCallbacks<DataModel : BaseModel>() : DiffUtil.ItemCallback<DataModel>() {
  override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
    return oldItem.itemId == newItem.itemId
  }

  override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
    return oldItem.hashCode() == newItem.hashCode()
  }
}