package com.ptbc.kotlin_mvvm.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ptbc.kotlin_mvvm.BR
import com.ptbc.kotlin_mvvm.R
import com.ptbc.kotlin_mvvm.ViewModelProviderFactory
import com.ptbc.kotlin_mvvm.data.model.api.CityResponse
import com.ptbc.kotlin_mvvm.databinding.FragmentSearchBinding
import com.ptbc.kotlin_mvvm.ui.base.BaseFragment
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchNavigator,
    SearchAdapter.CityAdapterListener {

    @Inject
    lateinit var mSearchAdapter: SearchAdapter
    private var mFragmentSearchBinding: FragmentSearchBinding? = null
    @Inject
    lateinit var mLayoutManager: LinearLayoutManager
    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mSearchViewModel: SearchViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_search

    override val viewModel: SearchViewModel
        get() {
            mSearchViewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
            return mSearchViewModel
        }

    override fun handleError(throwable: Throwable) {
        // handle error
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSearchViewModel.navigator = this
        mSearchAdapter.setListener(this)
        mSearchAdapter.StaggeredGridLayoutAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentSearchBinding = viewDataBinding
        setUp()
    }

    override fun updateCity(cityList: List<CityResponse.Cities>?) {
        if (cityList != null) {
            mSearchAdapter.addItems(cityList)
        }
    }

    private fun setUp() {
        mFragmentSearchBinding?.recyclerviewSearch?.setHasFixedSize(true)
        mFragmentSearchBinding?.recyclerviewSearch?.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
     //   mFragmentSearchBinding?.recyclerviewSearch?.itemAnimator = DefaultItemAnimator()
        mFragmentSearchBinding?.recyclerviewSearch?.adapter = mSearchAdapter
    }

    override fun onRetryClick() {
        mSearchViewModel.fetchCities()
    }

    companion object {
        fun newInstance() : SearchFragment{
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}