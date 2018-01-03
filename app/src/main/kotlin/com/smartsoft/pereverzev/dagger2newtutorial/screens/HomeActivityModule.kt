package com.smartsoft.pereverzev.dagger2newtutorial.screens

import dagger.Module
import dagger.Provides

/**
 * Created by alexander on 03.01.2018.
 */
@Module
class HomeActivityModule(private val homeActivity: HomeActivity) {

    @Provides
    @HomeActivityScope
    fun homeActivity(): HomeActivity = homeActivity
}