package com.alex.paykeytest.presenters;

import com.alex.paykeytest.model.dto.MovieItem;
import com.alex.paykeytest.model.network.ApiRequestServiceProvider;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.view.MainView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends Presenter<MainView> {

	public MainPresenter(MainView view) {
		super(view);
	}

	public void loadMovies() {
		disposable = ApiRequestServiceProvider.apiRequestService()
				.getMovies(UrlStorage.API_KEY, "en-US", 1)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(response -> onLoaded(response.getResults()), this::onError);
	}

	public void searchMovies(String searchQuery) {
		disposable = ApiRequestServiceProvider.apiRequestService()
				.searchMovies(UrlStorage.API_KEY, "en-US", searchQuery, false)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(response -> onLoaded(response.getResults()), this::onError);
	}

	private void onLoaded(List<MovieItem> movieItems) {
		view.hideLoading();
		view.update(movieItems);
		disposable.dispose();
	}

	private void onError(Throwable error) {
		view.hideLoading();
		view.reportError(error.getLocalizedMessage());
		disposable.dispose();
	}
}
