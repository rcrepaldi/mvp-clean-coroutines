package br.com.rac.domain.exceptions.entity

import com.google.gson.annotations.SerializedName

class ErrorMessage(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("arguments")
    val arguments: Map<String, Any>? = null
)