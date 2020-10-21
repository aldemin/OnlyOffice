package com.example.onlyoffice.common.api.only_office

import com.example.onlyoffice.common.api.only_office.responses.UserInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Header

interface PeopleAPI {

    @GET("api/2.0/people/@self")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getUserInfo(@Header("Authorization") token: String): Single<UserInfoResponse>

}