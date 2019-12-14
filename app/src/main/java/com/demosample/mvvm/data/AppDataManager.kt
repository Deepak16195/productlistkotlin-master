package com.demosample.mvvm.data

import android.arch.lifecycle.LiveData
import android.content.Context
import com.google.gson.JsonObject
import com.demosample.mvvm.data.local.DbHelper
import com.demosample.mvvm.data.remote.ApiHelper
import com.demosample.mvvm.model.Student
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManager @Inject constructor(private val apiHelper: ApiHelper, private val dbHelper: DbHelper, private val
context: Context) : DataManager {

    override fun updateStudent(student: Student) = dbHelper.updateStudent(student)

    override fun getAllCountry(params: MutableMap<String, String>): Single<JsonObject> {
        return apiHelper.getAllCountry(params)
    }

    override fun getAllStudent(): LiveData<List<Student>> = dbHelper.getAllStudent()

    override fun insertStudent(student: Student) = dbHelper.insertStudent(student)

    override fun deleteStudent(student: Student): Observable<Boolean> = dbHelper.deleteStudent(student)
}