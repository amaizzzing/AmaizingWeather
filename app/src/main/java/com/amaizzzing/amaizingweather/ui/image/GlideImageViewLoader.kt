package com.amaizzzing.amaizingweather.ui.image

import android.widget.ImageView
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.bumptech.glide.Glide

class GlideImageViewLoader(): IImageLoader<ImageView> {
    override fun loadInto(source: Int, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(source)
            .into(container)
    }
}