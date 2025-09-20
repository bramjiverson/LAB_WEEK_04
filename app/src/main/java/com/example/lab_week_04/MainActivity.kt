package com.example.lab_week_04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // AppBarConfiguration dengan drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.listFragment,
                R.id.favoritesFragment,
                R.id.cafeFragment
            ),
            findViewById(R.id.drawer_layout)
        )

        // Hubungkan toolbar dengan NavController → hamburger menu berfungsi
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Navigation Drawer
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)

        // BottomNavigationView → tetap berfungsi dengan warna dan selector
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
    }

    // Toolbar back button & hamburger menu
    override fun onSupportNavigateUp(): Boolean {
        val navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
