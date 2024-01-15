package com.example.architecture.entities

data class Quote(
    private val id: String,
    private val quoteText: String,
    private val author: String,
    private val tags: List<String>
){
    fun getText() = quoteText
}