package com.example.android.ubi_assignment.ext

import android.app.Activity
import com.example.android.ubi_assignment.factory.ViewModelFactory
import com.example.android.ubi_assignment.util.CustomApplication

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (this.applicationContext as CustomApplication).airPollutionRepository
    return ViewModelFactory(repository)
}