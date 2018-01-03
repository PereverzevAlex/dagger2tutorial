package com.smartsoft.pereverzev.dagger2newtutorial

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smartsoft.pereverzev.dagger2newtutorial.network.DateTimeConverter
import com.smartsoft.pereverzev.dagger2newtutorial.network.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by alexander on 03.01.2018.
 */
@Module(includes = [(NetworkModule::class)])
class GithubServiceModule {

    @Provides
    @GithubApplicationScope
    fun githubService(gitHubRetrofit: Retrofit): GithubService = gitHubRetrofit.create<GithubService>(GithubService::class.java!!)


    @Provides
    @GithubApplicationScope
    fun gson(): Gson = GsonBuilder()
            .registerTypeAdapter(DateTime::class.java, DateTimeConverter())
            .create()

    @Provides
    @GithubApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .baseUrl("https://api.github.com/")
                    .build()
}