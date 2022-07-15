package com.example.android.ubi_assignment.ext

import androidx.fragment.app.Fragment
import com.example.android.ubi_assignment.factory.ViewModelFactory
import com.example.android.ubi_assignment.util.CustomApplication

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as CustomApplication).airPollutionRepository
    return ViewModelFactory(repository)
}