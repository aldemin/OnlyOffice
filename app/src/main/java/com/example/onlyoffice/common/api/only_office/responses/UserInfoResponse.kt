package com.example.onlyoffice.common.api.only_office.responses

import com.google.gson.annotations.Expose

class UserInfoResponse(
    @Expose val status: String,
    @Expose val response: UserInfoResponse.Response
) {

    class Response(
        @Expose val userName: String,
        @Expose val email: String,
        @Expose val avatar: String
    )

}