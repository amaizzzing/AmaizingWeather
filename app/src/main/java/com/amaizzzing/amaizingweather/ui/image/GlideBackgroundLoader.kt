package com.amaizzzing.amaizingweather.ui.image

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class GlideBackgroundLoader: IImageLoader<ViewGroup> {
    override fun loadInto(source: Int, container: ViewGroup) {
        Glide.with(container.context)
            .load(source)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    container.background=resource
                }
            })
    }
}