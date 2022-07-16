package com.example.android.ubi_assignment.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Distribution(
    val qcLevel: String
) : Parcelable