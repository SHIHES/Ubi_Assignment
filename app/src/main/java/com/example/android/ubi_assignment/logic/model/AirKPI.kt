package com.example.android.ubi_assignment.logic.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AirKPI(
    val AQI: String,
    val CO: String,
    val CO_8hr: String,
    val County: String,
    val ImportDate: String,
    val Latitude: String,
    val Longitude: String,
    val NO: String,
    val NO2: String,
    val NOx: String,
    val O3: String,
    val O3_8hr: String,
    val PM10: String,
    val PM10_AVG: String,
    @Json(name = "PM2.5")
    val PM2dot5: String,
    @Json(name = "PM2.5_AVG")
    val PM2dot5_AVG: String,
    val Pollutant: String,
    val PublishTime: String,
    val SO2: String,
    val SO2_AVG: String,
    val SiteId: String,
    val SiteName: String,
    val Status: String,
    val WIND_DIREC: String,
    val WIND_SPEED: String
) : Parcelable