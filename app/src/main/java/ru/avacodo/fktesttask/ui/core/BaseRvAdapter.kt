package ru.avacodo.fktesttask.ui.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvAdapter<DataType> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var dataList = listOf<DataType>()

    fun setData(mDataList: List<DataType>) {
        val diffUtilCallback = BaseDiffUtilCallback(dataList, mDataList)
        dataList = mDataList
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
    }

    override fun getItemCount() = dataList.size
}