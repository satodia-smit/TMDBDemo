package com.hyperelement.mvvmdemo.utilities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class RxViewModel : ViewModel(), CoroutineScope {

    val mState = MutableLiveData<State>()

     private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    override fun onCleared() {
        Timber.e("VIEW MODEL CLEARED $this")
        super.onCleared()
        parentJob.cancel()
    }
}