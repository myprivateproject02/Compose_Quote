package com.jadhavrupesh.composequote.utils

import com.jadhavrupesh.composequote.model.Quote
import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<Quote>) : ApiState()
    data class Failuer(val error: Throwable) : ApiState()
    data object Loading : ApiState()
    data object Idle : ApiState()

}