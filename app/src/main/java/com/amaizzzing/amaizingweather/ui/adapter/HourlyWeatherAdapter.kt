package com.amaizzzing.amaizingweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.amaizingweather.databinding.HourlyWeatherItemBinding
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.amaizzzing.amaizingweather.mvp.presenter.list.IHourlyWeatherListPresenter
import com.amaizzzing.amaizingweather.mvp.view.list.HourlyWeatherItemView
import javax.inject.Inject

class HourlyWeatherAdapter(
    val presenter: IHourlyWeatherListPresenter
): RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(val vb: HourlyWeatherItemBinding): RecyclerView.ViewHolder(vb.root), HourlyWeatherItemView {
        override fun setTemp(temp: String) {
            vb.tempHourlyWeather.text = temp
        }

        override fun setTime(time: String) {
            vb.timeHourlyWeather.text = time
        }

        override fun setImage(image: Int) {
            imageLoader.loadInto(image, vb.imageHourlyWeather)
        }

        override fun setWind(wind: String) {
            vb.windHourlyWeather.text = wind
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HourlyWeatherItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()
}