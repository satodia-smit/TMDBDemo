package com.hyperelement.mvvmdemo.ui.fragment.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.entity.search.Results
import com.hyperelement.mvvmdemo.databinding.FragmentListBinding
import com.hyperelement.mvvmdemo.ui.activity.MainActivity
import com.hyperelement.mvvmdemo.ui.fragment.FragmentMoviesVM
import kotlinx.android.synthetic.main.fragment_list.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.listener.OnClickEventListener


//Framgent for the list
class FragmentMovieList :
    BaseFragment<FragmentMoviesVM>(R.layout.fragment_list, FragmentMoviesVM::class) {

    private lateinit var adapter: SmartRecyclerAdapter
    private var currentPage = 0
    private var currentSearch = ""
    private var totalPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentListBinding>()?.viewModel = viewModel

        adapter = SmartEndlessScrollRecyclerAdapter.empty()
            .setAutoLoadMoreEnabled(true)
            .setOnLoadMoreListener { adapter, loadMoreViewHolder ->
                if (currentPage <= totalPage) {
                    currentPage++
                    viewModel.getSearchedItem(currentSearch, currentPage)
                } else {
                    Toast.makeText(context, getString(R.string.lbl_no), Toast.LENGTH_SHORT).show()
                }

            }
            .add(OnClickEventListener {
                val action =
                    FragmentMovieListDirections.actionFragmentOneToFragmentDetail(it.adapter.getItem(it.position) as Results)
                findNavController().navigate(action)
            })
            .map(Results::class, GenericVH::class)
            .into(rvMovie)

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            it?.let {
                totalPage = it.total_pages
                adapter.addItems(it.results)
            }
        })
    }

    //To display search icon
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater?.inflate(R.menu.search, menu)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                currentSearch = newText
                viewModel.getSearchedItem(newText, currentPage)
                reset()
                return true
            }
        })
        searchView.setOnClickListener { view ->
        }
    }

    //To clear the Recyclerview when search changes
    fun reset() {
        viewModel.clearData()
        adapter.clear()
        currentPage = 0
    }
}