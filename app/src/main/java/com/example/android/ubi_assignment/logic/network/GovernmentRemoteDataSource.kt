package com.example.android.ubi_assignment.logic.network

import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.logic.model.Result


class GovernmentRemoteDataSource : DataSource {
    override suspend fun getAirPollutionData(): Result<AirPollutionNetworkResult> {
        
        return try {
            val callback = GovernmentApi.retrofitService.getAreaAirPollution()
            
            Result.Success(callback)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}