package com.demosample.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.base.BaseActivity
import com.google.gson.Gson
import com.demosample.mvvm.BR
import com.demosample.mvvm.R
import com.demosample.mvvm.databinding.ActivityCreateproductBinding
import com.demosample.mvvm.model.Student
import com.utils.Logger
import kotlinx.android.synthetic.main.activity_createproduct.*
import javax.inject.Inject

class CreateProductActivity : BaseActivity<ActivityCreateproductBinding, MainViewModel>(), MainNavigator {

    private val LOGGER = Logger.getLogger(CreateProductActivity::class.java)
    override fun getLayoutId() = R.layout.activity_createproduct
    override fun getBindingVariable() = BR.mainModel
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewFactory: ViewModelProvider.Factory

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProviders.of(this, viewFactory).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun doSomething() {

    }

    override fun updateUI(savedInstanceState: Bundle?) {
        mainViewModel.setNavigator(this)

        val gson = Gson()

        setUpRcv()
        mainViewModel.students.observe(this, Observer {
            LOGGER.error(gson.toJson(it))
        })
    }

    private fun setUpRcv() {

        if (mainViewModel.students.value?.size==0||mainViewModel.students.value?.size==null){
            var arrColor : Array<String> = arrayOf("green", "red", "blue")
            mainViewModel.insertStudent(Student("Black Shirt", null,"Formal, Available in all sizes",
                    "1500","1100","" , arrColor.toString()))

            mainViewModel.insertStudent(Student("Blue Shirt", null,"Casual, Available in all sizes",
                    "2500","1700","" , arrColor.toString()))

            mainViewModel.insertStudent(Student("Red Shirt", null,"Formal, Available in all sizes",
                    "1400","1200","" , arrColor.toString()))
        }

        btn_createProduct.setOnClickListener {
            var arrColor : Array<String> = arrayOf("green", "red", "blue")
            mainViewModel.insertStudent(Student(productname.text.toString(), null,descriptionname.text.toString(),
                    regularprice.text.toString(),saleprice.text.toString(),"" , arrColor.toString()))
        }

        btn_showproduct.setOnClickListener { view: View? ->

            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLayoutIdLoading(): Int {
        return R.layout.item_student
    }
}
