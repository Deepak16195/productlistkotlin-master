package com.demosample.mvvm.di.component

import com.demosample.MvvmApp
import com.demosample.mvvm.di.builder.ActivityBuilder
import com.demosample.mvvm.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MvvmApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvvmApp)

}