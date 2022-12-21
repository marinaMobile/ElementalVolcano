package com.sunsetgames.bordersecurit.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunsetgames.bordersecurit.databinding.ActivityWinnersBinding
import kotlin.random.Random

class WinnersAct : AppCompatActivity() {
    private lateinit var ksdaskm : ActivityWinnersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ksdaskm = ActivityWinnersBinding.inflate(layoutInflater)
        setContentView(ksdaskm.root)
        ksdaskm.tvName1.text = SecGmeUtils.topNames[Random.nextInt(5)]
        ksdaskm.tvName2.text = SecGmeUtils.topNames[Random.nextInt(5)]
        ksdaskm.tvName3.text = SecGmeUtils.topNames[Random.nextInt(5)]
        ksdaskm.button3.setOnClickListener {
            startActivity(Intent(this,Second::class.java))
        }
    }
}