package com.pcloud.tinykoin

import android.util.Log

class InjectCount {
    companion object {
        var count = 0
    }

    init {
        count++
    }

    fun printCount() {
        Log.v("Count", "Count: ${count}")
    }
}