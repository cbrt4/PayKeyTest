package com.alex.paykeytest.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.paykeytest.R;
import com.alex.paykeytest.listeners.OnItemClickListener;
import com.alex.paykeytest.model.dto.MovieItem;
import com.alex.paykeytest.model.network.UrlStorage;
import com.alex.paykeytest.util.GlideApp;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

	private List<MovieItem> movieItems;
	private OnItemClickListener onItemClickListener;

	public MainAdapter(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public List<MovieItem> getMovieItems() {
		return movieItems;
	}

	public void setMovieItems(List<MovieItem> movieItems) {
		this.movieItems = movieItems;
	}

	@NonNull
	@Override
	public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
		return new MainViewHolder(LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.item_main_recycler, viewGroup, false));
	}

	@Override
	public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
		mainViewHolder.bindData(movieItems.get(mainViewHolder.getAdapterPosition()));
	}

	@Override
	public int getItemCount() {
		return movieItems == null ? 0 : movieItems.size();
	}

	class MainViewHolder extends RecyclerView.ViewHolder {

		ImageView poster;
		TextView title;
		TextView popularity;

		MainViewHolder(@NonNull View itemView) {
			super(itemView);

			poster = itemView.findViewById(R.id.movie_poster);
			title = itemView.findViewById(R.id.movie_title);
			popularity = itemView.findViewById(R.id.movie_popularity);
		}

		void bindData(MovieItem movieItem) {
			title.setText(movieItem.getTitle());
			popularity.setText(String.valueOf(movieItem.getPopularity()));
			GlideApp.with(itemView.getContext())
					.load(UrlStorage.IMAGE_URL + movieItem.getPosterPath())
					.centerCrop()
					.transition(DrawableTransitionOptions.withCrossFade(100))
					.into(poster);

			itemView.setOnClickListener(view -> onItemClickListener.onItemClick(getAdapterPosition()));
		}
	}
}
