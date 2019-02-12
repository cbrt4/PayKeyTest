package com.alex.paykeytest.presenters;

import com.alex.paykeytest.model.dto.MainResponse;
import com.alex.paykeytest.model.network.ApiRequestServiceProvider;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.view.MainView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends Presenter<MainView> {

	public MainPresenter(MainView view) {
		super(view);
	}

	public void loadMovies(int pageIndex) {
		if (!isLoading) {
			view.showLoading();
			isLoading = true;
			disposable = ApiRequestServiceProvider.apiRequestService()
					.getMovies(UrlStorage.API_KEY, UrlStorage.LANGUAGE, pageIndex)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onLoaded, this::onError);
		}
	}

	public void searchMovies(String searchQuery) {
		if (isLoading) {
			dispose();
		}

		view.showLoading();
		isLoading = true;
		disposable = ApiRequestServiceProvider.apiRequestService()
				.searchMovies(UrlStorage.API_KEY, UrlStorage.LANGUAGE, searchQuery, false)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::onLoaded, this::onError);

	}

	private void onLoaded(MainResponse response) {
		isLoading = false;
		view.hideLoading();
		view.update(response.getResults());
		dispose();
	}

	private void onError(Throwable error) {
		isLoading = false;
		view.hideLoading();
		view.reportError(error.getLocalizedMessage());
		dispose();
	}
}
