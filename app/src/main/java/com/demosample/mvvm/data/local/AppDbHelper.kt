package com.demosample.mvvm.data.local

import android.arch.lifecycle.LiveData
import com.demosample.mvvm.model.Student
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {
    override fun updateStudent(student: Student): Observable<Int>? {
        return Observable.fromCallable { appDatabase.studentDao().updateProduct(student) }
    }

    override fun getAllStudent(): LiveData<List<Student>> = appDatabase.studentDao().getAllStudent()

    override fun insertStudent(student: Student): Observable<Long> {
        return Observable.fromCallable { appDatabase.studentDao().insertStudent(student) }
    }

    override fun deleteStudent(student: Student): Observable<Boolean> =
            Observable.fromCallable {
                appDatabase.studentDao().deleteStudent(student)
                true
            }
}