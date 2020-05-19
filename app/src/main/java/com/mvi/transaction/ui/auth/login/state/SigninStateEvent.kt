package com.mvi.transaction.ui.auth.login.state

/*
 * THIS IS THE FOURTH STEP AFTER CREATING THE VIEWMODEL
 */

sealed class SigninStateEvent {

    class SigninUserEvent(val email: String, val password: String): SigninStateEvent()

    class None: SigninStateEvent()

}