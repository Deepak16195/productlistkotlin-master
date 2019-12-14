package com.demosample.mvvm.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.demosample.mvvm.BR
import com.demosample.mvvm.databinding.ItemStudentBinding
import com.demosample.mvvm.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_student.view.*


class StudentAdapter(var context:Context) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    private var listStudent: List<Student>?
    private lateinit var onItemClick: OnItemClick
    private lateinit var onItemUpdateClick: OnItemUpdateClick
    private lateinit var onItemdeleteClick: OnItemDeleteClick
    var ctx:Context = context

    init {
        this.listStudent = arrayListOf()
    }

    fun setStudentList(listStudent: List<Student>) {
        this.listStudent = listStudent
        notifyDataSetChanged()
    }

    fun getStudent(position: Int) = listStudent!![position]

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    fun setOnItemUpdateClick(onItemUpdateClick: OnItemUpdateClick) {
        this.onItemUpdateClick = onItemUpdateClick
    }

    fun setOnItemDeleteClick(onItemdeleteClick: OnItemDeleteClick) {
        this.onItemdeleteClick = onItemdeleteClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        val studentBinding = ItemStudentBinding.inflate(layoutInflater, parent, false)
        return StudentHolder(studentBinding,parent.context)
    }

    override fun getItemCount(): Int = listStudent!!.size

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = listStudent!![position]
        holder.onBind(student)

        with(holder.itemView){
            Picasso.with(context).load("https://pngriver.com/wp-content/uploads/2017/11/gree-polo-shirt-free-PNG-transparent-background-images-free-download-clipart-pics-shirt-image-1.png").into(productimage, object:Callback{
                override fun onSuccess() {
                    Toast.makeText(context,"success",Toast.LENGTH_SHORT).show()
                }

                override fun onError() {
                    Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
                }
            });
        }

        with(holder.itemView){
            update.setOnClickListener { onItemUpdateClick.onItemUpdateClickListener(student,holder.adapterPosition) }
        }

        with(holder.itemView) {
            delete.setOnClickListener { onItemdeleteClick.onItemDeleteClickListener(it, holder.adapterPosition) }
        }

        with(holder.itemView) {
            item_student.setOnClickListener { onItemClick.onItemClickListener(student, holder.adapterPosition) }
        }
    }

    class StudentHolder(private var itemStudentBinding: ItemStudentBinding, context: Context) : RecyclerView.ViewHolder(itemStudentBinding.root) {

        var context:Context = context
        fun onBind(student: Student) {
            itemStudentBinding.setVariable(BR.studentModel, student)
            itemStudentBinding.executePendingBindings()

        }
    }

    interface OnItemClick {

        fun onItemClickListener(view: Student, pos: Int)
    }

    interface OnItemDeleteClick {

        fun onItemDeleteClickListener(view: View, pos: Int)
    }

    interface OnItemUpdateClick {

        fun onItemUpdateClickListener(view: Student, pos: Int)
    }
}