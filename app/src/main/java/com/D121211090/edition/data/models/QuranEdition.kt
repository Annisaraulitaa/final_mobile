package com.D121211090.edition.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class QuranEdition(
    val direction: String?,
    val englishName: String?,
    val format: String?,
    val identifier: String?,
    val language: String?,
    val name: String?,
    val type: String?
) : Parcelable