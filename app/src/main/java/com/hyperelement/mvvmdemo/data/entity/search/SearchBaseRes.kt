package com.hyperelement.mvvmdemo.data.entity.search

data class SearchBaseRes (

	val page : Int,
	val results : List<Results>,
	val total_pages : Int,
	val total_results : Int
)