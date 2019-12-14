package com.demosample.mvvm.ui.main

import android.arch.lifecycle.ViewModelProvider
import com.ViewModelProviderFactory
import com.demosample.mvvm.data.DataManager
import com.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Kaz on 10:07 8/20/18
 */
@Module
class CreateProductModule {

    @Provides
    fun provideMainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider): MainViewModel =
            MainViewModel(dataManager, schedulerProvider)

    @Provides
    fun provideViewModelFactory(mainViewModel: MainViewModel): ViewModelProvider.Factory = ViewModelProviderFactory(mainViewModel)

}