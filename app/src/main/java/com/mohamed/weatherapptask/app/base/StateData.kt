package com.mohamed.weatherapptask.app.base

class StateData<T> {

    private var dataStatus : DataStatus = DataStatus.LOADING

    private var data : T? = null

    private var error : Throwable? = null

    fun getLoading() : StateData<T> {

        this.dataStatus = DataStatus.LOADING
        this.data = null
        this.error = null
        return this
    }

    fun getSuccess(data : T) : StateData<T> {

        this.dataStatus = DataStatus.SUCCESS
        this.data = data
        this.error = null
        return this
    }

    fun getError(throwable: Throwable) : StateData<T> {

        this.dataStatus = DataStatus.ERROR
        this.data = null
        this.error = throwable
        return this
    }

    fun getNotCompleted() : StateData<T> {

        this.dataStatus = DataStatus.NOT_COMPLETED
        return this
    }

    fun getNoInternet(): StateData<T> {

        this.dataStatus = DataStatus.NO_INTERNET
        return this
    }

    fun getStatus() : DataStatus = this.dataStatus

    fun getData() : T? = this.data

    fun getError() : Throwable? = this.error

    enum class DataStatus{
        LOADING,
        SUCCESS,
        ERROR,
        NOT_COMPLETED,
        NO_INTERNET,
    }
}