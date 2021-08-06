package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.login.LoginViewModel
import timber.log.Timber

const val KEY_LOGIN_PREVIOUSLY_COMPLETED = "is_logged_in"

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var skipLogin: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
        setupNavigation()
        // View recreated
        skipLogin = savedInstanceState?.getBoolean(KEY_LOGIN_PREVIOUSLY_COMPLETED)
        Timber.i("Skip login status restored value: ${skipLogin}")
        LoginViewModel.LOGIN_COMPLETED = skipLogin ?: false
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                Timber.i("onOptionsItemSelected: Logout selected")
                LoginViewModel.LOGIN_COMPLETED = false
                val graph = navController.graph
                graph.startDestination = R.id.login_fragment
                navController.graph = graph
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_LOGIN_PREVIOUSLY_COMPLETED, LoginViewModel.LOGIN_COMPLETED)
        Timber.i("onSaveInstanceState called mainActivity")
        Timber.i("Stored login status restored value: ${LoginViewModel.LOGIN_COMPLETED}")
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        val graph = navController.graph
        graph.startDestination =
            if (LoginViewModel.LOGIN_COMPLETED) R.id.shoeSelectionFragment else R.id.login_fragment
        navController.graph = graph
    }
}
