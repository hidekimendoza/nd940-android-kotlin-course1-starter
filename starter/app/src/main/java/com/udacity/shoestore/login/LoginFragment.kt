package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            if (verify_login()) findNavController().navigate(R.id.action_login_welcomeScreen)

        }

        binding.signupButton.setOnClickListener {
            viewModel.forceAuthentication()
            findNavController().navigate(R.id.action_login_welcomeScreen)
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_logout)?.isVisible = false
    }

    private fun verify_login(): Boolean {
        if (viewModel.loginAuthentication(
                binding.loginUsername.text.toString(),
                binding.loginPassword.text.toString()
            )
        ) {
            Timber.i("Login successful")
            return true
        } else {
            Timber.i("Login failed ")
            val toast: Toast = Toast.makeText(
                this.context,
                "Login failed, try with user: guess and password: password",
                Toast.LENGTH_SHORT
            )
            toast.show()
            return false
        }
    }
}