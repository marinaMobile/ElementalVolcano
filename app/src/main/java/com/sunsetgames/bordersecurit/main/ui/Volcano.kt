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
import com.sunsetgames.bordersecurit.second.Second
import com.sunsetgames.bordersecurit.second.StartSecScr
import com.sunsetgames.bordersecurit.third.Third


class Volcano : AppCompatActivity() {
    var cont: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volcano)

        setUpGamesPager()


    }

    fun setUpGamesPager() {
        val gamesViewPager: ViewPager2 = findViewById(R.id.gamesVP)
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
                }
            }
        }
        gamesViewPager.setPageTransformer(compPageTransf)
        gamesViewPager.adapter = GameAdapter(getGames())

        gamesViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> cont = 0
                    1 -> cont = 1
                    2 -> cont = 2
                }
                Log.e("Selected_Page", position.toString())
            }

        })
    }



    fun getGames(): List<GameClass> {
        val games: List<GameClass>


        val first = GameClass()
        first.name = "First Game"
        first.category = "First Category"
        first.poster = R.drawable.roul

        val second = GameClass()
        second.name = "Second Game"
        second.category = "Second Category"
        second.poster = R.drawable.roul

        val third = GameClass()
        third.name = "Third Game"
        third.category = "Third Category"
        third.poster = R.drawable.roul

        games = arrayListOf(first, second, third)

        return games
    }




}

