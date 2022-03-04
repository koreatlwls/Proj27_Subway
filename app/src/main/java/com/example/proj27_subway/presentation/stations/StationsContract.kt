package com.example.proj27_subway.presentation.stations

import com.example.proj27_subway.domain.Station
import com.example.proj27_subway.presentation.BasePresenter
import com.example.proj27_subway.presentation.BaseView
interface StationsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showStations(stations: List<Station>)
    }

    interface Presenter : BasePresenter {

        fun filterStations(query: String)

        fun toggleStationFavorite(station: Station)
    }
}