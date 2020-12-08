package br.com.rac.domain.exceptions

import br.com.rac.domain.exceptions.entity.ErrorResponse


sealed class MyHttpErrorException : MyException {

    constructor(message: String, code: Int) : super(message)
    constructor(message: String, code: Int, cause: Throwable) : super(message, cause)
}

/**
 * Should be thrown when occurs a 4xx Http error
 */
class HttpClientErrorException(message: String, val  code: Int, cause: Throwable, val errorResponse: ErrorResponse) :
    MyHttpErrorException(message, code, cause)


/**
 * Should be thrown when occurs a 5xx Http error
 */
class HttpServerErrorException : MyHttpErrorException {
    constructor(message: String, code: Int) : super(message, code)
    constructor(message: String, code: Int, cause: Throwable) : super(message, code, cause)
}


/**
 * Should be thrown when occurs a 401 unauthorized
 */
class RefreshTokenException : MyHttpErrorException {
    constructor(message: String, code: Int) : super(message, code)
}

/**
 * Should be thrown when occurs a 400 invalided refresh token
 */
class InvalidedRefreshTokenException : MyHttpErrorException {
    constructor(message: String, code: Int) : super(message, code)
}


/**
 * Should be thrown when occurs a 400 Http error
 */
class LoginErrorException(message: String, val  code: Int, cause: Throwable, val errorResponse: ErrorResponse) :
    MyHttpErrorException(message, code, cause)
