package com.sunsetgames.bordersecurit.main.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.first.First
import com.sunsetgames.bordersecurit.main.util.GameAdapter
import com.sunsetgames.bordersecurit.main.util.GameClass
import com.sunsetgames.bordersecurit.second.StartSecScr
import com.sunsetgames.bordersecurit.sh.ShopActivity
import com.sunsetgames.bordersecurit.third.Third
import me.relex.circleindicator.CircleIndicator3


class Volcano : AppCompatActivity() {
    var cont: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volcano)

        setUpGamesPager()


    }

    fun setUpGamesPager() {
        val gamesViewPager: ViewPager2 = findViewById(R.id.gamesVP)
        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        gamesViewPager.clipToPadding= false
        gamesViewPager.clipChildren = false
        gamesViewPager.offscreenPageLimit = 3
        gamesViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        gamesViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compPageTransf = CompositePageTransformer()
        compPageTransf.addTransformer(MarginPageTransformer(10))
        compPageTransf.addTransformer { page, position ->
            val r: Float = 1 - Math.abs(position)
            page.scaleY = (0.85f+r*0.15f)
            page.setOnClickListener{
                when (cont) {
                    0 -> startActivity(Intent(this@Volcano, First::class.java))
                    1 -> startActivity(Intent(this@Volcano, StartSecScr::class.java))
                    2 -> startActivity(Intent(this@Volcano, Third::class.java))
                    3 -> startActivity(Intent(this@Volcano, ShopActivity::class.java))
                }
            }
        }
        gamesViewPager.setPageTransformer(compPageTransf)
        gamesViewPager.adapter = GameAdapter(getGames())
        indicator.setViewPager(gamesViewPager)


        gamesViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> cont = 0
                    1 -> cont = 1
                    2 -> cont = 2
                    3 -> cont = 3
                }
                Log.e("Selected_Page", position.toString())
            }

        })
    }



    fun getGames(): List<GameClass> {
        val games: List<GameClass>


        val first = GameClass()
        first.name = "Black Jack"
        first.category = "Collect 21 points from cards"
        first.poster = R.drawable.sasha_nice_card_back

        val second = GameClass()
        second.name = "Slot Machine"
        second.category = "Spin and win"
        second.poster = R.drawable.sec_gma_main

        val third = GameClass()
        third.name = "Craps"
        third.category = "Dice Game"
        third.poster = R.drawable.diceback

        val forth = GameClass()
        forth.name = "Shop"
        forth.category = "Boost your diamond points"
        forth.poster = R.drawable.diam

        games = arrayListOf(first, second, third, forth)

        return games
    }




}

