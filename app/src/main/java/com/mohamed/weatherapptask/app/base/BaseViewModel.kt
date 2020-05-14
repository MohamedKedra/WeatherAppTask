package com.mohamed.articaledemoapp.ui.main.ui.bases

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseViewModel(protected val connectivityManager: ConnectivityManager) : ViewModel() {

    var requestInProgress = false
        private set

    protected val isNetworkAvailable: Boolean
        get() {
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return if (activeNetInfo != null) {
                activeNetInfo.isAvailable && activeNetInfo.isConnected
            } else {
                false
            }
        }

    fun <T> performApiCall(liveData: StateLiveData<T>, call: Call<T>) {
        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    publishResult(liveData, it)
                } ?: run {
                    publishNotCompleted(liveData)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                publishError(liveData, t)
            }
        })
    }

    fun <T> publishLoading(liveData: StateLiveData<T>) {
        requestInProgress = true
        liveData.postLoading()
    }

    fun <T> publishNoInternet(liveData: StateLiveData<T>) {
        requestInProgress = false
        liveData.postNoInternet()
    }

    fun <T> publishNotCompleted(liveData: StateLiveData<T>) {
        requestInProgress = false
        liveData.postNotComplete()
    }

    fun <T> publishError(liveData: StateLiveData<T>, t: Throwable) {
        requestInProgress = false
        liveData.postError(t)
    }

    fun <T> publishResult(liveData: StateLiveData<T>, data: T) {
        requestInProgress = false
        liveData.postSuccess(data)
    }
}