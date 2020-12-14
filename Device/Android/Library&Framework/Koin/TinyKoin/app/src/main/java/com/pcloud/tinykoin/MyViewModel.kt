package com.pcloud.tinykoin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MyViewModel(val printService: PrintService) : ViewModel() {

    val TAG = this.javaClass.simpleName
    private val liveData:MutableLiveData<ExampleData> = MutableLiveData()

    val exampleData: LiveData<ExampleData>
        get() = liveData

    fun requestData() {
        val exampleData = ExampleData().apply {
            value = 10
        }

        liveData.value = exampleData
    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG, ">>> onCleared")
    }

    fun printTest() {
        printService.printTest()
    }
}