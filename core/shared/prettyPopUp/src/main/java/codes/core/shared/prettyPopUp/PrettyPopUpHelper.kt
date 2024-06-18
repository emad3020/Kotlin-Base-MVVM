package codes.core.shared.prettyPopUp

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.RawRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import codes.core.shared.prettyPopUp.presentation.PrettyPopUp

class PrettyPopUpHelper private constructor(builder: Builder) {

  private val fragmentManager: FragmentManager = builder.fragmentManager

  // Icon that appear in the middle top of the dialog
  private val dialogIcon: Drawable?

  // Is Cancelable
  private val isDialogCancelable: Boolean?

  private val title: String?

  // Title Color
  private val titleColorResId: Int?

  // Content
  private val content: String?

  // Content Color
  private val contentColorResId: Int?

  // Positive Button
  private val positiveButtonText: String?

  // Positive Button Text Color
  private val positiveButtonTextColorResId: Int?

  // Positive Button Click
  private val positiveButtonClick: ((popUp: DialogFragment) -> Unit)?

  // Neutral Button
  private val neutralButtonText: String?

  // Neutral Button Text Color
  private val neutralButtonTextColorResId: Int?

  // Neutral Button Click
  private val neutralButtonClick: ((popUp: DialogFragment) -> Unit)?

  // Negative Button
  private val negativeButtonText: String?

  // Negative Button Text Color
  private val negativeButtonTextColorResId: Int?

  // Negative Button Click
  private val negativeButtonClick: ((popUp: DialogFragment) -> Unit)?

  // Animation File Name
  private val animationFileRawResId: Int?

  private var dialogFragment: PrettyPopUp? = null

  init {

    dialogIcon = builder.dialogIcon

    title = builder.title

    titleColorResId = builder.titleColorResId

    content = builder.content

    contentColorResId = builder.contentColorResId

    positiveButtonText = builder.positiveButtonText

    positiveButtonTextColorResId = builder.positiveButtonTextColorResId

    positiveButtonClick = builder.positiveButtonClick

    neutralButtonText = builder.neutralButtonText

    neutralButtonTextColorResId = builder.neutralButtonTextColorResId

    neutralButtonClick = builder.neutralButtonClick

    negativeButtonText = builder.negativeButtonText

    negativeButtonTextColorResId = builder.negativeButtonTextColorResId

    negativeButtonClick = builder.negativeButtonClick

    isDialogCancelable = builder.isCancelable

    animationFileRawResId = builder.animationFileRawResId

    showPopUp()
  }

  private fun showPopUp() {
    dialogFragment = PrettyPopUp()
    dialogFragment?.apply {
      setDialogIcon(dialogIcon)
      setIsCancelable(isDialogCancelable)
      setTitle(title)
      setTitleColorResource(titleColorResId)
      setContent(content)
      setContentColorResource(contentColorResId)
      setNeutralButton(neutralButtonText, neutralButtonClick)
      setNegativeButtonTextColorResource(neutralButtonTextColorResId)
      setPositiveButton(positiveButtonText, positiveButtonClick)
      setPositiveButtonTextColorResource(positiveButtonTextColorResId)
      setupNegativeButton(negativeButtonText, negativeButtonClick)
      setNegativeButtonTextColorResource(negativeButtonTextColorResId)
      setAnimationFile(animationFileRawResId)
    }?.show(fragmentManager, "PrettyPopUp")
  }

  fun prettyPopUpDismiss() {
    dialogFragment?.apply {
      if (isVisible) {
        dialogFragment?.dismiss()
      }
    }
  }

  class Builder(internal var fragmentManager: FragmentManager) {

    // Icon that appear in the top middle of the dialog
    internal var dialogIcon: Drawable? = null

    // Is Cancelable
    internal var isCancelable: Boolean? = null

    // Title
    internal var title: String? = null

    // Title Color
    internal var titleColorResId: Int? = null

    // Content
    internal var content: String? = null

    // Content Color
    internal var contentColorResId: Int? = null

    // Positive Button
    internal var positiveButtonText: String? = null

    // Positive Button Text Color
    internal var positiveButtonTextColorResId: Int? = null

    // Positive Button Click
    internal var positiveButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    // Neutral Button
    internal var neutralButtonText: String? = null

    // Neutral Button Text Color
    internal var neutralButtonTextColorResId: Int? = null

    // Neutral Button Click
    internal var neutralButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    // Negative Button
    internal var negativeButtonText: String? = null

    // Negative Button Text Color
    internal var negativeButtonTextColorResId: Int? = null

    // Negative Button Click
    internal var negativeButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    // Animation File

    internal var animationFileRawResId: Int? = null

    fun setDialogIcon(icon: Drawable?): Builder {
      dialogIcon = icon
      return this
    }

    fun setIsCancelable(isCancelable: Boolean?): Builder {
      this.isCancelable = isCancelable
      return this
    }

    fun setTitle(title: String?): Builder {
      this.title = title
      return this
    }

    fun setTitleColor(@ColorRes titleColorResId: Int?): Builder {
      this.titleColorResId = titleColorResId
      return this
    }

    fun setContent(content: String?): Builder {
      this.content = content
      return this
    }

    fun setContentColor(@ColorRes contentColorResId: Int?): Builder {
      this.contentColorResId = contentColorResId
      return this
    }

    fun setPositiveButton(
      positiveButtonText: String?,
      positiveButtonClick: ((DialogFragment) -> Unit)? = null
    ): Builder {
      this.positiveButtonText = positiveButtonText
      this.positiveButtonClick = positiveButtonClick
      return this
    }

    fun setPositiveButtonTextColor(
      @ColorRes positiveButtonTextColorResId: Int?
    ): Builder {
      this.positiveButtonTextColorResId = positiveButtonTextColorResId
      return this
    }

    fun setNegativeButton(
      negativeButtonText: String?,
      negativeButtonClick: ((DialogFragment) -> Unit)? = null
    ): Builder {
      this.negativeButtonText = negativeButtonText
      this.negativeButtonClick = negativeButtonClick
      return this
    }

    fun setNegativeButtonTextColor(
      @ColorRes negativeButtonTextColorResId: Int?
    ): Builder {
      this.negativeButtonTextColorResId = negativeButtonTextColorResId
      return this
    }

    fun setNeutralButton(
      neutralButtonText: String?,
      neutralButtonClick: ((DialogFragment) -> Unit)? = null
    ): Builder {
      this.neutralButtonText = neutralButtonText
      this.neutralButtonClick = neutralButtonClick
      return this
    }

    fun setNeutralButtonTextColor(
      @ColorRes neutralButtonTextColorResId: Int?
    ): Builder {
      this.neutralButtonTextColorResId = neutralButtonTextColorResId
      return this
    }

    fun setAnimationFile(@RawRes rawResId: Int?): Builder {
      animationFileRawResId = rawResId
      return this
    }

    fun create(): PrettyPopUpHelper {
      return PrettyPopUpHelper(this)
    }
  }
}