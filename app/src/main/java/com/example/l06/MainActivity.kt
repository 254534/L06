package com.example.l06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frg_container) as NavHostFragment
        navController = navHostFragment.navController
        val bnNavView =
            this.findViewById<BottomNavigationView>(R.id.bottom_nav)

        val menu: Menu = bnNavView.menu
        val item: MenuItem = menu.getItem(1)
        item.isChecked = true

        bnNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_button1 -> {
                    navController.navigate(R.id.action_global_fragment2)
                }
                R.id.menu_button2 -> {
                    navController.navigate(R.id.action_global_fragment1)
                }
                R.id.menu_button3 -> {
                    navController.navigate(R.id.action_global_fragment3)
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        val bnNavView = this.findViewById<BottomNavigationView>(R.id.bottom_nav)
        val menu: Menu = bnNavView.menu

        if (bnNavView.selectedItemId == menu.getItem(2).itemId) {
            menu.getItem(2).isChecked = true
            navController.navigate(R.id.action_global_fragment3)
        }
    }
}