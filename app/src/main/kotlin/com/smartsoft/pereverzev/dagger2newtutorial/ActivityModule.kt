package com.smartsoft.pereverzev.dagger2newtutorial

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by alexander on 03.01.2018.
 */
@Module
class ActivityModule(context: Activity) {
    private val context: Activity = context

    @Provides
    @GithubApplicationScope
    @Named("activity_context")
    fun context(): Context = context
}