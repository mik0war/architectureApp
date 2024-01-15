package com.example.architecture.data.retrofit

import retrofit2.http.GET

interface QuoteService {

    @GET("/random")
    suspend fun getQuote(): QuoteServerModel
}