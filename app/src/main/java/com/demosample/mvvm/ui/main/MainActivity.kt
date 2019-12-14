package com.demosample.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.base.BaseActivity
import com.google.gson.Gson
import com.demosample.mvvm.BR
import com.demosample.mvvm.R
import com.demosample.mvvm.databinding.ActivityMainBinding
import com.demosample.mvvm.model.Student
import com.demosample.mvvm.ui.main.adapter.StudentAdapter
import com.utils.Logger
import com.widget.AppScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private val LOGGER = Logger.getLogger(MainActivity::class.java)

    override fun getLayoutId() = R.layout.activity_main

    override fun getBindingVariable() = BR.mainModel

    //    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewFactory: ViewModelProvider.Factory
    @Inject
    lateinit var studentAdapter: StudentAdapter
    @Inject
    lateinit var layoutManager: LinearLayoutManager

    var studentList: ArrayList<Student> = arrayListOf()

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
            studentAdapter.setStudentList(it!!)
        })
    }

    private fun setUpRcv() {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = layoutManager
        rcv.adapter = studentAdapter

        studentAdapter.setOnItemDeleteClick(object : StudentAdapter.OnItemDeleteClick {
            override fun onItemDeleteClickListener(view: View, pos: Int) {
                LOGGER.error("$pos")
                toast(studentAdapter.getStudent(pos).id.toString())
                mainViewModel.deleteStudent(studentAdapter.getStudent(pos))
            }
        })

        studentAdapter.setOnItemUpdateClick(object : StudentAdapter.OnItemUpdateClick {
            override fun onItemUpdateClickListener(view: Student, pos: Int) {
                LOGGER.error("$pos"+view.name)
                view.name =  "best shirt for men"
                var intent = Intent(applicationContext,UpdateProductActivity::class.java)
                intent.putExtra("name",view.name)
                intent.putExtra("description",view.description)
                intent.putExtra("regularprice",view.regularprice)
                intent.putExtra("saleprice",view.saleprice)
                intent.putExtra("id",view.id)
                startActivity(intent)
//                mainViewModel.updateStudent(view)
            }

        })

        studentAdapter.setOnItemClick(object : StudentAdapter.OnItemClick {
            override fun onItemClickListener(view: Student, pos: Int) {
                LOGGER.error("$pos"+view.name)
            }
        })

        rcv.addOnScrollListener(object : AppScrollListener() {

            override fun onLoadMore() {
            }
        })

    }

    override fun getLayoutIdLoading(): Int {
        return R.layout.item_student
    }
}
