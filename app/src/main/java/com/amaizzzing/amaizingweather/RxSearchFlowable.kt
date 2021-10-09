package com.amaizzzing.amaizingweather

import androidx.appcompat.widget.SearchView
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object RxSearchFlowable {
    fun searchFromView(view: SearchView): Flowable<String> {
        return Flowable.create<String>({ emitter ->
            view.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        emitter.onNext(it)
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        emitter.onNext(it)
                    }

                    return false
                }
            })
        }, BackpressureStrategy.LATEST)
        .switchMap { Flowable.just(it.toLowerCase().trim()) }
        .debounce(300, TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .filter { text -> text.isNotBlank() && text.length >= 3 }
        .subscribeOn(Schedulers.io())
    }
}