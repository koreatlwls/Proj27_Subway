package com.example.proj27_subway.data.preference

interface PreferenceManager {
    fun getLong(key: String): Long?

    fun putLong(key:String, value: Long)
}