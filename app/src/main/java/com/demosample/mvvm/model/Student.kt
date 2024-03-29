package com.demosample.mvvm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose

/**
 * Created by Kaz on 08:22 8/28/18
 */
@Entity(tableName = "student")
data class Student(@Expose var name: String,
                   @PrimaryKey(autoGenerate = true) @Expose var id: Int?,
                   @Expose var description: String,
                   @Expose var regularprice: String,
                   @Expose var saleprice: String,
                   @Expose var productimage: String,
                   val colors: String = arrayOf<String>().toString())