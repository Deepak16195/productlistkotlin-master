package com.demosample.mvvm.ui.main

import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.demosample.mvvm.base.BaseViewModel
import com.demosample.mvvm.data.DataManager
import com.demosample.mvvm.model.Student
import com.utils.SchedulerProvider

/**
 * Created by Kaz on 09:59 8/20/18
 */
class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<MainNavigator>(
        dataManager, schedulerProvider) {

    fun doSomething() {
//        getNavigator().doSomething()
//        student.set(Student("Fuck", "d"))
    }

    var students: LiveData<List<Student>> = dataManager.getAllStudent()

    var text: ObservableField<String> = ObservableField()

    var student = ObservableField<Student>()

    fun getAllCountry() {
        compositeDisposable.add(dataManager.getAllCountry(mutableMapOf())
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({ _ ->

                }, { _ ->

                }))
    }

    fun insertStudent(student: Student) {
        compositeDisposable.add(dataManager.insertStudent(student)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({

                }, {

                }))
    }

    fun updateStudent(student: Student) {
        compositeDisposable.add(dataManager.updateStudent(student)?.compose(schedulerProvider.ioToMainObservableScheduler())?.subscribe({

        }, {

        })!!)
    }

    fun deleteStudent(student: Student) {
        compositeDisposable.add(dataManager.deleteStudent(student)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe())
//        dataManager.deleteStudent(student)
    }
}