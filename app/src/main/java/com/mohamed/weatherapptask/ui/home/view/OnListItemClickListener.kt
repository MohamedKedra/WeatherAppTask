package com.mohamed.weatherapptask.ui.home.view

import android.view.View

interface OnListItemClickListener<T> {

    fun onItemClick(view: View, model: T)
}