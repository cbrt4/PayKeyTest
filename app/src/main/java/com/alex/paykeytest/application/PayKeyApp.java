package com.alex.paykeytest.application;

import android.app.Application;

import com.alex.paykeytest.model.network.ApiRequestServiceProvider;

public class PayKeyApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		ApiRequestServiceProvider.init(this);
	}
}
