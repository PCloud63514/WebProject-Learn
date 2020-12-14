package com.pcloud.tinykoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val TAG: String = this.javaClass.simpleName

    val myViewModel: MyViewModel by viewModel()

    val inject0 by inject<InjectCount>()
    val inject1 by inject<InjectCount>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MainActivityTEST", "TEST");
        myViewModel.exampleData.observe(this, Observer {
            Log.v(TAG, ">>> ${it.value}")
        })

        myViewModel.requestData()
        myViewModel.printTest()

        inject0.printCount()
        inject1.printCount()
    }
}