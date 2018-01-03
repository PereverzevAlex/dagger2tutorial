package com.smartsoft.pereverzev.dagger2newtutorial.screens.home

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.smartsoft.pereverzev.dagger2newtutorial.R
import com.smartsoft.pereverzev.dagger2newtutorial.models.GithubRepo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_repo.view.*
import org.joda.time.format.DateTimeFormat

/**
 * Created by alexander on 03.01.2018.
 */
class RepoListItem(context: Context, private val picasso: Picasso) : FrameLayout(context) {
    private val DATE_TIME_FORMATTER = DateTimeFormat.fullDate()

    fun setRepo(githubRepo: GithubRepo) {
        val locale = resources.configuration.locale
        repo_name.text = githubRepo.name
        repo_description.visibility = if (githubRepo.description.isNullOrEmpty()) View.GONE else View.VISIBLE
        repo_description.text = githubRepo.description

        repo_stars.text = String.format(locale, "%d", githubRepo.stargazersCount)
        repo_issues.text = String.format(locale, "%d", githubRepo.openIssuesCount)
        repo_forks.text = String.format(locale, "%d", githubRepo.forksCount)

        repo_updated_at.text = resources
                .getString(R.string.last_pushed, DATE_TIME_FORMATTER.print(githubRepo.updatedAt))

        picasso.load(githubRepo.owner?.avatarUrl)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(user_avatar)
    }

    init {
        View.inflate(getContext(), R.layout.list_item_repo, this)
    }
}