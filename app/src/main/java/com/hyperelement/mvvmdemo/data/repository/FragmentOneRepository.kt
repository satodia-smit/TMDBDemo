package com.hyperelement.mvvmdemo.data.repository

import android.content.Context
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.local.SearchAPIService
import com.hyperelement.mvvmdemo.data.entity.search.SearchBaseRes
import com.hyperelement.mvvmdemo.utilities.AppUtils
import java.net.UnknownHostException


class FragmentOneRepository(
    private val context: Context,
    private val searchAPIService: SearchAPIService
) {
    suspend fun getSearchedItem(aQuery: String, aPage: Int): SearchBaseRes {
        if (!AppUtils.hasNetwork(context)) {
            throw UnknownHostException(context.getString(R.string.err_no_internet))
        }
        return searchAPIService.getSearchedItem(aQuery, aPage)
    }
}