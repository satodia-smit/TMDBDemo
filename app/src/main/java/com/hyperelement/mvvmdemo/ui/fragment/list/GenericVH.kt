package com.hyperelement.mvvmdemo.ui.fragment.list

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.entity.search.Results
import com.hyperelement.mvvmdemo.databinding.ItemMovieBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.SmartViewHolderType
import smartadapter.viewevent.listener.OnViewEventListener
import smartadapter.viewholder.SmartViewHolder

//Standard ViewHolder as per the smartadapter
class GenericVH(var parentView: ViewGroup) : SmartViewHolder<Results>(
    parentView.inflate<ItemMovieBinding>(R.layout.item_movie).root
) {
    override fun bind(item: Results) {
        val binding = DataBindingUtil.getBinding<ItemMovieBinding>(itemView)
        //Settign the DB variable
        binding?.dataItem = item
    }
}