package br.com.rac.domain.exceptions.entity

import androidx.annotation.StringDef

class ExceptionType(@param:Type val type: String) {

    @StringDef(
        SERVICE_UNAVAILABLE,
        INTERNAL_SERVER_ERROR,
        UNKNOWN_ERROR
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type

    companion object {
        const val SERVICE_UNAVAILABLE = "service-unavailable"
        const val INTERNAL_SERVER_ERROR = "internal-server-error"
        const val UNKNOWN_ERROR = "unknown-error"
    }


}