package com.demosample.mvvm.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.demosample.mvvm.model.Student


@Database(entities = [Student::class], exportSchema = false, version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}