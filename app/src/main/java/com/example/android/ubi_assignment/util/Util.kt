package com.example.android.ubi_assignment.util

object Util {
    
    fun getString(resourceId: Int): String {
        return CustomApplication.instance.getString(resourceId)
    }
    
}