package codes.core.shared.prettyPopUp.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import codes.core.shared.prettyPopUp.R
import codes.core.shared.prettyPopUp.databinding.PrettyPopUpBinding

class PrettyPopUp : DialogFragment() {
  private var _binding : PrettyPopUpBinding? = null
  private val binding
    get() =  checkNotNull(_binding)

  // Close Button
  private var dialogIcon: Drawable? = null

  // Title
  private var title: String? = null

  // Title Color
  private var titleColorResId: Int? = null

  // Content
  private var content: String? = null

  // Content Color
  private var contentColorResId: Int? = null

  // Positive Button Text
  private var positiveButtonText: String? = null

  // Neutral Button Text
  private var neutralButtonText: String? = null

  // Neutral Button Text Color
  private var neutralButtonTextColorResId: Int = R.color.colorBlack

  // Neutral Button Click
  private var neutralButtonClick: ((DialogFragment) -> Unit)? = null

  // Positive Button Text Color
  private var positiveButtonTextColorResId: Int = R.color.colorBlack

  // Positive Button Click
  private var positiveButtonClick: ((DialogFragment) -> Unit)? = null

  // Negative Button Text
  private var negativeButtonText: String? = null

  // Negative Button Text Color
  private var negativeButtonTextColorResId: Int = R.color.colorDarkRed

  // Negative Button Click
  private var negativeButtonClick: ((DialogFragment) -> Unit)? = null

  // Animation File
  private var animationFileNameRawResId: Int? = null

  override fun getTheme() = R.style.PrettyDialogAnimation

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = PrettyPopUpBinding.inflate(inflater, container, false)

    dialog?.window?.apply {
      val params = attributes
      params.gravity = Gravity.CENTER
      attributes = params
      requestFeature(Window.FEATURE_NO_TITLE)
      setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    return binding.root
  }

  override fun onResume() {
    dialog?.window?.apply {
      setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
      setGravity(Gravity.CENTER)
    }

    super.onResume()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setUpViews()
  }

  private fun setUpViews() {
    setupTitle()

    setupDialogIcon()

    setupContent()

    setupPositiveButton()

    setupNegativeButton()

    setupNeutralButton()

    handleClickListeners()

    setupAnimationView()
  }

  private fun setupTitle() {
    setUpPopUpTitleColor()
    binding.apply {
      binding.txtDialogTitle.text = title
      binding.txtDialogTitle.visibility = View.VISIBLE
    }
  }

  private fun setUpPopUpTitleColor() {
    binding.txtDialogTitle.setTextColor(
      ContextCompat.getColor(
        requireContext(),
        titleColorResId ?: R.color.colorDarkGray
      )
    )
  }

  private fun setupContent() {
    setupContentColor()
    binding.apply {
      txtDialogContent.text = content
      txtDialogContent.visibility = View.VISIBLE
    }
  }

  private fun setupDialogIcon() {
    dialogIcon?.let {
      binding.apply {
        imgDialogIcon.visibility = View.VISIBLE
        imgAnimation.visibility = View.GONE
        imgDialogIcon.setImageDrawable(it)
      }
    }
  }

  private fun setupNeutralButton() {
    setupNeutralButtonColor()
    neutralButtonText?.let {
      binding.btnNeutral.apply {
        visibility = View.VISIBLE
        text = it
      }
    }
  }

  private fun setupAnimationView() {
    animationFileNameRawResId?.let {
      binding.apply {
        imgDialogIcon.visibility = View.GONE
        imgAnimation.visibility = View.VISIBLE
        imgAnimation.setAnimation(it)
      }
    }
  }

  private fun setupNeutralButtonColor() {
    binding.apply {
      btnNeutral.setTextColor(
        ContextCompat.getColor(
          requireContext(),
          neutralButtonTextColorResId
        )
      )
    }
  }

  private fun setupContentColor() {
    binding.apply {
      binding.txtDialogContent.setTextColor(
        ContextCompat.getColor(
          requireContext(),
          contentColorResId ?: R.color.colorGray
        )
      )
    }
  }

  private fun setupPositiveButton() {
    setupPositiveButtonTextColor()
    binding.btnAccept.text = positiveButtonText ?: resources.getText(
      android.R.string.ok
    )
  }

  private fun setupPositiveButtonTextColor() {
    binding.apply {
      btnAccept.setTextColor(
        ContextCompat.getColor(
          requireContext(),
          positiveButtonTextColorResId
        )
      )
    }
  }

  private fun setupNegativeButton() {
    negativeButtonText?.let {
      setupNegativeButtonTextColor()
      binding.btnCancel.text = it
    }
  }

  private fun setupNegativeButtonTextColor() {
    binding.apply {
      btnCancel.setTextColor(
        ContextCompat.getColor(
          requireContext(),
          negativeButtonTextColorResId
        )
      )
    }
  }

  private fun handleClickListeners() {
    binding.btnAccept.setOnClickListener {
      positiveButtonClick?.invoke(this) ?: dismiss()
    }

    binding.btnCancel.setOnClickListener {
      negativeButtonClick?.invoke(this) ?: dismiss()
    }

    binding.btnNeutral.setOnClickListener {
      neutralButtonClick?.invoke(this) ?: dismiss()
    }
  }

  fun setDialogIcon(icon: Drawable?) {
    this.dialogIcon = icon
  }

  fun setTitle(title: String?) {
    this.title = title
  }

  fun setTitleColorResource(@ColorRes titleColorResId: Int?) {
    this.titleColorResId = titleColorResId
  }

  fun setContent(content: String?) {
    this.content = content
  }

  fun setContentColorResource(@ColorRes contentColorResId: Int?) {
    this.contentColorResId = contentColorResId
  }

  fun setPositiveButton(
    positiveButtonText: String?,
    positiveButtonClick: ((DialogFragment) -> Unit)?
  ) {
    this.positiveButtonText = positiveButtonText
    this.positiveButtonClick = positiveButtonClick
  }

  fun setPositiveButtonTextColorResource(@ColorRes positiveButtonTextColorResId: Int?) {
    this.positiveButtonTextColorResId = positiveButtonTextColorResId ?: R.color.colorWhite
  }

  fun setNeutralButton(
    neutralButtonText: String?,
    neutralButtonClick: ((DialogFragment) -> Unit)?
  ) {
    this.neutralButtonText = neutralButtonText
    this.neutralButtonClick = neutralButtonClick
  }

  fun setupNegativeButton(
    negativeButtonText: String?,
    negativeButtonClick: ((DialogFragment) -> Unit)?
  ) {
    this.negativeButtonText = negativeButtonText
    this.negativeButtonClick = negativeButtonClick
  }

  fun setNegativeButtonTextColorResource(@ColorRes negativeButtonTextColorResId: Int?) {
    this.negativeButtonTextColorResId = negativeButtonTextColorResId ?: R.color.colorDarkRed
  }

  fun setIsCancelable(isCancelable: Boolean?) {
    this.isCancelable = isCancelable ?: true
  }

  fun setAnimationFile(@RawRes fileNameRawResId: Int?) {
    animationFileNameRawResId = fileNameRawResId
  }
}