package com.demosample.mvvm.data

import com.demosample.mvvm.data.local.DbHelper
import com.demosample.mvvm.data.remote.ApiHelper


interface DataManager : ApiHelper,DbHelper