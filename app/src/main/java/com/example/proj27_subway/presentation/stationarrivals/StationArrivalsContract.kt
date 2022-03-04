package com.example.proj27_subway.presentation.stationarrivals

import com.example.proj27_subway.domain.ArrivalInformation
import com.example.proj27_subway.presentation.BasePresenter
import com.example.proj27_subway.presentation.BaseView

interface StationArrivalsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showStationArrivals(arrivalInformation: List<ArrivalInformation>)
    }

    interface Presenter : BasePresenter {

        fun fetchStationArrivals()

        fun toggleStationFavorite()
    }
}