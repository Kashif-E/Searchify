package com.kashif.data.network


enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1), BadRequest(400), NotFound(404), Conflict(409), InternalServerError(500), Forbidden(
        403
    ),
    NotAcceptable(406), ServiceUnavailable(503), UnAuthorized(401),
}


fun <T : Any> handleError(statusCode: Int, message: String): String {
    return getErrorType(statusCode, message)
}

private fun getErrorType(code: Int, message: String): String {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        ErrorCodes.UnAuthorized.code -> "Unauthorized"
        ErrorCodes.InternalServerError.code -> "Internal Server Error"
        ErrorCodes.BadRequest.code -> "Bad Request"
        ErrorCodes.Conflict.code -> "Conflict"
        ErrorCodes.NotFound.code -> "Not found"
        ErrorCodes.NotAcceptable.code -> "Not Acceptable"
        ErrorCodes.ServiceUnavailable.code -> "Service Unavailable"
        ErrorCodes.Forbidden.code -> "Forbidden"
        else -> message
    }
}