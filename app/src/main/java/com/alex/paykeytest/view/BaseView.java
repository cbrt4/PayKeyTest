package com.alex.paykeytest.view;

public interface BaseView {

	void showLoading();

	void hideLoading();

	void reportError(String errorMessage);
}
