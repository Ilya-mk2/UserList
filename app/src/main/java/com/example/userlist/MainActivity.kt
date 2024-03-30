package com.example.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userlist.databinding.ActivityMainBinding
import com.example.userlist.list.UsersFragment


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val usersFragment = UsersFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.main_container, usersFragment).commit()

    }


}