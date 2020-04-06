package no.kristiania.foreignlands.ui.utils

interface ListClickListener {
    fun onNameClick(id: String)
    fun onIconClick(lat: Double, lon: Double)
}