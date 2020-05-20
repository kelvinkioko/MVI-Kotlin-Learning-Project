package com.mvi.transaction.ui.auth.login

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mvi.transaction.R
import com.mvi.transaction.ui.auth.login.state.SigninViewModel
import com.mvi.transaction.utility.DataState
import com.mvi.transaction.utility.DataStateListener
import com.mvi.transaction.widget.progress.SmoothProgressBar
import kotlinx.android.synthetic.main.dialog_loader.*
import java.util.*

class SigninActivity : AppCompatActivity(), DataStateListener {

    lateinit var signinViewModel: SigninViewModel

    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        //Initialize the viewModel in the activity class
        signinViewModel = ViewModelProvider(this).get(SigninViewModel::class.java)

        showSigninFragment()
        loaderDialog()
    }

    /*
     * This function is rather obvious, isn't it?
     * It starts and commits a fragment to the container
     */
    fun showSigninFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.signin_fragment_container, SigninActivityFragment(), "SigninFragment")
            .commit()
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            if (it.loading){
                dialog.show()
            }else{
                dialog.dismiss()
            }

            it.message?.let { message ->
                dialog.show()

                loaderPrimary.visibility = View.GONE
                loaderFail.visibility = View.VISIBLE
                loaderSuccess.visibility = View.GONE

                failMessage.text = message.getContentIfNotHandled()!!
                failDismiss.setOnClickListener{ dialog.dismiss(); loaderDialog() }
            }
        }
    }


    lateinit var loaderPrimary: LinearLayout
    lateinit var loaderFail: LinearLayout
    lateinit var loaderSuccess: LinearLayout

    lateinit var primaryProgress: SmoothProgressBar
    lateinit var primaryMessage: TextView

    lateinit var failMessage: TextView
    lateinit var failDismiss: TextView

    lateinit var successMessage: TextView
    lateinit var successDismiss: TextView

    private fun loaderDialog(){
        dialog = Dialog(this)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_loader)
        Objects.requireNonNull<Window>(dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loaderPrimary = dialog.loader_primary
        loaderFail = dialog.loader_fail
        loaderSuccess = dialog.loader_success

        primaryProgress = dialog.loader_primary_progress
        primaryMessage = dialog.loader_primary_message

        failMessage = dialog.loader_fail_message
        failDismiss = dialog.loader_fail_dismiss

        successMessage = dialog.loader_success_massage
        successDismiss = dialog.loader_success_dismiss

        loaderPrimary.visibility = View.VISIBLE
        loaderFail.visibility = View.GONE
        loaderSuccess.visibility = View.GONE

        primaryMessage.text = getString(R.string.sign_in_message)
    }


}