package com.jadhavrupesh.composequote.data

import com.jadhavrupesh.composequote.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteDataRepository  @Inject constructor(
    private val quoteApi: QuoteApi
){

    fun getRandomQuote():Flow<Response<Quote>> = flow {
        emit(quoteApi.getRandomQuote())
    }.flowOn(Dispatchers.IO)

}