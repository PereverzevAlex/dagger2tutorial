package com.smartsoft.pereverzev.dagger2newtutorial

import android.app.Activity
import android.app.Application
import com.smartsoft.pereverzev.dagger2newtutorial.network.GithubService
import com.squareup.picasso.Picasso
import timber.log.Timber

/**
 * Created by alexander on 03.01.2018.
 */
class GithubApplication : Application() {
    private lateinit var component: GithubApplicationComponent

    companion object {
        fun get(activity: Activity): GithubApplication {
            return activity.application as GithubApplication
        }
    }

    private var githubService: GithubService? = null
    private var picasso: Picasso? = null

    //   Activity

    //GithubService   picasso

    //retrofit    OkHttp3Downloader

    //gson      okhttp

    //      logger    cache

    //      timber           file

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        component = DaggerGithubApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        githubService = component.getGithubService()
        picasso = component.getPicasso()
    }

    fun component() = component
}