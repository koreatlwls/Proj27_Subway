package com.example.proj27_subway.data.api

import com.example.proj27_subway.data.db.entity.StationEntity
import com.example.proj27_subway.data.db.entity.SubwayEntity

interface StationApi {

    suspend fun getStationDataUpdatedTimeMillis(): Long

    suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>>
}