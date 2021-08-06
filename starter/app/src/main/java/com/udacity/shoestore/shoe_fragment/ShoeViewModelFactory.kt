package com.udacity.shoestore.shoe_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoeViewModelFactory(private val shoeName: String?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeViewModel::class.java)) {
            return ShoeViewModel(shoeName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}