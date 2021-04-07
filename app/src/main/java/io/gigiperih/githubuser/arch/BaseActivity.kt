package io.gigiperih.githubuser.arch

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity(), IBaseActivity {
    lateinit var dataBinding: D

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutRes())
        initView(savedInstanceState)
    }
}

interface IBaseActivity {
    @LayoutRes
    fun layoutRes(): Int
    fun initView(savedInstanceState: Bundle?)
}