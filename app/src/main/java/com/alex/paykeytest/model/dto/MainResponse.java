package com.alex.paykeytest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MainResponse implements Serializable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_results")
	private int totalResults;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<MovieItem> results;


	public int getPage() {
		return page;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<MovieItem> getResults() {
		return results;
	}
}
