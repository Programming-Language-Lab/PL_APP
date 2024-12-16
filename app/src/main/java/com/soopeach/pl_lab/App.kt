package com.soopeach.pl_lab

import android.app.Application
import com.soopeach.pl_lab.di.dataSourceModules
import com.soopeach.pl_lab.di.repositoryModules
import com.soopeach.pl_lab.di.viewModelModules
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        startKoin {
            modules(
                listOf(
                    viewModelModules,
                    repositoryModules,
                    dataSourceModules
                )
            )
        }
        super.onCreate()
    }
}
