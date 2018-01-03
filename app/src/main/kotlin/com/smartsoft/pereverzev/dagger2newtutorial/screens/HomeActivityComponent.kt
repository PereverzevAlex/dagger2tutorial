package com.smartsoft.pereverzev.dagger2newtutorial.screens

import com.smartsoft.pereverzev.dagger2newtutorial.GithubApplicationComponent
import dagger.Component

/**
 * Created by alexander on 03.01.2018.
 */
@HomeActivityScope
@Component(modules = [HomeActivityModule::class], dependencies = [GithubApplicationComponent::class])
interface HomeActivityComponent {
    fun injectHomeActivity(homeActivity: HomeActivity)
}