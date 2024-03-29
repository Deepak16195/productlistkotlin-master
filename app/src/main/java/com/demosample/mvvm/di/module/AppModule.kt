package com.demosample.mvvm.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.GsonBuilder
import com.demosample.MvvmApp
import com.demosample.mvvm.data.AppDataManager
import com.demosample.mvvm.data.DataManager
import com.demosample.mvvm.data.local.AppDatabase
import com.demosample.mvvm.data.local.AppDbHelper
import com.demosample.mvvm.data.local.DbHelper
import com.demosample.mvvm.data.remote.ApiHelper
import com.demosample.mvvm.data.remote.AppApiHelper
import com.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.R
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider() = SchedulerProvider()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!!

    @Provides
    @Singleton
    fun provideContext(mvvmApp: MvvmApp): Context = mvvmApp

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    fun provideDataManager(dataManager: AppDataManager): DataManager = dataManager

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig =
            CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "hihi").allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper = appDbHelper
}