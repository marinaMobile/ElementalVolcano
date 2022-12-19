package com.sunsetgames.bordersecurit.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunsetgames.bordersecurit.R

class First : AppCompatActivity() {

    companion object{
        val key_balance = "1"
        val key_bet = "55"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
}