package com.amaizzzing.amaizingweather.mvp.models.image

interface IImageLoader<T> {
    fun loadInto(source: Int, container: T)
}