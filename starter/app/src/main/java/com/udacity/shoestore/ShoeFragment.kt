package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.ShoeDescriptionFragmentBinding


class ShoeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: ShoeDescriptionFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_description_fragment, container, false)

        // Set cancel button action - pop
        binding.shoeCancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeFragment_to_shoeSelectionFragment)
        }
        return binding.root
    }
}