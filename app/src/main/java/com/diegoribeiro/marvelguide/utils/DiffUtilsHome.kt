package com.diegoribeiro.marvelguide.utils

import androidx.recyclerview.widget.DiffUtil
import com.diegoribeiro.marvelguide.model.Character

class DiffUtilsHome(
    val oldList: List<Character>,
    val newList: List<Character>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}