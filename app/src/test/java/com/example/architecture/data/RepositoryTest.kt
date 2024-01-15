package com.example.architecture.data

import com.example.architecture.data.retrofit.QuoteServerModel
import com.example.architecture.data.retrofit.QuoteService
import com.example.architecture.entities.Quote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {

    val testDispatcher = StandardTestDispatcher()

    @Test
    fun testRepository() = runTest(testDispatcher) {

        val testService = TestQuoteService()

        val repository = Repository(testService, testDispatcher)

        val expected = Quote("first", "first text", "test author", listOf("First"))

        val actual = repository.getQuote()

        assertEquals(expected, actual)


    }
}

class TestQuoteService : QuoteService {
    var index = 0

    override suspend fun getQuote(): QuoteServerModel {
        val quotesList = listOf(
            QuoteServerModel("first", "first text", "test author", listOf("First")),
            QuoteServerModel("second", "second text", "test author", listOf("Second")),
            QuoteServerModel("third", "third text", "test author", listOf("First", "Second"))
        )

        return quotesList[index]
    }
}