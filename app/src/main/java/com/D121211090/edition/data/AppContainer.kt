package com.D121211090.edition.data

import com.D121211090.edition.data.repository.QuranEditionRepository
import com.D121211090.edition.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val quranEditionRepository: QuranEditionRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL = "https://api.alquran.cloud/"

    val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val quranEditionRepository: QuranEditionRepository
        get() = QuranEditionRepository(retrofitService)

}