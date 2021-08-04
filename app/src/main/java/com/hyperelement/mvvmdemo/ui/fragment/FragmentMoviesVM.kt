package com.hyperelement.mvvmdemo.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.hyperelement.mvvmdemo.data.entity.search.SearchBaseRes
import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


//Common VM for the List and Detail
class FragmentMoviesVM(
    private val mRepo: FragmentOneRepository
) : RootViewModel() {

    //Live data instance for communication
    val movieList = MutableLiveData<SearchBaseRes>()

    fun clearData() {
        movieList.postValue(null)
    }

    fun getSearchedItem(searchedItem: String, currentPage: Int) {
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = mRepo.getSearchedItem(searchedItem, currentPage)
                    Timber.d("searchedItem API RESPONSE $response")
                    movieList.postValue(response)

                    if (response.results.isEmpty()) {
                        Timber.d("NO MOVIE AVAILABLE")
                    } else {
                        movieList.postValue(response)
                    }
                }
            } catch (exception: Exception) {
                Timber.d("searchedItem API EXCEPTION $exception")
            }
        }
    }
}