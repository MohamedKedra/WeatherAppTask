package com.mohamed.weatherapptask.app.base


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.layout_error_loading.*

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int
    protected lateinit var navigationController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationController = view.findNavController()

    }

    fun showLayoutLoading() {
        pb_progressbar.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }

    fun showLayoutError(error: String) {
        pb_progressbar.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = error
    }

    fun hideLayoutErrorAndLoading() {
        pb_progressbar.visibility = View.GONE
        tv_error.visibility = View.GONE
    }

    fun showMessage(view:View,message : String){
        val snack = Snackbar.make(view,message,Snackbar.LENGTH_SHORT)
        snack.show()
    }

}
