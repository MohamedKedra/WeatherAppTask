package com.mohamed.weatherapptask.app.base

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<StateData<T>>() {

    fun postLoading(){
        postValue(StateData<T>().getLoading())
    }

    fun postSuccess(data : T){
        postValue(StateData<T>().getSuccess(data))
    }

    fun postError(t : Throwable) {
        postValue(StateData<T>().getError(t))
    }

    fun postNotComplete(){
        postValue(StateData<T>().getNotCompleted())
    }

    fun postNoInternet() {
        postValue(StateData<T>().getNoInternet())
    }
}