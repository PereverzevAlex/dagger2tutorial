package com.smartsoft.pereverzev.dagger2newtutorial.screens.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.smartsoft.pereverzev.dagger2newtutorial.models.GithubRepo
import com.smartsoft.pereverzev.dagger2newtutorial.screens.HomeActivity
import com.squareup.picasso.Picasso
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by alexander on 03.01.2018.
 */
class AdapterRepos @Inject constructor(val context: HomeActivity, val picasso: Picasso) : BaseAdapter() {

    private val repoList = ArrayList<GithubRepo>(0)

    override fun getCount() = repoList.size

    override fun getItem(position: Int)= repoList[position]

    override fun hasStableIds() = true

    override fun getItemId(position: Int) = repoList[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val repoListItem = if (convertView == null) {
            RepoListItem(context, picasso)
        } else {
            RepoListItem::class.java.cast(convertView)
        }

        repoListItem.setRepo(repoList[position])

        return repoListItem
    }

    fun swapData(githubRepos: Collection<GithubRepo>?) {
        repoList.clear()
        if (githubRepos != null) {
            repoList.addAll(githubRepos)
        }
        notifyDataSetChanged()
    }
}