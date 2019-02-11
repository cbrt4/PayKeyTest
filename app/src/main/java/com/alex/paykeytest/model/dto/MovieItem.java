package com.alex.paykeytest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieItem implements Serializable {

	@SerializedName("id")
	private long id;

	@SerializedName("popularity")
	private float popularity;

	@SerializedName("title")
	private String title;

	@SerializedName("poster_path")
	private String posterPath;

	public long getId() {
		return id;
	}

	public float getPopularity() {
		return popularity;
	}

	public String getTitle() {
		return title;
	}

	public String getPosterPath() {
		return posterPath;
	}
}
