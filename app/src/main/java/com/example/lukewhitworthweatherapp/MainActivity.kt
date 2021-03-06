package com.example.lukewhitworthweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lukewhitworthweatherapp.view.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.main_view, SearchFragment()).commit()
    }
}

