package no.kristiania.foreignlands.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import no.kristiania.foreignlands.Coroutines
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.response.ApiResponse
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
