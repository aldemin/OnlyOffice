package com.example.onlyoffice.common.list_adapters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlyoffice.R
import com.example.onlyoffice.common.list_adapters.FileViewType
import com.example.onlyoffice.common.list_adapters.ViewType
import kotlinx.android.synthetic.main.item_file.view.*

class FileAdapter : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = FileViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as FileViewHolder
        holder.bind(item as FileViewType)
    }

    class FileViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_file,
            parent,
            false
        )
    ) {

        fun bind(item: FileViewType) {
            itemView.item_file_title.text = item.file.title
            itemView.item_file_create_date.text = item.file.created
        }

    }
}