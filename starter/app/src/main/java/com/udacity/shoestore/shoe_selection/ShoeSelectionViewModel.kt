package com.udacity.shoestore.shoe_selection

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeSelectionViewModel(application: Application) : AndroidViewModel(application) {
    init {
        Timber.i("ShoeSelectionViewModel created")
        addInitialShoes()
    }

    private fun addInitialShoes() {
        if (LIST_OF_SHOES.isEmpty()) {
            LIST_OF_SHOES.add(
                Shoe(
                    "Shoe 2",
                    7.5,
                    "comp2",
                    "description 2",
                    mutableListOf(getUriToDrawable(getApplication(), R.drawable.shoe_2).toString())
                )
            )
            LIST_OF_SHOES.add(
                Shoe(
                    "Shoe 3",
                    8.5,
                    "comp3",
                    "description 3",
                    mutableListOf(getUriToDrawable(getApplication(), R.drawable.shoe_3).toString())
                )
            )
            LIST_OF_SHOES.add(
                Shoe(
                    "Shoe 5",
                    9.5,
                    "comp5",
                    "description 5",
                    mutableListOf(getUriToDrawable(getApplication(), R.drawable.shoe_5).toString())
                )
            )
        }
    }

    fun getUriToDrawable(
        context: Context,
        drawableId: Int
    ): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + context.resources.getResourcePackageName(drawableId)
                    + '/' + context.resources.getResourceTypeName(drawableId)
                    + '/' + context.resources.getResourceEntryName(drawableId)
        )
    }

    companion object {
        val LIST_OF_SHOES = mutableListOf<Shoe>()
    }
}