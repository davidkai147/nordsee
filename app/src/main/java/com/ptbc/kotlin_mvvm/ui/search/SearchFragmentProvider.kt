package com.ptbc.kotlin_mvvm.ui.search

import com.ptbc.kotlin_mvvm.ui.about.AboutFragment
import com.ptbc.kotlin_mvvm.ui.feed.blogs.BlogFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentProvider {

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    internal abstract fun provideSearchFragmentFactory(): SearchFragment
}