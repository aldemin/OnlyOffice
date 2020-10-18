package com.example.onlyoffice.common.api.only_office

import com.example.onlyoffice.common.api.only_office.request.AuthorisationRequest
import com.example.onlyoffice.common.api.only_office.responses.AuthorisationResponse
import io.reactivex.Single
import retrofit2.http.*

interface AuthAPI {

    @POST("api/2.0/authentication")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun authorization(
        @Body authorisationRequest: AuthorisationRequest
    ): Single<AuthorisationResponse>

}