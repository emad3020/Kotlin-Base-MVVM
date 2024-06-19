package com.core.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.Editable
import android.util.Base64
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

/**
 * @Param prefixCount -> Number of unsecured characters from start
 * @Param suffixCount -> Number of unsecured characters from end
 * @Param maskedString -> Special character that you want to change original character with it
 **/
fun String.toMaskedString(
  prefixCount: Int,
  suffixCount: Int,
  maskedString: String = "*"
): String {
  return this.mapIndexed { index, c ->
    if (index >= prefixCount && this.length - index > suffixCount && c.toString().isNotBlank()) {
      maskedString
    } else {
      c
    }
  }.joinToString("")
}

fun String.convertToBitmap(): Bitmap {
  val decodeString = Base64.decode(this, Base64.DEFAULT)
  return BitmapFactory.decodeByteArray(decodeString, 0, decodeString.count())
}

/**
 * This function makes two tasks:
 *    - Split card number into 4 sections
 *    - Secure card number
 **/
fun String.maskedCardNumber(
  prefixCount: Int = 2,
  suffixCount: Int = 3,
  maskedString: String = "*"
): String {
  val result = this.chunked(4).joinToString(separator = " ")

  return result.toMaskedString(
    prefixCount = prefixCount,
    suffixCount = suffixCount,
    maskedString = maskedString
  )
}

fun String.generateQrCode(): Bitmap? {
  var qrBitmap: Bitmap? = null
  try {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(this, BarcodeFormat.QR_CODE, 750, 750)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val pixels = IntArray(width * height)

    var y = 0
    while (y < height) {
      var x = 0
      while (x < width) {
        pixels[y * width + x] = if (bitMatrix[x, y]) {
          Color.BLACK
        } else {
          Color.WHITE
        }
        x++
      }
      y++
    }

    qrBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    qrBitmap.setPixels(pixels, 0, width, 0, 0, width, height)

    return qrBitmap
  } catch (e: Throwable) {
    e.printStackTrace()
    return qrBitmap
  }
}

inline fun <reified T : Any> String?.toModel(modelClass: Class<T>): T? {
  return if (this.isNullOrEmpty()) {
    null
  } else {
    Json.decodeFromString<T>(this)
  }
}

fun Any?.toJson(): String? {
  return if (this == null) {
    null
  } else {
    Json.encodeToString(this)
  }
}

fun String?.truncateZeros(): String {
  return this?.let {
    if (contains(".0")) {
      it.substring(0, it.indexOf('.'))
    } else {
      this
    }
  } ?: run {
    ""
  }
}

fun String?.convertToCents(): Int {
  if (this.isNullOrEmpty()) {
    return 0
  }

  var rawDollar = BigDecimal.ZERO
  val increment = BigDecimal(this.steamingMoneyAmount())
  rawDollar = rawDollar.add(increment)
  val doubledValue = rawDollar.toDouble()
  val cents = doubledValue * 100
  val amount = cents.toInt()
  val roundedAmount = (doubledValue * 100).roundToInt()

  return if (amount != roundedAmount) roundedAmount else amount
}

fun String.steamingMoneyAmount() = this.replace(",", "")

fun String.toBigDecimal(): BigDecimal {
  val cleanString = this.replace("\\D".toRegex(), "")
  return BigDecimal(cleanString).setScale(
    2,
    RoundingMode.FLOOR
  ).divide(
    BigDecimal(100),
    RoundingMode.FLOOR
  )
}
