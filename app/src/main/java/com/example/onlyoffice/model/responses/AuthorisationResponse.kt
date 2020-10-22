package com.example.onlyoffice.model.responses

import com.google.gson.annotations.Expose

class AuthorisationResponse(
    @Expose val status: String,
    @Expose val response: AuthorisationResponse.Response
) {

    class Response(
        @Expose val token: String,
        @Expose val expires: String
    )

}