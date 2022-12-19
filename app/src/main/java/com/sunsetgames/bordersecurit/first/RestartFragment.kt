package com.sunsetgames.bordersecurit.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentRestartBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestartFragment : Fragment() {

    private var dddd: FragmentRestartBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentRestartBinding = null")


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
        dddd = FragmentRestartBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

            lifecycleScope.launch {

                delay(1500)
                findNavController().navigate(R.id.action_restartFragment_to_balanceFragment)
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