package com.smartsoft.pereverzev.dagger2oldtutorial.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.smartsoft.pereverzev.dagger2oldtutorial.GithubApplication;
import com.smartsoft.pereverzev.dagger2oldtutorial.models.GithubRepo;
import com.smartsoft.pereverzev.dagger2oldtutorial.network.GithubService;
import com.smartsoft.pereverzev.dagger2oldtutorial.screens.home.AdapterRepos;
import com.smartsoft.pereverzev.dagger2newtutorial.R;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    Call<List<GithubRepo>> reposCall;

    @Inject
    GithubService githubService;

    @Inject
    AdapterRepos adapterRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeActivityComponent component = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .githubApplicationComponent(GithubApplication.get(this).component())
                .build();

        component.injectHomeActivity(this);
        ListView listView = findViewById(R.id.repo_home_list);
        listView.setAdapter(adapterRepos);

        reposCall = githubService.getAllRepos();
        reposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                adapterRepos.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reposCall != null) {
            reposCall.cancel();
        }
    }
}
