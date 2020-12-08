package br.com.rac.domain.exceptions

abstract class MyException : Exception {

    override val message: String
        get() = super.message ?: ""

    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)

}