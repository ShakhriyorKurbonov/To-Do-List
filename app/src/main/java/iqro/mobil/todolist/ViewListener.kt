package iqro.mobil.todolist

import java.text.FieldPosition

interface ViewListener {
    fun onClickDate(position: Int)
    fun onClickDelete(position: Int)
    fun onCheck(position: Int)
    fun onEdit(position: Int)
}