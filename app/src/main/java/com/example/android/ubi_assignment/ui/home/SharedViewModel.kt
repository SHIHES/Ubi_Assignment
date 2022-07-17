package com.example.android.ubi_assignment.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.ubi_assignment.R
import com.example.android.ubi_assignment.logic.model.AirKPI
import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.logic.model.LoadApiStatus
import com.example.android.ubi_assignment.logic.network.DataSource
import com.example.android.ubi_assignment.logic.model.Result
import com.example.android.ubi_assignment.util.Logger
import com.example.android.ubi_assignment.util.Util
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SharedViewModel(
    private val repository: DataSource
) : ViewModel() {
    
    private val coroutineScope = viewModelScope
    
    private val _status = MutableLiveData<LoadApiStatus>()
    
    val status: LiveData<LoadApiStatus>
        get() = _status
    
    private val _error = MutableLiveData<String?>()
    
    val error: LiveData<String?>
        get() = _error
    
    private val _airResult = MutableLiveData<AirPollutionNetworkResult>()
    
    val airResult: LiveData<AirPollutionNetworkResult>
        get() = _airResult
    
    private val _airResultBad = MutableLiveData<List<AirKPI>>()
    
    val airResultBad: LiveData<List<AirKPI>>
        get() = _airResultBad
    
    private val _airResultGood = MutableLiveData<List<AirKPI>>()
    
    val airResultGood: LiveData<List<AirKPI>>
        get() = _airResultGood
    
    private val _searchText = MutableLiveData<String>()
    
    val searchText: LiveData<String>
        get() = _searchText
    
    private val _searchData = MutableLiveData<List<AirKPI>>()
    
    val searchData: LiveData<List<AirKPI>>
        get() = _searchData
    
    private val _searchStage = MutableLiveData(false)
    
    val searchStage: LiveData<Boolean>
        get() = _searchStage
    
    init {
        getAirPollutionData()
    }
    
    private fun getAirPollutionData(){
        coroutineScope.launch {
            val result = repository.getAirPollutionData()
    
            _airResult.value = when (result) {
                is Result.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE
                    result.data
                }
                is Result.Fail -> {
                    _error.value = result.error
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = Util.getString(R.string.unexpected_error)
                    _status.value = LoadApiStatus.ERROR
                    null
                }
        
            }
            filterAirResult()
        }
    }
    
    
    private fun filterAirResult(){
        
        val goodAirResult = mutableListOf<AirKPI>()
        val badAirResult = mutableListOf<AirKPI>()
        
        for ( result in _airResult.value!!.records){
            try {
                if (result.PM2dot5.toInt() > 30) {
                    goodAirResult.add(result)
                } else {
                    badAirResult.add(result)
                }
            } catch (e: Exception) {
                Log.d("ss", "e ${e}")
                continue
            }

        }
        _airResultGood.value = goodAirResult
        _airResultBad.value = badAirResult
        
    }
    
    fun getSearchText(text: String) {
        _searchText.value = text
        filterSearchData(text)
        Logger.d("getSearchText ${_searchText.value}")
    }
    
    fun filterSearchData(text: String) {
        _searchData.value = _airResultBad.value?.filter {
            it.County.contains(text) || it.SiteName.contains(text)
        }
    }
    
    fun getSearchStatus(status: Boolean){
        _searchStage.value = status
    }
    
    
    
    
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}