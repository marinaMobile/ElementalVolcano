package com.sunsetgames.bordersecurit.third

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.FragmentGameeBinding
import com.sunsetgames.bordersecurit.databinding.FragmentStartThirdBinding


class StartThird : Fragment() {

    private var dddd: FragmentStartThirdBinding? = null
    private val yyyyy
        get() = dddd ?: throw RuntimeException("FragmentStartThirdBinding = null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dddd = FragmentStartThirdBinding.inflate(inflater, container, false)
        return yyyyy.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yyyyy.starBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startThird_to_gameThird)
        }
    }


}