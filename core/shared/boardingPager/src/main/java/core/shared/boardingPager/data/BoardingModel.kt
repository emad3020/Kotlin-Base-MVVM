package core.shared.boardingPager.data

import androidx.annotation.ColorRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.core.presentation.models.BaseModel
import core.shared.boardingPager.R

data class BoardingModel(
  @StringRes val message: Int,
  @RawRes val boardingAnimImage: Int,
  @ColorRes val indicatorActiveColor : Int = R.color.colorBlack,
  @ColorRes val indicatorInactiveColor : Int = R.color.colorGray,
  @ColorRes val contentColor : Int = R.color.colorBlack
): BaseModel(){
  override val itemId: String  = message.toString()
}