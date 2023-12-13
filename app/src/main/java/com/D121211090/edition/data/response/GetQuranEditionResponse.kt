package com.D121211090.edition.data.response

import com.D121211090.edition.data.models.QuranEdition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetQuranEditionResponse(
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val data: List<QuranEdition>,
    @SerialName("status")
    val status: String?
)