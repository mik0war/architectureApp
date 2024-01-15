package com.example.architecture.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.data.Repository
import com.example.architecture.entities.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface Observable{
    fun observe(owner: LifecycleOwner, observer: Observer<Quote>)
}

open class QuoteLiveData : Observable{
    private val data = MutableLiveData<Quote>()

    fun changeData(quote: Quote){
        data.value = quote
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Quote>){
        data.observe(owner, observer)
    }
}

class QuoteViewModel(
    private val repository: Repository,
    private val liveData: QuoteLiveData,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), Observable {

    fun getQuote() = viewModelScope.launch(dispatcher) {
        liveData.changeData(repository.getQuote())
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Quote>) {
        liveData.observe(owner, observer)
    }
}