package com.ptbc.kotlin_mvvm.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @Provides
    internal fun provideSearchAdapter(): SearchAdapter {
        return SearchAdapter(ArrayList())
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: SearchFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }
}