package com.example.foreignlands.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foreignlands.Coroutines
import com.example.foreignlands.data.repository.OverviewRepository
import com.example.foreignlands.data.response.ApiResponse
import kotlinx.coroutines.Job

class OverviewViewModel(
    private val repository: OverviewRepository
) : ViewModel() {
    private lateinit var job: Job

    private val _overviews = MutableLiveData<ApiResponse>()
    val overviews: LiveData<ApiResponse>
        get() = _overviews

    fun getOverviews() {
        job = Coroutines.ioToMain(
            { repository.getPlaces() },
            {
                _overviews.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
