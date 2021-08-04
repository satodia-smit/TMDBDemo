package com.hyperelement.mvvmdemo.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.Resource
import com.hyperelement.mvvmdemo.data.entity.search.Results
import com.hyperelement.mvvmdemo.data.entity.search.SearchBaseRes
import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.utils.getOrAwaitValue
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class FragmentMoviesVMTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var repository: FragmentOneRepository

    private lateinit var viewModel: FragmentMoviesVM

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = FragmentMoviesVM(repository)
    }

    @Test
    fun test_getSearchedItem_success_res_pageCheck() {
        testCoroutineDispatcher.runBlockingTest {
            val mockData = mock(Results::class.java)
            val movieRes = SearchBaseRes(1, listOf(mockData, mockData), 5, 5)
            Mockito.`when`(repository.getSearchedItem("Ironman", 1)).thenReturn(movieRes)
            viewModel.getSearchedItem("Ironman", 1)
            Mockito.verify(repository).getSearchedItem("Ironman", 1)
            assertEquals(viewModel.movieList.getOrAwaitValue().page, 1)
        }
    }

    @Test
    fun test_getSearchedItem_success_res_totalPageCheck() {
        testCoroutineDispatcher.runBlockingTest {
            val mockData = mock(Results::class.java)
            val movieRes = SearchBaseRes(1, listOf(mockData, mockData), 5, 5)
            Mockito.`when`(repository.getSearchedItem("Ironman", 1)).thenReturn(movieRes)
            viewModel.getSearchedItem("Ironman", 1)
            Mockito.verify(repository).getSearchedItem("Ironman", 1)
            assertEquals(viewModel.movieList.getOrAwaitValue().total_pages, 5)
        }
    }

    @Test
    fun test_getSearchedItem_success_res_dataCheck() {
        testCoroutineDispatcher.runBlockingTest {
            val mockData = mock(Results::class.java)
            val movieRes = SearchBaseRes(1, listOf(mockData, mockData), 5, 5)
            Mockito.`when`(repository.getSearchedItem("Ironman", 1)).thenReturn(movieRes)
            viewModel.getSearchedItem("Ironman", 1)
            Mockito.verify(repository).getSearchedItem("Ironman", 1)
            assertNotNull(viewModel.movieList.getOrAwaitValue())
        }
    }

    @Test
    fun test_getSearchedItem_negative_res_dataCheck() {
        testCoroutineDispatcher.runBlockingTest {
            val movieRes = SearchBaseRes(1, emptyList(), 5, 5)
            Mockito.`when`(repository.getSearchedItem("Ironman", 1)).thenReturn(movieRes)
            viewModel.getSearchedItem("Ironman", 1)
            Mockito.verify(repository).getSearchedItem("Ironman", 1)
            assertSame(viewModel.movieList.getOrAwaitValue().results, emptyList<Results>())
        }
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }
}