package com.ripple.repositories.activity.home

import android.os.Bundle
import com.ripple.repositories.base.BaseActivity
import com.ripple.repositories.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun initBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}