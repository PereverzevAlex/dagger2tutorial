package com.smarsoft.pereverzev.dagger2tutorial;

import com.smarsoft.pereverzev.dagger2tutorial.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, PicassoModule.class, ActivityModule.class})
public interface GithubApplicationComponent {

    Picasso getPicasso();

    GithubService getGithubService();
}
