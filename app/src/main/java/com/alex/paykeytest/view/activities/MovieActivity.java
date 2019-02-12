package com.alex.paykeytest.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.paykeytest.R;
import com.alex.paykeytest.model.dto.MovieDetails;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.presenters.MoviePresenter;
import com.alex.paykeytest.util.GlideApp;
import com.alex.paykeytest.view.MovieView;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class MovieActivity extends AppCompatActivity implements MovieView {

	private ImageView backdropView;
	private TextView titleView;
	private TextView countriesView;
	private TextView taglineView;
	private TextView descriptionView;
	private ProgressBar progressBar;

	private MoviePresenter moviePresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);

		setupViews();

		moviePresenter = new MoviePresenter(this);
		moviePresenter.loadMovieDetails(getIntent().getStringExtra(UrlStorage.ID));
	}

	private void setupViews() {
		setupBackdropView();
		setupTitleView();
		setupCountriesView();
		setupTaglineView();
		setupDescriptionView();
		setupProgressBar();
	}

	private void setupBackdropView() {
		backdropView = findViewById(R.id.backdrop);
	}

	private void setupTitleView() {
		titleView = findViewById(R.id.title);
	}

	private void setupCountriesView() {
		countriesView = findViewById(R.id.countries);
	}

	private void setupTaglineView() {
		taglineView = findViewById(R.id.tagline);
	}

	private void setupDescriptionView() {
		descriptionView = findViewById(R.id.description);
	}

	private void setupProgressBar() {
		progressBar = findViewById(R.id.movie_progress);
	}

	@Override
	public void update(MovieDetails movieDetails) {
		GlideApp.with(this)
				.load(UrlStorage.IMAGE_URL + movieDetails.getBackdropPath())
				.centerInside()
				.transition(DrawableTransitionOptions.withCrossFade(100))
				.into(backdropView);

		StringBuilder countries = new StringBuilder();
		for (int i = 0; i < movieDetails.getProductionCountries().size(); i++) {
			countries.append(movieDetails.getProductionCountries().get(i).getName()).append(", ");
		}

		titleView.setText(movieDetails.getOriginalTitle());
		countriesView.setText(countries.subSequence(0, countries.lastIndexOf(",")));
		taglineView.setText(movieDetails.getTagline());
		descriptionView.setText(movieDetails.getOverview());
	}

	@Override
	public void showLoading() {
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		progressBar.setVisibility(View.GONE);
	}

	@Override
	public void reportError(String errorMessage) {
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		moviePresenter.dispose();
	}
}
