package com.alex.paykeytest.model.network;

public class UrlStorage {

	public static final String API_KEY = "9590a764b45c358fd086b860c7f112a5";
	public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
	public static final String LANGUAGE = "en-US";
	public static final String ID = "id";

	static final String BASE_URL = "https://api.themoviedb.org/3/";
	static final String MOVIES = "movie/popular";
	static final String SEARCH = "search/movie";
	static final String MOVIE_DETAILS = "movie/{"+ ID +"}";

	static final String KEY_API_KEY = "api_key";
	static final String KEY_LANGUAGE = "language";
	static final String KEY_PAGE = "page";
	static final String KEY_QUERY = "query";
	static final String KEY_INCLUDE_ADULT = "include_adult";
}
