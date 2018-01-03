package com.smartsoft.pereverzev.dagger2newtutorial.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.smartsoft.pereverzev.dagger2newtutorial.GithubApplication
import com.smartsoft.pereverzev.dagger2newtutorial.R
import com.smartsoft.pereverzev.dagger2newtutorial.models.GithubRepo
import com.smartsoft.pereverzev.dagger2newtutorial.network.GithubService
import com.smartsoft.pereverzev.dagger2newtutorial.screens.home.AdapterRepos
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by alexander on 28.12.2017.
 */
class HomeActivity : AppCompatActivity() {
    private var reposCall: Call<List<GithubRepo>>? = null

    @Inject
    lateinit var githubService: GithubService

    @Inject
    lateinit var adapterRepos: AdapterRepos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(HomeActivityModule(this))
                .githubApplicationComponent(GithubApplication.get(this).component())
                .build()

        component.injectHomeActivity(this)
        repo_home_list.adapter = adapterRepos
        reposCall = githubService.getAllRepos()
        reposCall!!.enqueue(object : Callback<List<GithubRepo>> {
            override fun onResponse(call: Call<List<GithubRepo>>, response: Response<List<GithubRepo>>) {
                adapterRepos.swapData(response.body())
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error getting repos " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (reposCall != null) {
            reposCall!!.cancel()
        }
    }
}