package com.sunsetgames.bordersecurit

import android.app.Application
import android.content.Context
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import com.qonversion.android.sdk.Qonversion
import com.sunsetgames.bordersecurit.main.di.appModule
import com.sunsetgames.bordersecurit.main.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*

class ApplCla: Application() {

    companion object {
        const val ONESIGNAL_APP_ID = "7f6166a4-54b7-48a8-b527-e1c0fd83b631"
        val myId: String = "myID"
        var instId: String? = "instID"
        var urlMain: String = "link"
        var MAIN_ID: String? = "main_id"
        var C1: String? = "c11"
        const val myTrId = "07188766525944037458"
        val appsCheckChe: String = "appsCheckChe"
        val geoCo: String = "geoCo"
        val userCo: String = "userCo"
        var BALANCE_VOLCANOS = 3499
    }


    override fun onCreate() {

        super.onCreate()
        val shP = getSharedPreferences("default", Context.MODE_PRIVATE)
        val settings = getSharedPreferences("PREFS_NAME", 0)

        val trackerParams = MyTracker.getTrackerParams()
        val trackerConfig = MyTracker.getTrackerConfig()
        val instID = MyTracker.getInstanceId(this)
        trackerConfig.isTrackingLaunchEnabled = true
        val IDIN = UUID.randomUUID().toString()

        if (settings.getBoolean("my_first_time", true)) {
            trackerParams.setCustomUserId(IDIN)
            shP.edit().putString(myId, IDIN).apply()
            shP.edit().putString(instId, instID).apply()
            settings.edit().putBoolean("my_first_time", false).apply()

            val balance_sp = getSharedPreferences("VOLCANO_BAL_SP", MODE_PRIVATE)
            balance_sp.edit().putInt(BALANCE_VOLCANOS.toString(),3499).apply()

        } else {
            val shIDIN = shP.getString(myId, IDIN)
            trackerParams.setCustomUserId(shIDIN)
        }
        MyTracker.initTracker(myTrId, this)

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@ApplCla)
            modules(listOf(
                viewModelModule, appModule
            ))
        }

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        Qonversion.setDebugMode()
        Qonversion.launch(this, "fMOlxDGSPwLU_BpeCQXo1tIR5cNqfsk-", false)

    }

}