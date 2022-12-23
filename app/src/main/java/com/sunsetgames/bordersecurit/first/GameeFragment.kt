package com.sunsetgames.bordersecurit.first

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentGameeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameeFragment : Fragment() {

    val listCards by lazy {
        mapOf(
            1 to ContextCompat.getDrawable(requireActivity(), R.drawable.oneofdiamonds),
            2 to ContextCompat.getDrawable(requireActivity(), R.drawable.two),
            3 to ContextCompat.getDrawable(requireActivity(), R.drawable.threeofdiamonds),
            4 to ContextCompat.getDrawable(requireActivity(), R.drawable.four),
            5 to ContextCompat.getDrawable(requireActivity(), R.drawable.five),
            6 to ContextCompat.getDrawable(requireActivity(), R.drawable.sixofdiamonds),
            7 to ContextCompat.getDrawable(requireActivity(), R.drawable.seven),
            8 to ContextCompat.getDrawable(requireActivity(), R.drawable.eight),
            9 to ContextCompat.getDrawable(requireActivity(), R.drawable.nine),
            10 to ContextCompat.getDrawable(requireActivity(), R.drawable.tenofdiamonds),
            11 to ContextCompat.getDrawable(requireActivity(), R.drawable.elevenofdiamonds),
        )
    }

    val listBinding by lazy {
        listOf(
            yyyyy.imgCard1,
            yyyyy.imgCard2,
            yyyyy.imgCard3,
            yyyyy.imgCard4,
            yyyyy.imgCard5,
        )
    }

    var totalBalance = 0
    var userBet = 0

    var userPoints = 0

    private var dddd: FragmentGameeBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentGameeBinding = null")


    private fun eeeeeeee() {
        Snackbar.make(
            yyyyy.root,
            "Error, error, error",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dddd = FragmentGameeBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

            yyyyy.btnImgClose.setOnClickListener {
                requireActivity().onBackPressed()
            }

            mmmmm()

            val totalBalanceSP = requireActivity().getSharedPreferences(
                First.MAIN_KEY_SHARED_PREF_BALANVE,
                Context.MODE_PRIVATE
            )
            totalBalance = totalBalanceSP.getInt(First.key_balance, 1000)
            userBet = totalBalanceSP.getInt(First.key_bet, 100)
            yyyyy.tvUserPointsCount.text = userPoints.toString()

            yyyyy.btnTakeCard.setOnClickListener {
                lifecycleScope.launch {
                    yyyyy.btnTakeCard.alpha = 0.2f
                    yyyyy.btnTakeCard.isEnabled = false
                    yyyyy.btnTakeCard.isPressed = true
                    val randomCard = listCards.keys.shuffled().random()
                    val imageCard = listCards[randomCard]

                    loadCard(imageCard)

                    userPoints = userPoints + randomCard
                    yyyyy.tvUserPointsCount.text = userPoints.toString()
                    delay(600)



                    if (userPoints >21){
                        Snackbar.make(
                            yyyyy.root,
                            "You lose $userBet$. Try again",
                            Snackbar.LENGTH_LONG
                        ).show()

                        totalBalance = totalBalance - userBet
                        val editPref = totalBalanceSP.edit()
                        editPref.putInt(First.key_balance, totalBalance)
                        editPref.apply()


                        delay(1000)
                        yyyyy.btnTakeCard.isEnabled = true
                        yyyyy.btnTakeCard.isPressed = false
                        yyyyy.btnTakeCard.alpha = 1.0f
                        findNavController().navigate(R.id.action_gameeFragment_to_restartFragment)
                    }
                    if (userPoints ==21){
                        Snackbar.make(
                            yyyyy.root,
                            "You WIN ${userBet}$",
                            Snackbar.LENGTH_LONG
                        ).show()

                        totalBalance = totalBalance + userBet
                        val editPref = totalBalanceSP.edit()
                        editPref.putInt(First.key_balance, totalBalance)
                        editPref.apply()

                        delay(1000)
                        yyyyy.btnTakeCard.isEnabled = true
                        yyyyy.btnTakeCard.isPressed = false
                        yyyyy.btnTakeCard.alpha = 1.0f
                        findNavController().navigate(R.id.action_gameeFragment_to_restartFragment)
                    }
                    delay(500)
                    yyyyy.btnTakeCard.isEnabled = true
                    yyyyy.btnTakeCard.isPressed = false
                    yyyyy.btnTakeCard.alpha = 1.0f

                }
            }

            yyyyy.btnImgRules.setOnClickListener {
                Snackbar.make(
                    yyyyy.root,
                    "Collect 21 points!",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {
            eeeeeeee()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadCard(imageCard: Drawable?) {
        for (i in listBinding) {
            if (i.contentDescription == "empty") {
                i.setImageDrawable(imageCard)
                i.contentDescription = "card"
                return
            }
        }
    }

    override fun onDestroy() {
        dddd = null
        super.onDestroy()
    }

    override fun onPause() {
        mmmmm()
        super.onPause()
    }

    private fun mmmmm() {
        for (i in listBinding) {
            i.contentDescription = "empty"
            i.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.sasha_card_baccck))
        }
    }
}