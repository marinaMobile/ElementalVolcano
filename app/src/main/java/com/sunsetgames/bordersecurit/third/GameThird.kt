package com.sunsetgames.bordersecurit.third

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.ApplCla.Companion.BALANCE_VOLCANOS
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentGameThirdBinding
import com.sunsetgames.bordersecurit.main.ui.Volcano
import com.sunsetgames.bordersecurit.second.StartSecScr
import kotlin.random.Random


class GameThird : Fragment() {
    var scoreInt = 50

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

        val totalBalanceSP: SharedPreferences = requireActivity().getSharedPreferences("VOLCANO_BAL_SP",
            AppCompatActivity.MODE_PRIVATE
        )

        var totalBalance: Int = totalBalanceSP.getInt(ApplCla.BALANCE_VOLCANOS.toString(), 0)

        yyyyy.butnRoll.setOnClickListener{
            val randInt = Random.nextInt(6)+1
            val randIntTwo = Random.nextInt(6)+1

            totalBalance -= scoreInt
            yyyyy.balanceTxt.text = totalBalance.toString()


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

            when (sum) {
                7, 11 -> {
                    val score = scoreInt*2
                    totalBalance += score
                    yyyyy.balanceTxt.text = (totalBalance).toString()
//                    yyyyy.balanceTxt.text = totalBalance.plus(scoreInt*3).toString()
                    yyyyy.txtWin.text = "You win $score"
                }
                2, 3, 12 -> {
                    totalBalance -= scoreInt
                    yyyyy.balanceTxt.text = (totalBalance).toString()
//                    yyyyy.balanceTxt.text = (totalBalance+scoreInt).toString()
//                    yyyyy.balanceTxt.text = totalBalance.minus(scoreInt).toString()
                    yyyyy.txtWin.text = "You lose $scoreInt"
                }
                else -> {
                    totalBalance += scoreInt
                    yyyyy.balanceTxt.text = (totalBalance).toString()
                    yyyyy.txtWin.text = "Point! Try again!"
                }
            }
        }



        //setting data block
        yyyyy.betInt.text = scoreInt.toString()

        yyyyy.balanceTxt.text = totalBalance.toString()
        //setting data block end

        yyyyy.btnPlus.setOnClickListener {
            scoreInt += 50
            yyyyy.betInt.text = scoreInt.toString()

            if (scoreInt >0){
                yyyyy.btnMinus.isClickable = true
                yyyyy.betInt.textSize = 36F
            }
            val balanceCurrent = yyyyy.balanceTxt.text.toString()
            val balanceCurrentInt = balanceCurrent.toInt()

            if (scoreInt==balanceCurrentInt){
                yyyyy.btnPlus.isClickable = false
            }
        }

        yyyyy.btnMinus.setOnClickListener {
            scoreInt -= 50
            yyyyy.betInt.text = scoreInt.toString()

            if (scoreInt == 0){
                yyyyy.betInt.text = "50 is the smallest bet!"
                yyyyy.betInt.textSize = 24F
                yyyyy.btnMinus.isClickable = false
            }

            val balanceCurrent = yyyyy.balanceTxt.text.toString()
            val balanceCurrentInt = balanceCurrent.toInt()

            if (scoreInt<balanceCurrentInt){
                yyyyy.btnPlus.isClickable = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val finalBalanceString = yyyyy.balanceTxt.text.toString()
        val finalBalance = finalBalanceString.toInt()

        val totalBalanceSP = requireActivity().getSharedPreferences("VOLCANO_BAL_SP", AppCompatActivity.MODE_PRIVATE)
        totalBalanceSP.edit().putInt(BALANCE_VOLCANOS.toString(), finalBalance).apply()


        val intent = Intent(activity, Volcano::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val finalBalanceString = yyyyy.balanceTxt.text.toString()
        val finalBalance = finalBalanceString.toInt()

        val totalBalanceSP = requireActivity().getSharedPreferences("VOLCANO_BAL_SP", AppCompatActivity.MODE_PRIVATE)
        totalBalanceSP.edit().putInt(BALANCE_VOLCANOS.toString(), finalBalance).apply()

        val intent = Intent(activity, Volcano::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        val finalBalanceString = yyyyy.balanceTxt.text.toString()
        val finalBalance = finalBalanceString.toInt()

        val totalBalanceSP = requireActivity().getSharedPreferences("VOLCANO_BAL_SP", AppCompatActivity.MODE_PRIVATE)
        totalBalanceSP.edit().putInt(BALANCE_VOLCANOS.toString(), finalBalance).apply()


        val intent = Intent(activity, Volcano::class.java)
        startActivity(intent)
    }



}