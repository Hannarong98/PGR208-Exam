package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import no.kristiania.foreignlands.data.repository.DetailsRepository

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val repository : DetailsRepository,
    private val placeId: String
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            repository,
            placeId
        ) as T
    }
}