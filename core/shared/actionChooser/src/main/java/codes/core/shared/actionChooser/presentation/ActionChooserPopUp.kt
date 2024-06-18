package codes.core.shared.actionChooser.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import codes.core.shared.actionChooser.R
import codes.core.shared.actionChooser.databinding.PopUpActionChooserBinding
import codes.core.shared.actionChooser.model.ChooserAction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionChooserPopUp : BottomSheetDialogFragment() {

  private var _binding: PopUpActionChooserBinding? = null
  private val binding get() = checkNotNull(_binding)

  private lateinit var actionChooserAdapter: ActionChooserAdapter

  // Pop Up Background
  private var popUpBackground: Int? = null

  // Title
  private var title: String? = null
  private var titleResId: Int? = null

  // Title Color
  private var titleColorResId: Int? = null
  private var titleColor: Int? = null

  // Content
  private var content: String? = null
  private var contentResId: Int? = null

  // Content Color
  private var contentColorResId: Int? = null
  private var contentColor: Int? = null

  // Actions
  private var actions: ArrayList<ChooserAction>? = null

  // Item CLick
  private var itemClick: ((action: ChooserAction?) -> Unit)? = null

  override fun getTheme(): Int {
    return R.style.ChooserDialogAnimation
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = PopUpActionChooserBinding.inflate(inflater, container, false)

    val params = dialog?.window?.attributes
    params?.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

    dialog?.window?.apply {
      attributes = params
      requestFeature(Window.FEATURE_NO_TITLE)
      setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    dialog?.setCanceledOnTouchOutside(true)

    return binding.root
  }

  override fun onResume() {
    dialog?.window?.apply {
      setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
      )

      setGravity(Gravity.BOTTOM)
    }

    super.onResume()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setUpViews()
  }

  private fun setUpViews() {
    setUpPopUpBackground()

    setUpPopUpTitle()

    setUpPopUpTitleColor()

    setUpPopUpContent()

    setUpPopUpContentColor()

    setUpActionsRecyclerView()

//    setUpCloseButton()
//
//    setUpCloseButtonTextColor()
//
//    setUpCloseButtonBackground()
//
//    handleClickListeners()
  }

  private fun setUpPopUpBackground() {
    popUpBackground?.let {
      binding.popUpContainer.background = ContextCompat.getDrawable(requireContext(), it)
    } ?: run {
      binding.popUpContainer.background =
        ContextCompat.getDrawable(requireContext(), R.drawable.bg_chooser_pop_up)
    }
  }

  private fun setUpPopUpTitle() {
    titleResId?.let {
      binding.tvTitle.text = resources.getString(it)
      binding.tvTitle.visibility = View.VISIBLE
    } ?: run {
      if (title != null && title?.isNotEmpty() == true) {
        binding.tvTitle.text = title
        binding.tvTitle.visibility = View.VISIBLE
      } else {
        binding.tvTitle.visibility = View.GONE
      }
    }
  }

  private fun setUpPopUpTitleColor() {
    titleColorResId?.let {
      binding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), it))
    } ?: run {
      titleColor?.let {
        binding.tvTitle.setTextColor(it)
      } ?: run {
        binding.tvTitle.setTextColor(
          ContextCompat.getColor(requireContext(), R.color.colorDarkGray)
        )
      }
    }
  }

  private fun setUpPopUpContent() {
    contentResId?.let {
      binding.tvHint.text = resources.getString(it)
      binding.tvHint.visibility = View.VISIBLE
    } ?: run {
      if (content != null && content?.isNotEmpty() == true) {
        binding.tvHint.text = content
        binding.tvHint.visibility = View.VISIBLE
      } else {
        binding.tvHint.visibility = View.GONE
      }
    }
  }

  private fun setUpPopUpContentColor() {
    contentColorResId?.let {
      binding.tvHint.setTextColor(ContextCompat.getColor(requireContext(), it))
    } ?: run {
      contentColor?.let {
        binding.tvHint.setTextColor(it)
      } ?: run {
        binding.tvHint.setTextColor(
          ContextCompat.getColor(requireContext(), R.color.colorGray)
        )
      }
    }
  }

  private fun setUpActionsRecyclerView() {
    actionChooserAdapter = ActionChooserAdapter(
      itemClick = { onActionSelected(it) }
    )
    binding.recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = actionChooserAdapter.apply { submitList(actions) }
    }
  }

  private fun onActionSelected(action: ChooserAction?) {
    actions?.forEach { it.isSelected = false }
    action?.isSelected = true

    itemClick?.invoke(action)

    dismiss()
  }

  fun setPopUpBackground(@DrawableRes popUpBackground: Int?) {
    this.popUpBackground = popUpBackground
  }

  fun setIsCancelable(isCancelable: Boolean?) {
    this.isCancelable = isCancelable ?: true
  }

  fun setTitle(title: String?) {
    this.title = title
  }

  fun setTitle(@StringRes titleResId: Int?) {
    this.titleResId = titleResId
  }

  fun setTitleColorResource(@ColorRes titleColorResId: Int?) {
    this.titleColorResId = titleColorResId
  }

  fun setTitleColor(titleColor: Int?) {
    this.titleColor = titleColor
  }

  fun setContent(content: String?) {
    this.content = content
  }

  fun setContent(@StringRes contentResId: Int?) {
    this.contentResId = contentResId
  }

  fun setContentColorResource(@ColorRes contentColorResId: Int?) {
    this.contentColorResId = contentColorResId
  }

  fun setContentColor(contentColor: Int?) {
    this.contentColor = contentColor
  }

  fun setActions(actions: ArrayList<ChooserAction>?) {
    this.actions = actions
  }
  fun setItemClick(itemClick: ((action: ChooserAction?) -> Unit)?) {
    this.itemClick = itemClick
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}