package no.kristiania.foreignlands.ui.overviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import no.kristiania.foreignlands.data.db.dao.PlacesDaoSQL
import no.kristiania.foreignlands.data.repository.OverviewRepository

@Suppress("UNCHECKED_CAST")
class OverviewViewModelFactory(private val repository : OverviewRepository, private val dao: PlacesDaoSQL): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OverviewViewModel(repository, dao) as T
    }
}