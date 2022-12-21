package com.sunsetgames.bordersecurit.main.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sunsetgames.bordersecurit.main.int.ApiInterface
import com.sunsetgames.bordersecurit.main.int.HostInterface
import com.sunsetgames.bordersecurit.main.repo.CountryRepo
import com.sunsetgames.bordersecurit.main.repo.DevRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single  <HostInterface> (named("HostInter")){
    get<Retrofit>(named("RetroDev"))
        .create(HostInterface::class.java)
}

    single <ApiInterface> (named("ApiInter")) {
        get<Retrofit>(named("RetroCountry"))
            .create(ApiInterface::class.java)
    }
    single<Retrofit>(named("RetroCountry")) {
        Retrofit.Builder()
            .baseUrl("http://pro.ip-api.com/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single <Retrofit>(named("RetroDev")){
        Retrofit.Builder()
            .baseUrl("http://elementalvolcano.live/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory (named("CountryRep")) {
        CountryRepo(get(named("ApiInter")))
    }

    factory  (named("DevRep")){
        DevRepo(get(named("HostInter")))
    }
    single{
        provideGson()
    }
    single(named("SharedPreferences")){
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }


}
fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun getSharedPrefs(androidApplication: Application): SharedPreferences{
    return  androidApplication.getSharedPreferences("default",  Context.MODE_PRIVATE)
}

