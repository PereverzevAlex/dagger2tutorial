package com.smartsoft.pereverzev.dagger2newtutorial.network

import com.smartsoft.pereverzev.dagger2newtutorial.models.GithubRepo
import com.smartsoft.pereverzev.dagger2newtutorial.models.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by alexander on 03.01.2018.
 */
interface GithubService {
    @GET("users/{username}/repos")
    fun getReposForUser(@Path("username") username: String): Call<List<GithubRepo>>

    @GET("repositories")
    fun getAllRepos(): Call<List<GithubRepo>>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<GithubUser>
}