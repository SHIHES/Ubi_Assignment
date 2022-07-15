package com.example.android.ubi_assignment.util

import androidx.annotation.VisibleForTesting
import com.example.android.ubi_assignment.logic.network.DataSource
import com.example.android.ubi_assignment.logic.network.DefaultRepository
import com.example.android.ubi_assignment.logic.network.GovernmentRemoteDataSource

class ServiceLocator {
    
    @Volatile
    var repository: DataSource? = null
        @VisibleForTesting set
    
    fun provideTasksRepository(): DataSource {
        synchronized(this) {
            return repository
                ?: createRepository()
        }
    }
    
    private fun createRepository(): DataSource {
        return DefaultRepository(
            GovernmentRemoteDataSource()
        )
    }
}