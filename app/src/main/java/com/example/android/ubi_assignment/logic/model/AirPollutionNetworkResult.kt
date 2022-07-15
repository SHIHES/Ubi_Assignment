package com.example.android.ubi_assignment.logic.model

data class AirPollutionNetworkResult(
    val __extras: Extras,
    val _links: Links,
    val distribution: List<Distribution>,
    val fields: List<Field>,
    val include_total: Boolean,
    val limit: Int,
    val offset: Int,
    val records: List<AirKPI>,
    val records_format: String,
    val resource_id: String,
    val sort: String,
    val total: Int
)