package com.test.worldwether.di

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: List<Module>
    get() = listOf(homeScreenModule)

val homeScreenModule = module {

}