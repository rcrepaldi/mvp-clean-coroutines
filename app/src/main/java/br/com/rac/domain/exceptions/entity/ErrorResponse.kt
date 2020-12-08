package br.com.rac.domain.exceptions.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("error")
    var error: ErrorMessage = ErrorMessage()
)