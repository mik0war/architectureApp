package com.example.architecture

import android.app.Application
import co.infinum.retromock.Retromock
import com.example.architecture.data.Repository
import com.example.architecture.data.RepositoryList
import com.example.architecture.data.retrofit.QuoteService
import com.example.architecture.presentation.QuoteLiveData
import com.example.architecture.presentation.QuoteViewModel
import com.example.architecture.presentation.QuoteViewModelList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArchitectureApplication : Application() {

    private val IS_TEST = true

    lateinit var viewModel: QuoteViewModel
    lateinit var listViewModel: QuoteViewModelList

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(QuoteService::class.java)

        if (IS_TEST){
            val retroMock = Retromock.Builder()
                .retrofit(retrofit)
                .build()

            service = retroMock.create(QuoteService::class.java)
        }


        viewModel = QuoteViewModel(
            Repository(
                service
            ),
            QuoteLiveData()
        )

        listViewModel = QuoteViewModelList(
            RepositoryList(
                service
            ), QuoteLiveData()
        )
    }
}