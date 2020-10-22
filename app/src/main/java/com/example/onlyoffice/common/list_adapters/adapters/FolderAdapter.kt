package com.example.onlyoffice.common.list_adapters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlyoffice.R
import com.example.onlyoffice.common.list_adapters.FolderViewType
import com.example.onlyoffice.common.list_adapters.ViewType
import kotlinx.android.synthetic.main.item_folder.view.*

class FolderAdapter(val onClick: (String) -> Unit) : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = FolderViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as FolderViewHolder
        holder.bind(item as FolderViewType)
    }

    inner class FolderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_folder,
            parent,
            false
        )
    ) {

        fun bind(item: FolderViewType) {
            itemView.item_folder_title.text = item.folder.title
            itemView.item_folder_create_date.text = item.folder.created
            itemView.setOnClickListener {
                onClick.invoke(item.folder.id)
            }
        }

    }
}