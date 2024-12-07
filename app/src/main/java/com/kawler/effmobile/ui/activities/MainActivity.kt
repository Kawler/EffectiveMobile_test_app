package com.kawler.effmobile.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.ActivityMainBinding
import com.kawler.effmobile.domain.utils.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBar: AppBarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        navView.setupWithNavController(navController)
        appBar = binding.mainAppbar
        sharedPreferencesUtil.setFirstLaunch()
        toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.hotTicketsFragment, R.id.weekendsFragment, R.id.hardRouteFragment -> {
                    appBar.visibility = View.VISIBLE
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setTitle("")
                }

                else -> {
                    appBar.visibility = View.GONE
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            }
        }

        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }
}