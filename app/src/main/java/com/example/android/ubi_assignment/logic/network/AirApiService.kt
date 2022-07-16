package com.example.android.ubi_assignment.logic.network

import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://data.epa.gov.tw/api/v1/"

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface AirApiService {
    
    @GET("aqx_p_432")
    suspend fun getAreaAirPollution(
        @Query("limit") limit: String = "1000",
        @Query("api_key") apiKey: String = "9be7b239-557b-4c10-9775-78cadfc555e9",
        @Query("sort") sort: String = "ImportDate desc",
        @Query("format") format: String = "json"
    ) : AirPollutionNetworkResult
}

object GovernmentApi {
    val retrofitService: AirApiService by lazy { retrofit.create(AirApiService::class.java) }
}