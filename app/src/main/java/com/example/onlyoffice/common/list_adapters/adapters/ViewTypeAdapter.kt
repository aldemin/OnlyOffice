package com.example.onlyoffice.common.list_adapters.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlyoffice.common.list_adapters.ViewType

interface ViewTypeAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}