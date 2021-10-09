package com.amaizzzing.amaizingweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaizzzing.amaizingweather.databinding.SearchCityItemBinding
import com.amaizzzing.amaizingweather.mvp.presenter.list.ISearchCityListPresenter
import com.amaizzzing.amaizingweather.mvp.view.list.SearchCityItemView

class SearchCityAdapter(val presenter: ISearchCityListPresenter): RecyclerView.Adapter<SearchCityAdapter.ViewHolder>() {
    inner class ViewHolder(val vb: SearchCityItemBinding): RecyclerView.ViewHolder(vb.root), SearchCityItemView {
        override fun setCity(cityName: String) {
            vb.cityNameSearchFragment.text = cityName
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(SearchCityItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )).apply {
                itemView.setOnClickListener {
                    presenter.itemClickListener?.invoke(this)
                }
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()
}