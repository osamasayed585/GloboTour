package com.sriyank.globotour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        toolbar = findViewById(R.id.activity_main_toolbar)
        navigationView = findViewById(R.id.nav_vew)
        drawerLayout = findViewById(R.id.drawer_layout)

        // Get NavHostFragment and NavController
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        toolbar.setupWithNavController(navController, appBarConfiguration)

        navigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen)
            drawerLayout.close()
        else
            super.onBackPressed()
    }
}