package com.pcloud.tinykoin

import android.util.Log

class PrintService(val packageRepository: MyRepository) {
    fun printTest() {
        Log.v("Print Service", "Test Message ${packageRepository.packageName}");
    }
}