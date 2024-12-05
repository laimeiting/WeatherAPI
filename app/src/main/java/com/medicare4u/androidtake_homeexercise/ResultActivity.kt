package com.medicare4u.androidtake_homeexercise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import com.medicare4u.androidtake_homeexercise.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Get the data passed via Intent
        val celsius = intent.getDoubleExtra("CELSIUS", 0.0)
        val fahrenheit = intent.getDoubleExtra("FAHRENHEIT", 0.0)

        // Display the results
        findViewById<TextView>(R.id.celsiusValue).text = celsius.toString()
        findViewById<TextView>(R.id.fahrenheitValue).text = fahrenheit.toString()
    }
}