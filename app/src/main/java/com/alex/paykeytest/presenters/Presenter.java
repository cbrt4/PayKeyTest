package com.alex.paykeytest.presenters;

import com.alex.paykeytest.view.BaseView;

import io.reactivex.disposables.Disposable;

public abstract class Presenter<V extends BaseView> {

	V view;
	Disposable disposable;

	Presenter(V view) {
		this.view = view;
	}

	public void cancel() {
		if (disposable != null && !disposable.isDisposed()) {
			disposable.dispose();
		}
	}
}
