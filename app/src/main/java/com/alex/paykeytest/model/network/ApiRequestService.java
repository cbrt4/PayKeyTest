package com.alex.paykeytest.model.network;

import com.alex.paykeytest.model.dto.MovieDetails;
import com.alex.paykeytest.model.dto.MainResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequestService  {

	@GET(UrlStorage.MOVIES)
	Observable<MainResponse> getMovies(
			@Query(UrlStorage.KEY) String key,
			@Query(UrlStorage.LANG) String lang,
			@Query(UrlStorage.PAGE) int pageIndex
	);

	@GET(UrlStorage.SEARCH)
	Observable<MainResponse> searchMovies(
			@Query(UrlStorage.KEY) String key,
			@Query(UrlStorage.LANG) String lang,
			@Query(UrlStorage.QUERY) String queryString,
			@Query(UrlStorage.INCLUDE_ADULT) boolean includeAdult
	);

	@GET(UrlStorage.MOVIE_DETAILS)
	Observable<MovieDetails> getMovieDetails(
			@Path(UrlStorage.ID) String id,
			@Query(UrlStorage.KEY) String key,
			@Query(UrlStorage.LANG) String lang
	);
}
