package com.jadhavrupesh.composequote.data

import com.jadhavrupesh.composequote.model.Quote
import com.jadhavrupesh.composequote.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET(Constant.END_POINT)
    suspend fun getRandomQuote(): Response<Quote>

}