package no.kristiania.foreignlands.ui.overviews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.model.overviews.Places
import kotlin.coroutines.CoroutineContext

// The code was based on this article: https://android.jlelse.eu/android-networking-in-2019-retrofit-with-kotlins-coroutines-aefe82c4d777
// With some minor adjustment


class OverviewViewModel(private val repository: OverviewRepository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)



    val placesLiveData = MutableLiveData<MutableList<Places>>()

    fun fetchPlaces(){
        scope.launch {
            val places = repository.getPlaces()
            placesLiveData.postValue(places)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()
}
