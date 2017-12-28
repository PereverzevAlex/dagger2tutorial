package com.smartsoft.pereverzev.dagger2tutorial.screens.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartsoft.pereverzev.dagger2tutorial.R;
import com.smartsoft.pereverzev.dagger2tutorial.models.GithubRepo;
import com.squareup.picasso.Picasso;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;


@SuppressLint("ViewConstructor")
public class RepoListItem extends FrameLayout {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.fullDate();
    private final Picasso picasso;
    ImageView avatarImage;
    TextView name;
    TextView description;
    TextView stars;
    TextView issues;
    TextView forks;
    TextView updatedAt;

    public RepoListItem(Context context, Picasso picasso) {
        super(context);
        this.picasso = picasso;
        inflate(getContext(), R.layout.list_item_repo, this);
        avatarImage = findViewById(R.id.user_avatar);
        name = findViewById(R.id.repo_name);
        description = findViewById(R.id.repo_description);
        stars = findViewById(R.id.repo_stars);
        issues = findViewById(R.id.repo_issues);
        forks = findViewById(R.id.repo_forks);
        updatedAt = findViewById(R.id.repo_updated_at);
    }

    public void setRepo(GithubRepo githubRepo) {
        Locale locale = getResources().getConfiguration().locale;

        name.setText(githubRepo.name);
        description.setVisibility(TextUtils.isEmpty(githubRepo.description) ? GONE : VISIBLE);
        description.setText(githubRepo.description);

        stars.setText(String.format(locale, "%d", githubRepo.stargazersCount));
        issues.setText(String.format(locale, "%d", githubRepo.openIssuesCount));
        forks.setText(String.format(locale, "%d", githubRepo.forksCount));

        updatedAt.setText(getResources()
                .getString(R.string.last_pushed, DATE_TIME_FORMATTER.print(githubRepo.updatedAt)));

        picasso.load(githubRepo.owner.avatarUrl)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(avatarImage);
    }
}
