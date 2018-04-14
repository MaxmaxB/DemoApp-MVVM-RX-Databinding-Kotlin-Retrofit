package com.factory.mobile.architecturedemoapp.extension

import android.databinding.ObservableField
import io.reactivex.Observable

/**
 * Created by maxence bourdin on 14/04/2018.
 * ObservableField Extensions file
 */
//Converts an ObservableField to an Observable.
fun <T> ObservableField<T>.toObservable(): Observable<T> {
    return Observable.create { e ->
        val initialValue = this.get()
        e.onNext(initialValue!!)

        val callback = object : android.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: android.databinding.Observable, i: Int) {
                e.onNext(this@toObservable.get()!!)
            }
        }

        this.addOnPropertyChangedCallback(callback)
        e.setCancellable { this.removeOnPropertyChangedCallback(callback) }
    }
}