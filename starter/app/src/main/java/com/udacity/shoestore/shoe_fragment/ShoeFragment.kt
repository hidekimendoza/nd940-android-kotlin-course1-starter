package com.udacity.shoestore.shoe_fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDescriptionFragmentBinding
import com.udacity.shoestore.models.Shoe


class ShoeFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    private lateinit var viewModelFactory: ShoeViewModelFactory
    private lateinit var binding: ShoeDescriptionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_description_fragment, container, false)
        setHasOptionsMenu(true)

        val args by navArgs<ShoeFragmentArgs>()
        viewModelFactory = ShoeViewModelFactory(args.shoeName)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeViewModel::class.java)

        binding.shoe = viewModel.shoe

        // Run toast only when viewModel is initialized
        viewModel.eventShoeSelected.observe(this, Observer { hasShoeBeenSelected ->
            if (hasShoeBeenSelected) {
                val toast: Toast =
                    Toast.makeText(
                        activity,
                        "Selected ${args.shoeName.toString()}",
                        Toast.LENGTH_SHORT
                    )
                toast.show()
                viewModel.onEventShoeSelectedComplete()
            }
        })


        // Set cancel button action - pop
        binding.shoeCancelButton.setOnClickListener {
            findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeSelectionFragment())
        }
        binding.shoeSaveButton.setOnClickListener {
            viewModel.updateShoeInformation()
            findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeSelectionFragment())
        }
        populate_view(viewModel.shoe)
        return binding.root
    }

    private fun populate_view(shoe: Shoe) {
        if (shoe.images.isNotEmpty()) {
            binding.shoeImageView.setImageURI(Uri.parse(shoe.images[0]))
        }
    }

}