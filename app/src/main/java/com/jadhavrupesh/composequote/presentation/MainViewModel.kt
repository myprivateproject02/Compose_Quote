package com.jadhavrupesh.composequote.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadhavrupesh.composequote.data.RemoteDataRepository
import com.jadhavrupesh.composequote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository
) : ViewModel() {


    val response: MutableState<ApiState> = mutableStateOf(ApiState.Idle)

    fun getRandomQuote() = viewModelScope.launch {
        remoteDataRepository.getRandomQuote().onStart {
            response.value = ApiState.Loading
        }.catch {
            response.value = ApiState.Failuer(it)
        }.collect {
            response.value = ApiState.Success(it)
        }
    }


}