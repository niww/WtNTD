package com.example.wtntd.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.wtntd.R
import com.example.wtntd.error.NoAuthException
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 123
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, Observer<S> { t ->
            t?.apply {
                data?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        })

    }

    protected fun renderError(e: Throwable) {
        when (e) {
            is NoAuthException -> startLoginActivity()
            else -> e.message?.let { showError(it) }
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.Theme_AppCompat_DayNight)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build()
                    )
                )
                .build(),
            RC_SIGN_IN
        )
    }

    private fun showError(it: String) {
    }

    abstract fun renderData(date: T)

    //fixme not open new intent
//    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
//        if (requestCode == RC_SIGN_IN && requestCode !=Activity.RESULT_OK){
//            finish() //fixme
//        }
//    }

}