package com.factory.mobile.architecturedemoapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.factory.mobile.architecturedemoapp.extension.toObservable
import io.reactivex.rxkotlin.Observables
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class LoginViewModel : ViewModel(), AnkoLogger{
    //DataBinding Observable
    var login: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var loginAndPasswordNotEmpty = ObservableBoolean(false)

    init {
        //Using Rx to observe databinding variable
        Observables.combineLatest(login.toObservable(), password.toObservable()) { _, _ -> "" }
                .subscribe({
                    //If login and password fields are not empty: button is enabled
                    loginAndPasswordNotEmpty.set(login.get() != "" && password.get() != "")

                    //Log info using Anko
                    info("combineLatest onNext - $login $password")
                })
    }
}