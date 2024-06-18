package codes.core.shared.actionChooser.presentation

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.core.shared.actionChooser.R
import codes.core.shared.actionChooser.databinding.ItemChooserActionBinding
import codes.core.shared.actionChooser.model.ChooserAction

internal class ActionChooserAdapter(
  private var itemClick: ((item: ChooserAction?) -> Unit)
) :
  ListAdapter<ChooserAction, ActionChooserAdapter.ActionChooserViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChooserAction>() {
      override fun areItemsTheSame(oldItem: ChooserAction, newItem: ChooserAction): Boolean =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: ChooserAction, newItem: ChooserAction): Boolean =
        oldItem == newItem
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ActionChooserViewHolder {
    val root = LayoutInflater.from(parent.context).inflate(
      R.layout.item_chooser_action,
      parent,
      false
    )
    return ActionChooserViewHolder(ItemChooserActionBinding.bind(root))
  }

  override fun onBindViewHolder(holder: ActionChooserViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  @SuppressLint("RestrictedApi")
  inner class ActionChooserViewHolder(private val itemBinding: ItemChooserActionBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: ChooserAction) {
      itemBinding.tvItem.apply {
        text = item.name
        if (item.isSelected) {
          isCheckable = true
          isEnabled = false
          icon = ContextCompat.getDrawable(context, R.drawable.ic_check)
          iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorYellow))
        } else {
          icon = null
        }

        setOnClickListener {
          itemClick.invoke(
            if (item.isSelected) {
              null
            } else {
              item
            }
          )
        }
      }
    }
  }
}