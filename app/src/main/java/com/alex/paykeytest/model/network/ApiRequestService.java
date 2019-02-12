package com.alex.paykeytest.model.network;

import com.alex.paykeytest.model.dto.MainResponse;
import com.alex.paykeytest.model.dto.MovieDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequestService {

	@GET(UrlStorage.MOVIES)
	Observable<MainResponse> getMovies(
			@Query(UrlStorage.KEY_API_KEY) String apiKey,
			@Query(UrlStorage.KEY_LANGUAGE) String language,
			@Query(UrlStorage.KEY_PAGE) int pageIndex
	);

	@GET(UrlStorage.SEARCH)
	Observable<MainResponse> searchMovies(
			@Query(UrlStorage.KEY_API_KEY) String apiKey,
			@Query(UrlStorage.KEY_LANGUAGE) String language,
			@Query(UrlStorage.KEY_QUERY) String queryString,
			@Query(UrlStorage.KEY_INCLUDE_ADULT) boolean includeAdult
	);

	@GET(UrlStorage.MOVIE_DETAILS)
	Observable<MovieDetails> getMovieDetails(
			@Path(UrlStorage.ID) String id,
			@Query(UrlStorage.KEY_API_KEY) String apiKey,
			@Query(UrlStorage.KEY_LANGUAGE) String language
	);
}
