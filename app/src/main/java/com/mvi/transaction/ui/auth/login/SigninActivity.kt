package com.mvi.transaction.ui.auth.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mvi.transaction.R
import com.mvi.transaction.ui.auth.login.state.SigninViewModel

class SigninActivity : AppCompatActivity() {

    lateinit var signinViewModel: SigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        //Initialize the viewModel in the activity class
        signinViewModel = ViewModelProvider(this).get(SigninViewModel::class.java)

        showSigninFragment()
    }

    fun showSigninFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.signin_fragment_container, SigninActivityFragment(), "SigninFragment")
            .commit()
    }
}