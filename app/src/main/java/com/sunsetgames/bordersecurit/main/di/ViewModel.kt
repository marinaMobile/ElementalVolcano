package com.sunsetgames.bordersecurit.main.di

import com.sunsetgames.bordersecurit.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(named("CountryRep")), get(named("DevRep")), get(), get(named("SharedPreferences")))
    }
}