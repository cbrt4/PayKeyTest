package com.alex.paykeytest.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class Glide extends AppGlideModule {

	private final int IMAGE_CACHE_SIZE = 16 * 1024 * 1024;
	private final String IMAGE_FOLDER = "/images";

	@Override
	public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
		builder.setDefaultRequestOptions(new RequestOptions()
				.format(DecodeFormat.PREFER_ARGB_8888)
				.diskCacheStrategy(DiskCacheStrategy.ALL));
		InternalCacheDiskCacheFactory factory = new InternalCacheDiskCacheFactory(context, IMAGE_FOLDER, IMAGE_CACHE_SIZE);
		builder.setDiskCache(factory);
	}

	@Override
	public boolean isManifestParsingEnabled() {
		return false;
	}

}
