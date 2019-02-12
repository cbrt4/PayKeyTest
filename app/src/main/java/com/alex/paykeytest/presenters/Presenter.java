package com.alex.paykeytest.presenters;

import com.alex.paykeytest.view.BaseView;

import io.reactivex.disposables.Disposable;

public abstract class Presenter<V extends BaseView> {

	V view;
	Disposable disposable;
	boolean isLoading;

	Presenter(V view) {
		this.view = view;
	}

	public void dispose() {
		if (disposable != null && !disposable.isDisposed()) {
			disposable.dispose();
		}
	}

	public boolean isLoading() {
		return isLoading;
	}
}
