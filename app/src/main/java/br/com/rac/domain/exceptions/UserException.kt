package br.com.rac.domain.exceptions

import br.com.rac.domain.exceptions.entity.ErrorResponse

class UserException(message: String, cause: Throwable, val errorResponse: ErrorResponse?) : MyException(message, cause)