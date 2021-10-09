package com.amaizzzing.amaizingweather.di.modules

import android.view.ViewGroup
import android.widget.ImageView
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.amaizzzing.amaizingweather.ui.image.GlideBackgroundLoader
import com.amaizzzing.amaizingweather.ui.image.GlideImageViewLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> =
        GlideImageViewLoader()

    @Singleton
    @Provides
    fun backgroundImageLoader(): IImageLoader<ViewGroup> =
        GlideBackgroundLoader()
}