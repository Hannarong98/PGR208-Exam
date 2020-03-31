package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.model.details.Place
import no.kristiania.foreignlands.data.repository.DetailsRepository
import kotlin.coroutines.CoroutineContext


class DetailViewModel(private val repository: DetailsRepository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    val placeDetailLiveData = MutableLiveData<Place>()

    fun fetchDetails() {
        scope.launch {
            val detail = repository.getPlaceDetails()
            placeDetailLiveData.postValue(detail)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()
}
