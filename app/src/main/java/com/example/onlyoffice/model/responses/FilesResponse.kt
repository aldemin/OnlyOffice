package com.example.onlyoffice.model.responses

import com.google.gson.annotations.Expose

class FilesResponse(
    @Expose val status: String,
    @Expose val response: FilesResponse.Response
) {

    class Response(
        @Expose val files: List<FilesResponse.Response.File>,
        @Expose val folders: List<FilesResponse.Response.Folder>
    ) {

        class File(
            @Expose val id: String,
            @Expose val title: String,
            @Expose val created: String
        )

        class Folder(
            @Expose val id: String,
            @Expose val title: String,
            @Expose val created: String
        )

    }

}