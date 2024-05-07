package com.lamz.salamcafe.di

import com.lamz.salamcafe.data.DataRepository
import com.lamz.salamcafe.ui.screen.catalog.CatalogViewModel
import com.lamz.salamcafe.ui.screen.detail.DetailViewModel
import com.lamz.salamcafe.ui.screen.home.HomeViewmodel
import com.lamz.salamcafe.ui.screen.order.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { DataRepository() }
    viewModel { HomeViewmodel(get()) }
    viewModel { OrderViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { CatalogViewModel(get()) }
}
