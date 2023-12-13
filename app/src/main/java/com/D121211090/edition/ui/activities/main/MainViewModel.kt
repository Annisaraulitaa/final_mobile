package com.D121211090.edition.ui.activities.main

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211090.edition.MyApplication
import com.D121211090.edition.data.models.QuranEdition
import com.D121211090.edition.data.repository.QuranEditionRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val data: List<QuranEdition>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(application: Application, private val quranEditionRepository: QuranEditionRepository) :
    AndroidViewModel(application) {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getSurah() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = quranEditionRepository.getQuranEdition()
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getSurah()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
                val quranEditionRepository = (application as MyApplication).container.quranEditionRepository
                MainViewModel(application, quranEditionRepository)
            }
        }
    }
}