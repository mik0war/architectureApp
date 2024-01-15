package com.example.architecture.data.retrofit

import com.example.architecture.entities.Quote
import com.google.gson.annotations.SerializedName

data class QuoteServerModel(
    @SerializedName("_id")
    private val id: String,
    @SerializedName("content")
    private val quoteText: String,
    @SerializedName("author")
    private val author: String,
    @SerializedName("tags")
    private val tags: List<String>
){
    fun toQuoteModel(): Quote = Quote(id, quoteText, author, tags)
}
