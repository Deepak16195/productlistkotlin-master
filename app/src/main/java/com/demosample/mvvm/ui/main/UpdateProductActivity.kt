package com.demosample.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.base.BaseActivity
import com.google.gson.Gson
import com.demosample.mvvm.BR
import com.demosample.mvvm.R
import com.demosample.mvvm.databinding.ActivityCreateproductBinding
import com.demosample.mvvm.model.Student
import com.utils.Logger
import kotlinx.android.synthetic.main.activity_createproduct.*
import javax.inject.Inject

class UpdateProductActivity : BaseActivity<ActivityCreateproductBinding, MainViewModel>(), MainNavigator {

    private val LOGGER = Logger.getLogger(UpdateProductActivity::class.java)
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


        productname.setText(intent.getStringExtra("name"))
        descriptionname.setText(intent.getStringExtra("description"))
        regularprice.setText(intent.getStringExtra("regularprice"))
        saleprice.setText(intent.getStringExtra("saleprice"))

//        var student : Student = Student("",null,"","","","")

        btn_createProduct.setOnClickListener {
            var arrColor : Array<String> = arrayOf("green", "red", "blue")
            mainViewModel.updateStudent(Student(productname.text.toString(), intent.getIntExtra("id",0),descriptionname.text.toString(),
                    regularprice.text.toString(),saleprice.text.toString(),"" , arrColor.toString()))
        }


    }

    override fun getLayoutIdLoading(): Int {
        return R.layout.item_student
    }
}
