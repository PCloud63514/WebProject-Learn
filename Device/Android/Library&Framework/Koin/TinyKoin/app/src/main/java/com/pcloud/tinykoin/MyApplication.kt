package com.pcloud.tinykoin

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.v("ApplicationTEST", "TEST");
        //Koin 시작
        startKoin {
            //Log
            androidLogger()

            // Android Context를 넘겨줍니다.
            androidContext(this@MyApplication)

            // assets / koin.properties 파일에서 Property를 가져옵니다.
            androidFileProperties()

            // 사용할 module을 등록합니다.
            modules(myModule, myViewModel)
        }
    }

    val myViewModel = module {
        viewModel {
            MyViewModel(get())
        }
    }

    val myModule = module {
        single {
            MyRepository(androidContext())
        }

        single {
            PrintService(get())
        }

        factory {
            InjectCount()
        }
    }
}