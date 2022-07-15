package com.example.android.ubi_assignment.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.ubi_assignment.logic.network.DataSource
import com.example.android.ubi_assignment.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: DataSource
) : ViewModelProvider.NewInstanceFactory(){
    
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repository)
                
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
            
        } as T
}