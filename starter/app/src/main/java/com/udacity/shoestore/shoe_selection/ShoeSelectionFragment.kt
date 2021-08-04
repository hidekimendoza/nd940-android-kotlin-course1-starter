package com.udacity.shoestore.shoe_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeSelectionBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding


class ShoeSelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeSelectionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_selection, container, false)

        // Enable FAB action
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_shoeSelectionFragment_to_shoeFragment)
        }
        binding.shoeListview.removeAllViews()

        val shoe_binding = DataBindingUtil.inflate<ShoeListItemBinding>(layoutInflater, R.layout.shoe_list_item, binding.shoeListview, false)
        shoe_binding.shoeItemImageview.setImageResource(R.drawable.shoe_2)
        shoe_binding.showItemName.text = "Shoe 1"
        binding.shoeListview.addView(shoe_binding.root)
        return binding.root
    }
}