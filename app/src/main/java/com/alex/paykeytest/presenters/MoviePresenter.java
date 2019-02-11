package com.alex.paykeytest.presenters;

import com.alex.paykeytest.model.dto.MovieDetails;
import com.alex.paykeytest.model.network.ApiRequestServiceProvider;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.view.MovieView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter extends Presenter<MovieView> {

	public MoviePresenter(MovieView view) {
		super(view);
	}

	public void loadMovieDetails(String id) {
		disposable = ApiRequestServiceProvider.apiRequestService()
				.getMovieDetails(id, UrlStorage.API_KEY, "en-US")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onLoaded, this::onError);
	}

	private void onLoaded(MovieDetails movieDetails) {
		view.hideLoading();
		view.update(movieDetails);
		disposable.dispose();
	}

	private void onError(Throwable error) {
		view.hideLoading();
		view.reportError(error.getLocalizedMessage());
		disposable.dispose();
	}

}
