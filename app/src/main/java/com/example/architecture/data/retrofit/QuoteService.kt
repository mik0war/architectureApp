package com.example.architecture.data.retrofit

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import retrofit2.http.GET

interface QuoteService {

    @Mock
    @MockResponse(body="{\"content\":\"The meaning I picked, the one that changed my life: Overcome fear, behold wonder.\"," +
            "\"author\":\"Richard Bach\"," +
            "\"tags\":[\"Life\",\"Change\"], \"_id\":\"p3WMuYECz33S\"}")
    @GET("/random")
    suspend fun getQuote(): QuoteServerModel
}