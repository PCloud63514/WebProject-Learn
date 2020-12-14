package com.pcloud.tinykoin

import android.content.Context

interface Repository

class MyRepository(context: Context):Repository {
    val packageName:String
    init {
        packageName = context.packageName
    }
}