package com.sunsetgames.bordersecurit.main.ui.frag

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.ApplCla.Companion.MAIN_ID
import com.sunsetgames.bordersecurit.main.ui.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import okhttp3.internal.wait
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class FirstMainFragment : Fragment() {
    val viewMainModel by viewModel<MainViewModel>()
    val shareP: SharedPreferences by inject(named("SharedPreferences"))

    override fun onAttach(context: Context) {
        super.onAttach(context)

        lifecycleScope.launch(Dispatchers.IO) {
            viewMainModel.getAdvertisingIdClient()
        }

        viewMainModel.countryAnswer.observe(this) {
            if (it!=null) {
                val country = viewMainModel.countryAnswer.value?.countryCode
                shareP.edit().putString("countryCode", country).apply()
                findNavController().navigate(com.sunsetgames.bordersecurit.R.id.action_firstFragment_to_secondMainFragment)
            }}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.sunsetgames.bordersecurit.R.layout.fragment_main_first, container, false)
    }
}


