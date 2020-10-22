package com.example.onlyoffice.common.list_adapters

import com.example.onlyoffice.common.list_adapters.Constants.FILE
import com.example.onlyoffice.common.list_adapters.Constants.FOLDER
import com.example.onlyoffice.model.responses.FilesResponse.Response.*

object Constants{
    const val FILE = 0
    const val FOLDER = 1
}

class FileViewType(val file: File): ViewType{
    override fun getViewType() = FILE
}

class FolderViewType(val folder: Folder): ViewType{
    override fun getViewType() = FOLDER
}
