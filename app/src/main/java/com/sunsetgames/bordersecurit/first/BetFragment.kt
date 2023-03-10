package com.sunsetgames.bordersecurit.first

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentBetBinding


class BetFragment : Fragment() {
    var userBet = 0
    var totalBalance = 0

    private var dddd: FragmentBetBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentBetBinding = null")


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
        dddd = FragmentBetBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {



            val totalBalanceSP = requireActivity().getSharedPreferences(
                "TOTAL_BAL_SP",
                Context.MODE_PRIVATE
            )

            totalBalance = totalBalanceSP.getInt(First.key_balance, 500)
            userBet = totalBalanceSP.getInt(First.key_bet, 100)

            yyyyy.tvUserBet.text = userBet.toString()
            yyyyy.btnAddBet.setOnClickListener {
                userBet = userBet + 20



                yyyyy.tvUserBet.text = userBet.toString()
            }

            yyyyy.btnMinusBet.setOnClickListener {
                if (userBet >= 40) {
                    userBet -= 20



                    yyyyy.tvUserBet.text = userBet.toString()


                }else{
                    Snackbar.make(
                        yyyyy.root,
                        "Bet can`t be lower than 20",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

            yyyyy.btnAllInOne.setOnClickListener {
                userBet = totalBalance
                yyyyy.tvUserBet.text = userBet.toString()
            }

            yyyyy.lottBtnConfirm.setOnClickListener {
                initAlertDialog(totalBalanceSP)

            }

        } catch (e: Exception) {
            eeeeeeee()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun staaartGame(totalBalanceSP: SharedPreferences) {
        if (totalBalance >= userBet) {

            val editPref = totalBalanceSP.edit()
            editPref.putInt(First.key_bet, userBet)
            editPref.apply()
            findNavController().navigate(R.id.action_betFragment_to_gameeFragment)
        } else {
            Snackbar.make(
                yyyyy.root,
                "Your balance is lower than current BET",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroy() {
        dddd = null
        super.onDestroy()
    }

    private fun initAlertDialog(totalBalanceSP: SharedPreferences) {
        AlertDialog.Builder(requireContext())
            .setTitle("Start Game")
            .setMessage("Are you ready?")
            .setPositiveButton("Yes") { _, _ ->
                staaartGame(totalBalanceSP)
            }
            .setNegativeButton("No, exit") { _, _ ->
                requireActivity().onBackPressed()
            }

            .setCancelable(true)
            .create()
            .show()
    }
}