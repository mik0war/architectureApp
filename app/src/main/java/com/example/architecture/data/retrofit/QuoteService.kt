package com.example.architecture.data.retrofit

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import retrofit2.http.GET

interface QuoteService {

    @Mock
    @MockResponse(
        body = "{\n" +
                "  \"_id\": \"test id\",\n" +
                "  \"content\": \"Test quote text\",\n" +
                "  \"author\": \"test quote author\",\n" +
                "  \"tags\": [\n" +
                "    \"test tag 1\",\n" +
                "    \"test tag 2\",\n" +
                "    \"test tag 3\"\n" +
                "  ]\n" +
                "}"
    )
    @GET("/random")
    suspend fun getQuote(): QuoteServerModel


    @Mock
    @MockResponse(body = "[\n" +
            "  {\n" +
            "    \"_id\": \"test id 1\",\n" +
            "    \"content\": \"Test quote text 1\",\n" +
            "    \"author\": \"test quote author 2\",\n" +
            "    \"tags\": [\n" +
            "      \"test tag 1\",\n" +
            "      \"test tag 2\",\n" +
            "      \"test tag 3\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"_id\": \"test id 2\",\n" +
            "    \"content\": \"Test quote text\",\n" +
            "    \"author\": \"test quote author\",\n" +
            "    \"tags\": [\n" +
            "      \"test tag 1\",\n" +
            "      \"test tag 2\"\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"_id\": \"test id 3\",\n" +
            "    \"content\": \"Test quote text 3\",\n" +
            "    \"author\": \"test quote author 3\",\n" +
            "    \"tags\": [\n" +
            "      \"test tag 3\"\n" +
            "    ]\n" +
            "  }\n" +
            "]")
    @GET("/test")
    suspend fun getQuotesList(): List<QuoteServerModel>

}