package com.sunsetgames.bordersecurit.main.repo

import com.sunsetgames.bordersecurit.main.int.ApiInterface
import com.sunsetgames.bordersecurit.main.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CountryRepo(private val countryApi: ApiInterface) {

   suspend fun getDat() = countryApi.getData()

}