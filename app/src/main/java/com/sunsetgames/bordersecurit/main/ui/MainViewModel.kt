package com.sunsetgames.bordersecurit.main.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.ApplCla.Companion.C1
import com.sunsetgames.bordersecurit.ApplCla.Companion.MAIN_ID
import com.sunsetgames.bordersecurit.ApplCla.Companion.appsCheckChe
import com.sunsetgames.bordersecurit.ApplCla.Companion.geoCo
import com.sunsetgames.bordersecurit.ApplCla.Companion.urlMain
import com.sunsetgames.bordersecurit.main.di.viewModelModule
import com.sunsetgames.bordersecurit.main.model.Country
import com.sunsetgames.bordersecurit.main.model.Dev
import com.sunsetgames.bordersecurit.main.repo.CountryRepo
import com.sunsetgames.bordersecurit.main.repo.DevRepo
import com.sunsetgames.bordersecurit.main.util.Resort
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainViewModel(private val mainRepository: CountryRepo, private val devRepo: DevRepo, val application: Application, private val shP: SharedPreferences): ViewModel() {

    private val _advertIdClient = MutableLiveData<String?>()
    val advertIdClient: LiveData<String?>
        get() = _advertIdClient
//
//    private val _couData = MutableStateFlow("value")
//    val couData = _couData.asStateFlow()

//    private val _linka = MutableStateFlow("link")
//    val linka = _linka.asStateFlow()
//
//    private val _apppppsCh = MutableStateFlow("ll")
//    val appsCh = _apppppsCh.asStateFlow()
//
//    private val _geoC = MutableStateFlow("geoC")
//    val geoC = _geoC.asStateFlow()

//    val _devData = MutableStateFlow(Dev("" , "", ""))
//    val devData:  StateFlow<Dev> = _devData.asStateFlow()

    private val _devData = MutableLiveData<Dev?>()
    val devData: LiveData<Dev?>
        get() = _devData

    private val _countryAnswer = MutableLiveData<Country?>()
    val countryAnswer: LiveData<Country?>
        get() = _countryAnswer

    private var _currentMode = MutableLiveData<Resort>()
    val currentMode: LiveData<Resort>
        get() = _currentMode

    private var _appsCamp = MutableLiveData<String>()
    val appsCamp: LiveData<String>
        get() = _appsCamp

    private var _appChec = MutableLiveData<String>()
    val appChec: LiveData<String>
        get() = _appChec

    private var _viewUrl = MutableLiveData<String>()
    val viewUrl: LiveData<String>
        get() = _viewUrl

    private var _geoCh = MutableLiveData<String>()
    val geoCh: LiveData<String>
        get() = _geoCh


   init {
    viewModelScope.launch{
        dataGet()
    }
}
    suspend fun dataGet() {
        _devData.postValue(devRepo.getDataDev().body())
        _countryAnswer.postValue(mainRepository.getDat().body())
//        Log.d("SharedPr", "dataGet: ${shP.getString(urlMain, null)}, ${devData.value}")
    }
    fun getAdvertisingIdClient() {
        val advertisingIdClient = AdvertisingIdClient(application)
        advertisingIdClient.start()
        val idUserAdvertising = advertisingIdClient.info.id
         shP.edit().putString(MAIN_ID, idUserAdvertising).apply()

         Log.d("GetAdVID", "getAdvertisingIdClient: ${shP.getString(MAIN_ID, null)}")

     }

    fun initAppsFlyerLib(context: Context) {
        AppsFlyerLib.getInstance()
            .init("wyGzDcoEQJ2zAt47G7JkM", conversionDataListener, application)
        AppsFlyerLib.getInstance().start(context)
    }

    val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("campaign").toString()
            shP.edit().putString(C1, dataGotten).apply()
        }
        override fun onConversionDataFail(p0: String?) {}
        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
        override fun onAttributionFailure(p0: String?) {}
    }

}