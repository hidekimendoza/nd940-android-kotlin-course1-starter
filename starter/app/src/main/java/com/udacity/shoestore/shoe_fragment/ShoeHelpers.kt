package com.udacity.shoestore.shoe_fragment
import androidx.databinding.InverseMethod


object SizeConverter {
    @JvmStatic
    @InverseMethod("doubleToStr")
    fun strToDouble(value: String): Double {
        return try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            Double.NaN
        }
    }
    @JvmStatic
    fun doubleToStr(value: Double?): String? {
        return value?.toString() ?: ""
    }
}