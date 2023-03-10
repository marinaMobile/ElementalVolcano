package com.sunsetgames.bordersecurit.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.ActivityFirstBinding

class First : AppCompatActivity() {

    lateinit var bindinggg: ActivityFirstBinding

    companion object{
        val key_balance = "1"
        val key_bet = "55"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinggg = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(bindinggg.root)

        bindinggg.botomDovn.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.save -> {
                    Toast.makeText(this, "All data saved!", Toast.LENGTH_SHORT).show()
                }
                R.id.info -> {
                    Toast.makeText(this, "This game created for your pleasure", Toast.LENGTH_SHORT).show()
                }
                R.id.rules -> {
                    Toast.makeText(this, "Collect 21 points from cards", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}