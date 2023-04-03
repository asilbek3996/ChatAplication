package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.databinding.ActivityMainBinding
import com.example.chatapplication.fragment.HomeFragment
import com.example.chatapplication.fragment.ProfileFragment
import com.example.chatapplication.fragment.SettingsFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var homeFragment = HomeFragment.newInstance()
    var profileFragment = ProfileFragment.newInstance()
    var settingsFragment = SettingsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.flContainer, homeFragment).commit()
        binding.bottomNavigationMenu.setOnItemSelectedListener {
            if (it.itemId == R.id.actionSettings){
                supportFragmentManager.beginTransaction().replace(R.id.flContainer, settingsFragment).commit()
            }else if (it.itemId == R.id.actionHome){
                supportFragmentManager.beginTransaction().replace(R.id.flContainer,homeFragment).commit()
            }else if (it.itemId == R.id.actionProfile){
                supportFragmentManager.beginTransaction().replace(R.id.flContainer,profileFragment).commit()
            }
            return@setOnItemSelectedListener true
        }
           }


}