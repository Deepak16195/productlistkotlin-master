package com.demosample.mvvm.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.LinearLayoutManager
import com.ViewModelProviderFactory
import com.demosample.mvvm.data.DataManager
import com.demosample.mvvm.ui.main.adapter.StudentAdapter
import com.utils.SchedulerProvider
import dagger.Module
import dagger.Provides


@Module
class MainModule {

    @Provides
    fun provideMainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider): MainViewModel =
            MainViewModel(dataManager, schedulerProvider)

    @Provides
    fun provideViewModelFactory(mainViewModel: MainViewModel): ViewModelProvider.Factory = ViewModelProviderFactory(mainViewModel)

    @Provides
    fun provideLayoutManager(activity: MainActivity): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideStudentAdapter(activity: MainActivity) = StudentAdapter(activity)
}