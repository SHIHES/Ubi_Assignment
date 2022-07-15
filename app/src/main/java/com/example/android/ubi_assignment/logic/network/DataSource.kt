package com.example.android.ubi_assignment.logic.network

import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.logic.model.Result

interface DataSource {
    
    suspend fun getAirPollutionData(): Result<AirPollutionNetworkResult>
    
}