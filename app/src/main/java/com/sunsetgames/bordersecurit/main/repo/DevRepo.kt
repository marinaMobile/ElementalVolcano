package com.sunsetgames.bordersecurit.main.repo

import com.sunsetgames.bordersecurit.main.int.HostInterface
import com.sunsetgames.bordersecurit.main.model.Dev
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DevRepo(private val devData: HostInterface) {

suspend fun getDataDev() = devData.getDataDev()

}