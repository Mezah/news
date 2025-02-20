package com.task.news.listing.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.news.listing.domain.model.HeadLine
import com.task.news.listing.domain.usecases.HeadLineUseCase
import com.task.news.listing.presentation.state.ViewIntent
import com.task.news.listing.presentation.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TopHeadLineViewModel @Inject constructor(
    private val topHeadLineUseCase: HeadLineUseCase,
) : ViewModel() {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState

    private fun topHeadLines(page: Int, query: String) =
        topHeadLineUseCase.topHeadLines(page, query)
            .map { result: Result<List<HeadLine>> ->
                if (result.isSuccess) {
                    val data = result.getOrDefault(emptyList())
                    _viewState.update {
                        it.copy(isLoading = false, data = data)
                    }
                } else {
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.exceptionOrNull()?.message ?: "No message"
                        )
                    }
                }
            }
            .stateIn(
                viewModelScope, started = SharingStarted.Eagerly, initialValue = ViewState()
            )


    fun handleViewIntent(viewIntent: ViewIntent) {
        when (viewIntent) {
            is ViewIntent.LoadHeadLines -> topHeadLines(viewIntent.page, viewIntent.query)
        }
    }
}

