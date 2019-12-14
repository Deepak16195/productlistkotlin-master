package com.demosample.mvvm.data.local

import android.arch.lifecycle.LiveData
import com.demosample.mvvm.model.Student
import io.reactivex.Observable


interface DbHelper {

    fun getAllStudent(): LiveData<List<Student>>

    fun insertStudent(student: Student): Observable<Long>

    fun updateStudent(student: Student): Observable<Int>?

    fun deleteStudent(student: Student): Observable<Boolean>
}