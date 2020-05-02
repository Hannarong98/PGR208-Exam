package no.kristiania.foreignlands.ui.details

import android.util.Log.i
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.repository.DetailsRepository


class DetailViewModel(private val repository: DetailsRepository, private val placeId: String) :
    ViewModel() {

    val detail = liveData {
        i("Detail VM", "fetching place detail from repository")
        emit(repository.getRemotePlaceDetails(placeId))
    }

}
