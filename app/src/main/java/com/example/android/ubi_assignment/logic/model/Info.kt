package com.example.android.ubi_assignment.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val label: String,
    val notes: String
) : Parcelable