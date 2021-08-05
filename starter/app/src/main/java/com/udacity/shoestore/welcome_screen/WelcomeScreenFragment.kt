package com.udacity.shoestore.welcome_screen

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeScreenBinding
import timber.log.Timber


class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)

        binding.readInstructionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreen_to_instructionsFragment)

        }

        return binding.root
    }
}
