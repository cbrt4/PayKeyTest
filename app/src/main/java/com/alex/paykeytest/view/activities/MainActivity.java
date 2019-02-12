package com.alex.paykeytest.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
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
	private ProgressBar mainProgressBar;
	private MainAdapter mainAdapter;

	private MainPresenter mainPresenter;

	private int currentPage = 1;
	private int itemsToLoad = 10;
	private boolean isSearching;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupViews();

		mainPresenter = new MainPresenter(this);
		mainPresenter.loadMovies(currentPage);
	}

	private void setupViews() {
		setupRecyclerView();
		setupSearchView();
		setupProgressBar();
	}

	private void setupRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
		mainAdapter = new MainAdapter(this);
		mainRecyclerView = findViewById(R.id.main_recycler_view);
		mainRecyclerView.setLayoutManager(layoutManager);
		mainRecyclerView.setAdapter(mainAdapter);
		mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);

				if (isSearching) {
					return;
				}

				int difference = mainAdapter.getItemCount() - itemsToLoad;
				int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

				if (lastVisibleItem > difference) {
					currentPage++;
					mainPresenter.loadMovies(currentPage);
				}
			}
		});
	}

	private void setupProgressBar() {
		mainProgressBar = findViewById(R.id.main_progress);
	}

	private void setupSearchView() {
		searchView = findViewById(R.id.main_search_view);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				isSearching = true;

				if (query.length() > 2) {
					search(query);
				}
				return false;
			}

			@Override
			public boolean onQueryTextChange(String query) {
				isSearching = true;

				if (query.length() > 2) {
					search(query);
				}
				return false;
			}
		});

		searchView.setOnCloseListener(() -> {
			isSearching = false;
			currentPage = 1;
			mainPresenter.loadMovies(currentPage);
			return false;
		});
	}

	private void search(String searchQuery) {
		mainPresenter.searchMovies(searchQuery);
	}

	@Override
	public void showLoading() {
		mainProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		mainProgressBar.setVisibility(View.GONE);
	}

	@Override
	public void update(List<MovieItem> element) {
		if (currentPage == 1 || isSearching) {
			mainAdapter.setMovieItems(element);
			mainAdapter.notifyDataSetChanged();
		} else {
			mainAdapter.getMovieItems().addAll(element);
			mainAdapter.notifyItemRangeInserted(mainAdapter.getMovieItems().size() - element.size(), element.size());
		}
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

		mainRecyclerView.clearOnScrollListeners();
		mainPresenter.dispose();
	}
}
