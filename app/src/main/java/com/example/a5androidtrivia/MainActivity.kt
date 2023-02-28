package com.example.a5androidtrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val binding : ViewDataBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val myNavHostFragment   = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
//        val navController  = this.findNavController(R.id.myNavHostFragment)
        val navController = myNavHostFragment.navController
        val navView: NavigationView = findViewById(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)
        drawerLayout = findViewById(R.id.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout )


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}