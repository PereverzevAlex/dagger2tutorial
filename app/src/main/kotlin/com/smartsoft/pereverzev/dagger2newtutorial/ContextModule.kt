package com.smartsoft.pereverzev.dagger2newtutorial

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by alexander on 30.12.2017.
 */
@Module
class ContextModule(context: Context) {
    private val context: Context = context.applicationContext

    @Provides
    @GithubApplicationScope
    @ApplicationContext
    fun context(): Context = context
}