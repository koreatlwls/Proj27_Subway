package com.example.proj27_subway.di

import android.app.Activity
import com.example.proj27_subway.BuildConfig
import com.example.proj27_subway.data.api.StationApi
import com.example.proj27_subway.data.api.StationArrivalsApi
import com.example.proj27_subway.data.api.StationStorageApi
import com.example.proj27_subway.data.api.Url
import com.example.proj27_subway.data.db.AppDatabase
import com.example.proj27_subway.data.preference.PreferenceManager
import com.example.proj27_subway.data.preference.SharedPreferenceManager
import com.example.proj27_subway.data.repository.StationRepository
import com.example.proj27_subway.data.repository.StationRepositoryImpl
import com.example.proj27_subway.presentation.stationarrivals.StationArrivalsContract
import com.example.proj27_subway.presentation.stationarrivals.StationArrivalsFragment
import com.example.proj27_subway.presentation.stationarrivals.StationArrivalsPresenter
import com.example.proj27_subway.presentation.stations.StationsContract
import com.example.proj27_subway.presentation.stations.StationsFragment
import com.example.proj27_subway.presentation.stations.StationsPresenter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().stationDao() }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<StationArrivalsApi> {
        Retrofit.Builder().baseUrl(Url.SEOUL_DATA_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }
    single<StationApi> { StationStorageApi(Firebase.storage) }

    // Repository
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get(), get()) }

    // Presentation
    scope<StationsFragment> {
        scoped<StationsContract.Presenter> { StationsPresenter(getSource(), get()) }
    }
    scope<StationArrivalsFragment> {
        scoped<StationArrivalsContract.Presenter> { StationArrivalsPresenter(getSource(), get(), get()) }
    }
}