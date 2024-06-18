package codes.core.shared.actionChooser.model

import kotlin.random.Random

data class ChooserAction(
  var id: Int = Random.nextInt(),
  var name: String,
  var isSelected: Boolean = false
)