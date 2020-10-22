package com.example.onlyoffice.common.list_adapters

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlyoffice.common.list_adapters.adapters.ViewTypeAdapter

class ListAdapter(vararg adapters: Pair<Int, ViewTypeAdapter>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeAdapter>()

    init {
        for (adapter in adapters) {
            delegateAdapters.put(adapter.first, adapter.second)
        }
    }

    var items = arrayListOf<ViewType>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters[viewType]!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters[getItemViewType(position)]!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun getItemCount() = items.size
}