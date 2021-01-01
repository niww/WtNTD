package com.example.wtntd.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.wtntd.R
import com.example.wtntd.ui.base.BaseActivity
import com.example.wtntd.ui.base.BaseViewModel
import com.example.wtntd.ui.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val layoutRes: Int
        get() = R.layout.activiti_splash
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun renderData(date: Boolean?) {
        date?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()

    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

}