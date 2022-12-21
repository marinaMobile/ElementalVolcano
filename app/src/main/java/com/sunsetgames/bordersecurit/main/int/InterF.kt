package com.sunsetgames.bordersecurit.main.int

import com.sunsetgames.bordersecurit.main.model.Country
import com.sunsetgames.bordersecurit.main.model.Dev
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("json/?key=ZSSz86ONagSoYRv")
    suspend fun getData(): Response<Country>
}


interface HostInterface {
    @GET("typo.json")
    suspend fun getDataDev(): Response<Dev>
}

