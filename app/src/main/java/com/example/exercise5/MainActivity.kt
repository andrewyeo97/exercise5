package com.example.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Module-level variables
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences
    var countDislike: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")

        //Initialize the counter ViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialize the shared preference
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = countDislike.toString()

        imageViewLike.setOnClickListener {
            counterViewModel.countLike++
            textViewLike.text = counterViewModel.countLike.toString()
        }

        imageViewDislike.setOnClickListener {
            countDislike++
            textViewDislike.text = countDislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "OnResume")
        //Retrieve Shared Preference value(s)
        counterViewModel.countLike = sharedPreferences.getInt(getString(R.string.countlike), 0)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "OnPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.countlike), counterViewModel.countLike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "OnDestroyed")
        super.onDestroy()
    }
}

