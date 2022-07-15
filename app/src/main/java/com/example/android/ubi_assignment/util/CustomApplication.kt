package com.example.android.ubi_assignment.util

import android.app.Application
import com.example.android.ubi_assignment.logic.network.DataSource
import kotlin.properties.Delegates

class CustomApplication : Application() {
    
    val airPollutionRepository: DataSource
        get() = ServiceLocator().provideTasksRepository()
    
    companion object {
        var instance: CustomApplication by Delegates.notNull()
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}