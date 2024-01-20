package com.example.architecture

import android.app.Application
import co.infinum.retromock.Retromock
import com.example.architecture.data.Repository
import com.example.architecture.data.retrofit.QuoteService
import com.example.architecture.presentation.QuoteLiveData
import com.example.architecture.presentation.QuoteViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArchitectureApplication : Application() {
    lateinit var viewModel: QuoteViewModel

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(QuoteService::class.java)

        val isTests = false

        if (isTests) {
            val retrofitMock = Retromock.Builder()
                .retrofit(retrofit)
                .build()
            service = retrofitMock.create(QuoteService::class.java)
        }

        viewModel = QuoteViewModel(
            Repository(
                service
            ),
            QuoteLiveData()
        )
    }
}