package com.alex.paykeytest.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductionCountry implements Serializable {

	@SerializedName("name")
	private String name;

	public String getName() {
		return name;
	}
}
