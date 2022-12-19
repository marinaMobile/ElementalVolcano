package com.sunsetgames.bordersecurit.first

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentBalanceBinding


class BalanceFragment : Fragment() {

    var totalBalance = 0

    private var dddd: FragmentBalanceBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentBalanceBinding = null")


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
        dddd = FragmentBalanceBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

            val totalBalanceSP = requireActivity().getSharedPreferences(
                "TOTAL_BAL_SP",
                Context.MODE_PRIVATE
            )
            totalBalance = totalBalanceSP.getInt(First.key_balance, 500)

            yyyyy.tvUserBalance.text = totalBalance.toString()
            yyyyy.lottieNext.setOnClickListener {

                if (totalBalance > 50) {
                    findNavController().navigate(R.id.action_balanceFragment_to_betFragment)
                } else {
                    Snackbar.make(
                        yyyyy.root,
                        "Your balance is less than 50. Need more money",
                        Snackbar.LENGTH_LONG
                    ).show()
                }


            }

        } catch (e: Exception) {
            eeeeeeee()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        dddd = null
        super.onDestroy()
    }
}