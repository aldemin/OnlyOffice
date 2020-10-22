package com.example.onlyoffice.common.api.only_office

import com.example.onlyoffice.model.responses.FilesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface FilesAPI {

    @GET("api/2.0/files/@my")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getMyFiles(@Header("Authorization") token: String): Observable<FilesResponse>

    @GET("api/2.0/files/@common")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getCommonFiles(@Header("Authorization") token: String): Observable<FilesResponse>

    @GET("api/2.0/files/{folderId}")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getFileById(
        @Header("Authorization") token: String,
        @Path("folderId") folderId: String,
        @Query("userIdOrGroupId") userID: String,
        @Query("filterType") filterType: String = "none",
    ): Observable<FilesResponse>
}