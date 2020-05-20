package com.mvi.transaction.ui.auth.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvi.transaction.R
import com.mvi.transaction.ui.auth.login.state.SigninStateEvent
import com.mvi.transaction.ui.auth.login.state.SigninStateEvent.*
import com.mvi.transaction.ui.auth.login.state.SigninViewModel
import kotlinx.android.synthetic.main.activity_signin_fragment.*
import java.lang.Exception

class SigninActivityFragment : Fragment(){

    lateinit var signinViewModel: SigninViewModel

    //To avoid null pointer and init failures we use a root initialization
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.activity_signin_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        signinViewModel = activity?.run { ViewModelProvider(this).get(SigninViewModel::class.java) }?: throw Exception ("Invalid Activity")

        subscribeObserver()

        sign_in_started.setOnClickListener {
            when {
                sign_in_email.text.toString().isNullOrEmpty() -> { sign_in_email.error = "Please enter a valid email address" }
                sign_in_password.text.toString().isNullOrEmpty() -> { sign_in_password.error = "Please enter a valid password" }
                else -> { triggerSigninEvent(sign_in_email.text.toString(), sign_in_password.text.toString()) }
            }
        }
    }

    private fun subscribeObserver(){
        signinViewModel.signinDataState.observe(viewLifecycleOwner, Observer { signinDataState ->
            println("Debug: Signin Datastate: $signinDataState")
            signinDataState.data?.let { event -> event.getContentIfNotHandled()?.let { signinViewState ->
                signinViewModel.setSuccessLogin(signinViewState.userEntity!!)
            }}
        })

        signinViewModel.signinViewState.observe(viewLifecycleOwner, Observer { signinViewState ->
             signinViewState.userEntity?.let {
                 println("DEBUG: Setting user details: $it")
             }
        })
    }

    private fun triggerSigninEvent(email_address: String, password: String){
        signinViewModel.setSigninStateEvent(SigninUserEvent(email_address, password))
    }

}