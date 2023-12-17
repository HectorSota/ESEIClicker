package com.example.eseiclicker


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.widget.TextView
import android.os.Handler


class TiendaActivity : ComponentActivity() {

    private var currentScore: Int = 0
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
        setContentView(R.layout.activity_tienda)

        currentScore = intent.getIntExtra("currentScore", 0)
        autoClicksPerSecond = intent.getIntExtra("autoClicksPerSecond", 0)

        val itemUno: Button = findViewById(R.id.itemUno)
        val itemDos: Button = findViewById(R.id.itemDos)
        val itemTres: Button = findViewById(R.id.itemTres)
        val itemCuatro: Button = findViewById(R.id.itemCuatro)
        val itemCinco: Button = findViewById(R.id.itemCinco)
        val itemSeis: Button = findViewById(R.id.itemSeis)
        val backToMainButton: Button = findViewById(R.id.backToMainButton)
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        itemUno.setOnClickListener{ onItemButtonClick(it) }
        itemDos.setOnClickListener{ onItemDosButtonClick(it) }
        itemTres.setOnClickListener{ onItemTresButtonClick(it) }
        itemCuatro.setOnClickListener{ onItemCuatroButtonClick(it) }
        itemCinco.setOnClickListener{ onItemCincoButtonClick(it) }
        itemSeis.setOnClickListener{ onItemSeisButtonClick(it) }
        backToMainButton.setOnClickListener { onBackToMainButtonClick(it) }



        lblTotalShop.text = "Score: $currentScore"
    }

    private fun onItemButtonClick(view: View) {
        val cost = 10
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 1
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onItemDosButtonClick(view: View) {
        val cost = 35
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 5
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onItemTresButtonClick(view: View) {
        val cost = 60
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 10
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onItemCuatroButtonClick(view: View) {
        val cost = 120
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 25
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onItemCincoButtonClick(view: View) {
        val cost = 500
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 50
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onItemSeisButtonClick(view: View) {
        val cost = 1000
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        lblTotalShop.text = "Score: $currentScore"

        val tempScore = currentScore
        if (tempScore != null && tempScore >= cost) {
            currentScore = tempScore - cost
            lblTotalShop.text = "Score: $currentScore"
            autoClicksPerSecond += 100
        }
        else {
            Toast.makeText(
                this@TiendaActivity,
                "You don't have enough funds to purchase this item!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun onBackToMainButtonClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("currentScore", currentScore)
        intent.putExtra("autoClicksPerSecond", autoClicksPerSecond)
        startActivity(intent)
    }

    private fun autoClick() {
        val lblTotalShop : TextView = findViewById(R.id.lblTotalShop)

        currentScore = currentScore!! + autoClicksPerSecond
        lblTotalShop.text = "Score: $currentScore"

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
    }

}