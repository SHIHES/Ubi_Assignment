package com.example.android.ubi_assignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.ubi_assignment.R
import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.logic.model.LoadApiStatus
import com.example.android.ubi_assignment.logic.network.DataSource
import com.example.android.ubi_assignment.logic.model.Result
import com.example.android.ubi_assignment.util.Util
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel(
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
        }
    }
    
    
    
    
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}