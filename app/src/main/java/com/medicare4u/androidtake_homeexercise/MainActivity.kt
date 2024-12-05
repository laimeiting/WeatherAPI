package com.medicare4u.androidtake_homeexercise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.widget.*
import com.medicare4u.androidtake_homeexercise.R
import com.medicare4u.androidtake_homeexercise.WeatherViewModel
import com.medicare4u.androidtake_homeexercise.ResultActivity
import android.util.Log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val apiKeyInput = findViewById<EditText>(R.id.apiKeyInput)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val citySpinner = findViewById<Spinner>(R.id.citySpinner)

        // Set up the Spinner with cities
        val cities = listOf("Kuala Lumpur", "Singapore")
        val adapter = ArrayAdapter(this, R.layout.spinner_item, cities)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        citySpinner.adapter = adapter

        // Handle button click
        submitButton.setOnClickListener {
            Log.d("MainActivity", "Submit button clicked")
            val apiKey = apiKeyInput.text.toString()
            val city = citySpinner.selectedItem?.toString() ?: "No City Selected"
            Log.d("MainActivity", "Selected City: $city")

            // Fetch weather data
            viewModel.fetchWeather(apiKey, city)

            Log.d("MainActivity", "Fetching weather for API Key: $apiKey and City: $city")

        }

        // Observe data and navigate to ResultActivity
        viewModel.weather.observe(this) { response ->
            Log.d("MainActivity", "Weather Response Received: $response")

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("CELSIUS", response.current.temp_c)
            intent.putExtra("FAHRENHEIT", response.current.temp_f)
            startActivity(intent)
        }
    }
}

