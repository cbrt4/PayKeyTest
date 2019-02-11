package com.alex.paykeytest.view;

import com.alex.paykeytest.model.dto.MovieDetails;

public interface MovieView extends BaseView {
	void update(MovieDetails movieDetails);
}
