package com.sunsetgames.bordersecurit.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentGameThirdBinding
import kotlin.random.Random


class GameThird : Fragment() {

    private var dddd: FragmentGameThirdBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentGameThirdBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dddd = FragmentGameThirdBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yyyyy.butnRoll.setOnClickListener{
            rollDice()
            val dic1 = yyyyy.diceOne
            val dic2 = yyyyy.diceTwo
            dic1.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.rotate))
            dic2.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate))

        }
    }

    fun rollDice() {
        val randInt = Random.nextInt(6)+1
        val randIntTwo = Random.nextInt(6)+1
//        val anim = AnimationUtils.loadAnimation(requireActivity(), R.anim.rotate)

        val drawRes =
            when (randInt){
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                else -> R.drawable.dice6
        }

        val drawResTwo =
            when (randIntTwo){
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                else -> R.drawable.dice6
            }

        yyyyy.diceOne.setImageResource(drawRes)
        yyyyy.diceTwo.setImageResource(drawResTwo)


        val sum = randInt+randIntTwo

        if(sum == 7|| sum == 11) {
            Toast.makeText(requireActivity(), sum.toString(), Toast.LENGTH_SHORT).show()
        } else if (sum == 2|| sum == 3 || sum == 12) {
            Toast.makeText(requireActivity(), sum.toString(), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), sum.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}