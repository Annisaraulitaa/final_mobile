package com.D121211090.edition.data.source.remote

import com.D121211090.edition.data.response.GetQuranEditionResponse
import retrofit2.http.GET


interface ApiService {
    @GET("v1/edition")
    suspend fun getQuranEdition(): GetQuranEditionResponse
}