package com.example.android.ubi_assignment.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Field(
    val id: String,
    val info: Info,
    val type: String
) : Parcelable