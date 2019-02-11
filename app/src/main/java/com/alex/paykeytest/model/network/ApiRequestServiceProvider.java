package com.alex.paykeytest.model.network;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alex.paykeytest.model.network.UrlStorage.BASE_URL;

public class ApiRequestServiceProvider {

	private static ApiRequestService apiRequestService;

	public static void init(Context context) {

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(1, TimeUnit.MINUTES)
				.readTimeout(1, TimeUnit.MINUTES)
				.writeTimeout(1, TimeUnit.MINUTES)
				.followRedirects(true)
				.cache(new Cache(new File(context.getCacheDir(), "responses"), 1024 * 1024 * 16))
				.build();

		apiRequestService = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(okHttpClient)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
						.setLenient()
						.create()))
				.build().create(ApiRequestService.class);
	}

	public static ApiRequestService apiRequestService() {
		return apiRequestService;
	}
}
