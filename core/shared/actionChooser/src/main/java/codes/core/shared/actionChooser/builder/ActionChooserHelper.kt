package codes.core.shared.actionChooser.builder

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import codes.core.shared.actionChooser.model.ChooserAction
import codes.core.shared.actionChooser.presentation.ActionChooserPopUp

class ActionChooserHelper private constructor(builder: Builder) {

  private var fragmentManager: FragmentManager = builder.fragmentManager

  // Pop Up Background
  private val popUpBackground: Int?

  // Is Cancelable
  private val isCancelable: Boolean?

  // Title
  private val title: String?
  private val titleResId: Int?

  // Title Color
  private val titleColorResId: Int?
  private val titleColor: Int?

  // Content
  private val content: String?
  private val contentResId: Int?

  // Content Color
  private val contentColorResId: Int?
  private val contentColor: Int?

  // Actions
  private val actions: ArrayList<ChooserAction>?

  // Item CLick
  private var itemClick: ((action: ChooserAction?) -> Unit)?

  init {
    popUpBackground = builder.popUpBackground

    isCancelable = builder.isCancelable

    title = builder.title
    titleResId = builder.titleResId

    titleColorResId = builder.titleColorResId
    titleColor = builder.titleColor

    content = builder.content
    contentResId = builder.contentResId

    contentColorResId = builder.contentColorResId
    contentColor = builder.contentColor

    actions = builder.actions

    itemClick = builder.itemClick

    showPopUp()
  }

  private fun showPopUp() {
    val dialogFragment = ActionChooserPopUp().apply {
      setPopUpBackground(popUpBackground)
      setIsCancelable(isCancelable)
      setTitle(title)
      setTitle(titleResId)
      setTitleColorResource(titleColorResId)
      setTitleColor(titleColor)
      setContent(content)
      setContent(contentResId)
      setContentColorResource(contentColorResId)
      setContentColor(contentColor)
      setActions(actions)
      setItemClick(itemClick)
    }

    dialogFragment.show(fragmentManager, "AnimatedDialog")
  }

  class Builder(internal var fragmentManager: FragmentManager) {

    // Pop Up Background
    internal var popUpBackground: Int? = null

    // Is Cancelable
    internal var isCancelable: Boolean? = null

    // Title
    internal var title: String? = null
    internal var titleResId: Int? = null

    // Title Color
    internal var titleColorResId: Int? = null
    internal var titleColor: Int? = null

    // Content
    internal var content: String? = null
    internal var contentResId: Int? = null

    // Content Color
    internal var contentColorResId: Int? = null
    internal var contentColor: Int? = null

    // Actions
    internal var actions: ArrayList<ChooserAction>? = null

    // Item CLick
    internal var itemClick: ((action: ChooserAction?) -> Unit)? = null

    fun setPopUpBackground(@DrawableRes popUpBackground: Int): Builder {
      this.popUpBackground = popUpBackground
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

    fun setTitle(@StringRes titleResId: Int): Builder {
      this.titleResId = titleResId
      return this
    }

    fun setTitleColorResource(@ColorRes titleColorResId: Int): Builder {
      this.titleColorResId = titleColorResId
      return this
    }

    fun setTitleColor(titleColor: Int): Builder {
      this.titleColor = titleColor
      return this
    }

    fun setContent(content: String?): Builder {
      this.content = content
      return this
    }

    fun setContent(@StringRes contentResId: Int): Builder {
      this.contentResId = contentResId
      return this
    }

    fun setContentColorResource(@ColorRes contentColorResId: Int): Builder {
      this.contentColorResId = contentColorResId
      return this
    }

    fun setContentColor(contentColor: Int): Builder {
      this.contentColor = contentColor
      return this
    }

    fun setActions(actions: ArrayList<ChooserAction>): Builder {
      this.actions = actions
      return this
    }

    fun setItemClick(itemClick: ((action: ChooserAction?) -> Unit)): Builder {
      this.itemClick = itemClick
      return this
    }

    fun create(): ActionChooserHelper {
      return ActionChooserHelper(this)
    }
  }
}