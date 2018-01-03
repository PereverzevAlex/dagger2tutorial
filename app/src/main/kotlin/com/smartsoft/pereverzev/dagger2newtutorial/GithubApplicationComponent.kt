package com.smartsoft.pereverzev.dagger2newtutorial

import com.smartsoft.pereverzev.dagger2newtutorial.network.GithubService
import com.squareup.picasso.Picasso
import dagger.Component

/**
 * Created by alexander on 03.01.2018.
 */
@GithubApplicationScope
@Component(modules = [(GithubServiceModule::class), (PicassoModule::class), (ActivityModule::class)])
interface GithubApplicationComponent {

    fun getPicasso(): Picasso

    fun getGithubService(): GithubService
}