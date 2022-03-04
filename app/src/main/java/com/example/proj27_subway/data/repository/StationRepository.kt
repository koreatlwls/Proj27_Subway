package com.example.proj27_subway.data.repository

import com.example.proj27_subway.domain.ArrivalInformation
import com.example.proj27_subway.domain.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    val stations: Flow<List<Station>>

    suspend fun refreshStations()

    suspend fun getStationArrivals(stationName: String): List<ArrivalInformation>

    suspend fun updateStation(station: Station)
}