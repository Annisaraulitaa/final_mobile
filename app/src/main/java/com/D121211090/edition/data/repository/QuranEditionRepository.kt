package com.D121211090.edition.data.repository

import com.D121211090.edition.data.response.GetQuranEditionResponse
import com.D121211090.edition.data.source.remote.ApiService

class QuranEditionRepository(
    private val apiService: ApiService
) {
    suspend fun getQuranEdition(): GetQuranEditionResponse {
        return apiService.getQuranEdition()
    }
}
