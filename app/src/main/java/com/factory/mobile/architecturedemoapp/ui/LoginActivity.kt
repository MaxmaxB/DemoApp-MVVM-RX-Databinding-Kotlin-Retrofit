package com.factory.mobile.architecturedemoapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.factory.mobile.architecturedemoapp.R
import com.factory.mobile.architecturedemoapp.databinding.ActivityLoginBinding
import com.factory.mobile.architecturedemoapp.viewmodel.LoginViewModel
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        initDataBinding()
        init()
    }

    private fun initDataBinding(){
        loginBinding.loginViewModel = loginViewModel
    }

    private fun init(){
        loginBinding.signInButton.onClick {
            //Show toast using Anko
            toast("Hello ${loginViewModel.login.get()}")

            //Start activity using Anko
            startActivity<NewsActivity>()
        }
    }
}
