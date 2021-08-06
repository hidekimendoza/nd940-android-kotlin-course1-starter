package com.udacity.shoestore.shoe_fragment

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoe_selection.ShoeSelectionViewModel
import timber.log.Timber

class ShoeViewModel(shoe_name: String?) : ViewModel() {
    lateinit var shoe: Shoe

    init {
        Timber.i("ShoeViewModel created")
        var shoeFound = false
        if (shoe_name != null) {
            for (cur_shoe in ShoeSelectionViewModel.LIST_OF_SHOES) {
                if (cur_shoe.name == shoe_name) {
                    shoe = cur_shoe
                    shoeFound = true
                    break
                }
            }
            if (!shoeFound) {
                shoe = Shoe("", 0.0, "", "", mutableListOf())
            }
        } else {
            shoe = Shoe("", 0.0, "", "", mutableListOf())
        }
    }
}