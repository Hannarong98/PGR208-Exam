package no.kristiania.foreignlands.ui.details

import android.util.Log.i
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.repository.DetailsRepository


class DetailViewModel(private val repository: DetailsRepository,  private val placeId: String) : ViewModel() {

    // Encapsulated because its mutable
    private val placeDetailLiveData = MutableLiveData<PlaceDetail>()

    init {
        i("Detail VM", "fetching place detail from repository")
        viewModelScope.launch {
            val detail = repository.getRemotePlaceDetails(placeId)
            placeDetailLiveData.postValue(detail)
        }
    }

    val detail: LiveData<PlaceDetail>
    get() = placeDetailLiveData
}
