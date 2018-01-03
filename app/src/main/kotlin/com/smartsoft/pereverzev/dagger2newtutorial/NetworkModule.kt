package com.smartsoft.pereverzev.dagger2newtutorial

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * Created by alexander on 03.01.2018.
 */
@Module(includes = [(ContextModule::class)])
class NetworkModule {
    @Provides
    @GithubApplicationScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.i(message) })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @GithubApplicationScope
    fun cache(cacheFile: File): Cache
            = Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10MB Cahe

    @Provides
    @GithubApplicationScope
    fun cacheFile(@ApplicationContext context: Context): File =
            File(context.cacheDir, "okhttp_cache")

    @Provides
    @GithubApplicationScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .cache(cache)
                    .build()
}