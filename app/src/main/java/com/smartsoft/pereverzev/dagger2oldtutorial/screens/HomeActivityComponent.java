package com.smartsoft.pereverzev.dagger2oldtutorial.screens;


import com.smartsoft.pereverzev.dagger2oldtutorial.GithubApplicationComponent;

import dagger.Component;

@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubApplicationComponent.class)
public interface HomeActivityComponent {

    void injectHomeActivity(HomeActivity homeActivity);
}
