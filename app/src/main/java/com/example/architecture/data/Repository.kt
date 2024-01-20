package com.example.architecture.data

import com.example.architecture.data.retrofit.QuoteService
import com.example.architecture.entities.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val service: QuoteService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getQuote(): Quote = withContext(dispatcher) {
        return@withContext service.getQuote().toQuoteModel()
    }
}

class RepositoryList(
    private val service: QuoteService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getQuotes(): List<Quote> = withContext(dispatcher) {
        return@withContext service.getQuotesList().map { it.toQuoteModel() }
    }
}
