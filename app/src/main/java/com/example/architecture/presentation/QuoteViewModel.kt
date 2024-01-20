package com.example.architecture.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.data.Repository
import com.example.architecture.data.RepositoryList
import com.example.architecture.entities.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface Observable<TYPE>{
    fun observe(owner: LifecycleOwner, observer: Observer<TYPE?>)
}

open class QuoteLiveData<TYPE> : Observable<TYPE>{
    private val data = MutableLiveData<TYPE?>()

    fun changeData(quote: TYPE){
        data.value = quote
    }

    fun getData(): TYPE? = data.value
    override fun observe(owner: LifecycleOwner, observer: Observer<TYPE?>){
        data.observe(owner, observer)
    }
}

class QuoteViewModel(
    private val repository: Repository,
    private val liveData: QuoteLiveData<Quote>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), Observable<Quote> {

    fun getQuote() = viewModelScope.launch(dispatcher) {
        liveData.changeData(repository.getQuote())
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Quote?>) {
        liveData.observe(owner, observer)
    }
}

class QuoteViewModelList(
    private val repository: RepositoryList,
    private val liveData: QuoteLiveData<List<Quote>>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), Observable<List<Quote>> {

    fun getQuote() = viewModelScope.launch(dispatcher) {
        liveData.changeData(repository.getQuotes())
    }

    fun getData() = liveData.getData() ?: emptyList()
    override fun observe(owner: LifecycleOwner, observer: Observer<List<Quote>?>) {
        liveData.observe(owner, observer)
    }
}

