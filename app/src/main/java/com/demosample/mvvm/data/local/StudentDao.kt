package com.demosample.mvvm.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.demosample.mvvm.model.Student
import android.arch.persistence.room.Update

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(student: Student): Int

    @Query("select * from student")
    fun getAllStudent(): LiveData<List<Student>>

    @Delete
    fun deleteStudent(vararg student: Student)

    @Query("SELECT * FROM student WHERE id == :id")
    fun getTour(id: Int): Student

    /*fun updateProductItem(student: Student){
        val tour = getTour(student.id!!)
        tour.name = student.name
        tour.description = student.description//productdesc
        tour.regularprice = student.regularprice
        tour.saleprice = student.saleprice
        updateProduct(tour)

    }*/
}