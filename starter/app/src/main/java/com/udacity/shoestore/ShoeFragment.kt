package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.ShoeDescriptionFragmentBinding
import timber.log.Timber


class ShoeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: ShoeDescriptionFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_description_fragment, container, false)
        setHasOptionsMenu(true)

        val args by navArgs<ShoeFragmentArgs>()

        val toast:Toast = Toast.makeText(activity, "Selected ${args.shoeName.toString()}", Toast.LENGTH_SHORT)
        toast.show()

        // Set cancel button action - pop
        binding.shoeCancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeFragment_to_shoeSelectionFragment)
        }
        return binding.root
    }

}