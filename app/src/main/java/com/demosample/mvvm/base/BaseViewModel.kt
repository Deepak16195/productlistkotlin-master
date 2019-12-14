package com.demosample.mvvm.base

import com.base.ViewModelB
import com.demosample.mvvm.data.DataManager
import com.utils.SchedulerProvider

open class BaseViewModel<N>(var dataManager: DataManager, var schedulerProvider: SchedulerProvider) :
        ViewModelB<N>(schedulerProvider)