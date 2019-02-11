package com.alex.paykeytest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_title")
	private String originalTitle;

	@SerializedName("overview")
	private String overview;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("production_countries")
	private List<ProductionCountry> productionCountries;

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public String getTagline() {
		return tagline;
	}

	public List<ProductionCountry> getProductionCountries() {
		return productionCountries;
	}
}
