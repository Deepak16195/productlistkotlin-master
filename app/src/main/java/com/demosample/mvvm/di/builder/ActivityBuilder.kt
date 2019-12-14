package com.demosample.mvvm.di.builder

import com.demosample.mvvm.ui.main.*
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(CreateProductModule::class)])
    abstract fun bindCreateProductActivity(): CreateProductActivity

    @ContributesAndroidInjector(modules = [(UpdateProductModule::class)])
    abstract fun bindUpdateProductActivity(): UpdateProductActivity
}