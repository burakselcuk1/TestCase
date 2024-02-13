package com.base.core

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : AppCompatActivity() {

    internal val disposable: CompositeDisposable by lazy { CompositeDisposable() }


    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }
    val viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    abstract fun onInitDataBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        onInitDataBinding()

        if (!isInternetConnected()) {
            showNoInternetDialog()
        }
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun showNoInternetDialog() {
        val title = getString(R.string.no_internet_title)
        val message = getString(R.string.no_internet_message)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
                    //  finish()
        }
        builder.setCancelable(false)
        builder.show()
    }



    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}