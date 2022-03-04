package com.example.proj27_subway.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationEntity(
    @PrimaryKey val stationName: String,
    val isFavorited: Boolean = false
)