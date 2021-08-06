package com.udacity.shoestore.shoe_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoe_selection.ShoeSelectionViewModel
import timber.log.Timber

class ShoeViewModel(shoe_name: String?) : ViewModel() {
    lateinit var shoe: Shoe
    private val _eventShoeSelected = MutableLiveData<Boolean>()
    val eventShoeSelected: LiveData<Boolean>
        get() = _eventShoeSelected
    var idx: Int = -1

    init {
        Timber.i("ShoeViewModel created")
        idx = -1
        // Trigger event just after select the shoe
        _eventShoeSelected.value = true
        var shoeFound = false
        if (shoe_name != null) {
            for ((index, cur_shoe) in ShoeSelectionViewModel.LIST_OF_SHOES.withIndex()) {
                if (cur_shoe.name == shoe_name) {
                    shoe = cur_shoe
                    shoeFound = true
                    idx = index
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

    fun updateShoeInformation(
    ) {
        // New shoe element
        if (idx == -1) {
            ShoeSelectionViewModel.LIST_OF_SHOES.add(shoe)

        }
        // Overwrite element
        else {
            ShoeSelectionViewModel.LIST_OF_SHOES[idx] = shoe
        }

    }

    fun onEventShoeSelectedComplete() {
        _eventShoeSelected.value = false
    }
}