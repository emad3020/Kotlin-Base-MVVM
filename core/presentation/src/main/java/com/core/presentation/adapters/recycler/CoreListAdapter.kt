package com.core.presentation.adapters.recycler

import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.recyclerview.widget.ListAdapter
import com.core.presentation.models.BaseModel

abstract class CoreListAdapter<DataModel : BaseModel, ViewHolder : CoreViewHolder<DataModel, *>> :
  ListAdapter<DataModel, ViewHolder>(AdapterDiffCallbacks()), DefaultLifecycleObserver {

  abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    getItem(position)?.let {
      holder.onBind(it)
    }
  }
}