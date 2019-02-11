package com.alex.paykeytest.view;

import com.alex.paykeytest.model.dto.MovieItem;

import java.util.List;

public interface MainView extends BaseView {
	void update(List<MovieItem> movieItems);
}
