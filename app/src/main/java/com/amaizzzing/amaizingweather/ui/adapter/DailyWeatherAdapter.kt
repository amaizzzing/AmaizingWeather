package com.amaizzzing.amaizingweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.amaizingweather.databinding.DailyWeatherItemBinding
import com.amaizzzing.amaizingweather.mvp.models.image.IImageLoader
import com.amaizzzing.amaizingweather.mvp.presenter.list.IDailyWeatherListPresenter
import com.amaizzzing.amaizingweather.mvp.view.list.DailyWeatherItemView
import javax.inject.Inject

class DailyWeatherAdapter(val presenter: IDailyWeatherListPresenter): RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(val vb: DailyWeatherItemBinding): RecyclerView.ViewHolder(vb.root),
        DailyWeatherItemView {
        override fun setDate(text: String) {
            vb.dateDailyWeatherItem.text = text
        }

        override fun setTemp(temp: String) {
            vb.tempDailyWeatherItem.text = temp
        }

        override fun setBackground(color: Int) {
            vb.cvDailyWeatherItem.setCardBackgroundColor(color)
        }

        override fun setHourlyImage(image: Int) {
            imageLoader.loadInto(
                image,
                vb.imageHourlyForecastDailyWeatherItem
            )
        }

        override fun setWeatherImage(weatherImage: Int) {
            imageLoader.loadInto(
                weatherImage,
                vb.imageDailyWeatherItem
            )
        }

        override fun setHourlyImageGone(isGone: Int) {
            vb.imageHourlyForecastDailyWeatherItem.visibility = isGone
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DailyWeatherItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
            vb.imageHourlyForecastDailyWeatherItem.setOnClickListener {
                presenter.hourlyClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int = presenter.getCount()

}