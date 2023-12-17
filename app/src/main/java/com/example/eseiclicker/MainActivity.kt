package com.example.eseiclicker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import android.content.Context
import android.os.Handler


class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var currentScore = 0
    private var autoClicksPerSecond: Int = 0

    private val handler = Handler()
    private val autoClickRunnable = object : Runnable {
        override fun run() {
            autoClick()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentScore = 0

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        currentScore = intent.getIntExtra("currentScore", 0)
        autoClicksPerSecond = intent.getIntExtra("autoClicksPerSecond", 0)

        val imgCookie: ImageView = findViewById(R.id.imgCookie)
        val lblTotal : TextView = findViewById(R.id.lblTotal)

        lblTotal.text = "Puntuación: $currentScore"

        val shopButton: Button = findViewById(R.id.shopButton)
        shopButton.setOnClickListener{ onShopButtonClick(it) }

        val deletionButton: Button = findViewById(R.id.deletionButton)
        deletionButton.setOnClickListener{ onDeletionButtonClick(it) }

        imgCookie.setOnClickListener {
            currentScore ++
            lblTotal.text = "Puntuación: $currentScore"
        }
    }

    private fun onShopButtonClick(view: View) {
        val intent = Intent(this, TiendaActivity::class.java)
        intent.putExtra("currentScore", currentScore)
        intent.putExtra("autoClicksPerSecond", autoClicksPerSecond)
        startActivity(intent)

    }

    private fun onDeletionButtonClick(view: View) {
        val intent = Intent(this, BorrarActivity::class.java)
        startActivity(intent)
    }

    private fun autoClick() {
        val lblTotal : TextView = findViewById(R.id.lblTotal)

        currentScore = currentScore!! + autoClicksPerSecond
        lblTotal.text = "Puntuación: $currentScore"

    }

    private fun startAutoClicks() {
        handler.postDelayed(autoClickRunnable, 1000)
    }

    private fun stopAutoClicks() {
        handler.removeCallbacks(autoClickRunnable)
    }

    override fun onResume() {
        super.onResume()
        startAutoClicks()
    }

    override fun onPause() {
        super.onPause()
        stopAutoClicks()
        with(sharedPreferences.edit()) {
            putInt("currentScore", currentScore)
            apply()
        }
    }
}