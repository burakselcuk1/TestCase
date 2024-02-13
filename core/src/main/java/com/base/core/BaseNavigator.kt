package com.base.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.base.core.common.getNavOptions
import com.base.core.common.getNavigationResult
import com.base.core.common.setCurrentNavigationResult
import com.base.core.common.setNavigationResult

abstract class BaseNavigator : Navigation {
    lateinit var navController: NavController

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    private fun getBundleByNavArgs(args: NavArgs): Bundle {
        val toBundleMethod = args::class.java.getMethod("toBundle")
        return toBundleMethod.invoke(args) as Bundle
    }
    fun navigate(@IdRes destinationId: Int, args: NavArgs? = null, navOptions: NavOptions? = null) {
        navController.navigate(destinationId, args?.let { getBundleByNavArgs(it) }, navOptions ?: navController.getNavOptions().build())
    }

    fun <T> getNavigationResult(key: String = "result") = navController.getNavigationResult<T>(key)

    fun <T> setNavigationResult(result: T, key: String = "result") { navController.setNavigationResult<T>(result, key) }

    fun <T> setCurrentNavigationResult(result: T, key: String = "result") { navController.setCurrentNavigationResult(result, key) }

    fun <T> setNavigationResult(result: T, key: String = "result", destinationId: Int) {
        navController.setNavigationResult(result, key, destinationId)
    }

}

