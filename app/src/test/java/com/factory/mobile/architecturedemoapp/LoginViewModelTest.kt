package com.factory.mobile.architecturedemoapp

import com.factory.mobile.architecturedemoapp.viewmodel.LoginViewModel
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by maxence bourdin on 14/04/2018.
 */
class LoginViewModelTest{
    @Test
    @Throws(Exception::class)
    fun loginViewModel_login_password_loginAndPassWordNotEmptyToTrue() {
        val loginViewModel = LoginViewModel()

        assertFalse(loginViewModel.loginAndPasswordNotEmpty.get())

        loginViewModel.login.set("smith")
        loginViewModel.password.set("john")

        assertTrue(loginViewModel.loginAndPasswordNotEmpty.get())
    }

    @Test
    @Throws(Exception::class)
    fun loginViewModel_login_passwordEmpty_loginAndPassWordNotEmptySetToFalse() {
        val loginViewModel = LoginViewModel()

        loginViewModel.login.set("smith")
        loginViewModel.password.set("")

        assertFalse(loginViewModel.loginAndPasswordNotEmpty.get())
    }

    @Test
    @Throws(Exception::class)
    fun loginViewModel_loginEmpty_password_loginAndPassWordNotEmptySetToFalse() {
        val loginViewModel = LoginViewModel()

        loginViewModel.login.set("")
        loginViewModel.password.set("john")

        assertFalse(loginViewModel.loginAndPasswordNotEmpty.get())
    }
}