package com.example.onlyoffice.model.responses

import com.google.gson.annotations.Expose

class UserInfoResponse(
    @Expose val status: String,
    @Expose val response: UserInfoResponse.Response
) {

    class Response(
        @Expose val id: String,
        @Expose val displayName: String,
        @Expose val email: String,
        @Expose val avatar: String
    )

}