package com.example.android.ubi_assignment.logic.network

import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.logic.model.Result

class DefaultRepository(
    private val remoteDataSource: DataSource,
) : DataSource {
    
    override suspend fun getAirPollutionData(): Result<AirPollutionNetworkResult> {
        return remoteDataSource.getAirPollutionData()
    }
}