package com.udacity.shoestore.shoe_selection

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeSelectionBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.login.LoginViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeSelectionFragment : Fragment() {

    lateinit var viewModel: ShoeSelectionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeSelectionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_selection, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(ShoeSelectionViewModel::class.java)

        // Enable FAB action
        binding.fab.setOnClickListener {
            findNavController().navigate(
                ShoeSelectionFragmentDirections.actionShoeSelectionFragmentToShoeFragment(
                    null
                )
            )
        }
        binding.shoeListview.removeAllViews()
        setHasOptionsMenu(true)
        for (shoe: Shoe in ShoeSelectionViewModel.LIST_OF_SHOES) {
            val shoe_binding = DataBindingUtil.inflate<ShoeListItemBinding>(
                layoutInflater,
                R.layout.shoe_list_item,
                binding.shoeListview,
                false
            )
            if (shoe.images.isNotEmpty()) {
                shoe_binding.shoeItemImageview.setImageURI(Uri.parse(shoe.images[0]))
            } else {
                shoe_binding.shoeItemImageview.setImageResource(R.drawable.ic_launcher_background)
            }
            shoe_binding.showItemName.text = shoe.name

            binding.shoeListview.addView(shoe_binding.root)
            shoe_binding.root.setOnClickListener {
                findNavController().navigate(
                    ShoeSelectionFragmentDirections.actionShoeSelectionFragmentToShoeFragment(
                        shoe_binding.showItemName.text.toString()
                    )
                )
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                Timber.i("onOptionsItemSelected: Logout selected")
                LoginViewModel.LOGIN_COMPLETED = false
                val graph = findNavController().graph
                graph.startDestination = R.id.loginFragment
                findNavController().graph = graph
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}