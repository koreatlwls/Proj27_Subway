package com.example.proj27_subway.data.db.entity.mapper

import com.example.proj27_subway.data.db.entity.StationEntity
import com.example.proj27_subway.data.db.entity.StationWithSubwaysEntity
import com.example.proj27_subway.data.db.entity.SubwayEntity
import com.example.proj27_subway.domain.Station
import com.example.proj27_subway.domain.Subway

fun StationWithSubwaysEntity.toStation() =
    Station(
    name = station.stationName,
    isFavorited = station.isFavorited,
    connectedSubways = subways.toSubways()
)

fun Station.toStationEntity() =
    StationEntity(
        stationName = name,
        isFavorited = isFavorited,
    )

fun List<StationWithSubwaysEntity>.toStations() = map { it.toStation() }

fun List<SubwayEntity>.toSubways(): List<Subway> = map { Subway.findById(it.subwayId) }