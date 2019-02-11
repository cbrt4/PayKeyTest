package com.alex.paykeytest.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.alex.paykeytest.R;
import com.alex.paykeytest.listeners.OnItemClickListener;
import com.alex.paykeytest.model.dto.MovieItem;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.presenters.MainPresenter;
import com.alex.paykeytest.view.MainView;
import com.alex.paykeytest.view.adapters.MainAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {

	private SearchView searchView;
	private RecyclerView mainRecyclerView;
	private MainAdapter mainAdapter;

	private MainPresenter mainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupViews();

		mainPresenter = new MainPresenter(this);
		mainPresenter.loadMovies();

	}

	private void setupViews() {
		setupRecyclerView();
		setupSearchView();
	}

	private void setupRecyclerView() {
		mainAdapter = new MainAdapter(this);

		mainRecyclerView = findViewById(R.id.main_recycler_view);
		mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
		mainRecyclerView.setAdapter(mainAdapter);
	}

	private void setupSearchView() {
		searchView = findViewById(R.id.main_search_view);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				if (query.length() > 2) {
					search(query);
				}
				return false;
			}

			@Override
			public boolean onQueryTextChange(String query) {
				if (query.length() > 2) {
					search(query);
				}
				return false;
			}
		});

		searchView.setOnCloseListener(() -> {
			mainPresenter.loadMovies();
			return false;
		});
	}

	private void search(String searchQuery) {
		mainPresenter.searchMovies(searchQuery);
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void update(List<MovieItem> element) {
		mainAdapter.setMovieItems(element);
		mainAdapter.notifyDataSetChanged();
	}

	@Override
	public void reportError(String errorMessage) {
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(int position) {
		startActivity(new Intent(this, MovieActivity.class)
				.putExtra(UrlStorage.ID, String.valueOf(mainAdapter.getMovieItems().get(position).getId())));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mainPresenter.cancel();
	}
}
