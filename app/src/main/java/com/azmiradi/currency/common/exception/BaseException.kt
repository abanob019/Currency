package com.azmiradi.currency.common.exception

import com.azmiradi.currency.common.utilities.extensions.getModelFromJSON
import java.lang.reflect.Type

sealed class BaseException(message: String?) : Exception(message) {
    sealed class Network(message: String? = null) : BaseException(message) {
        data class Retrial(override val message: String?) :
            Network(message)

        data class Unhandled(override val message: String?) :
            Network(message)
    }

    sealed class Client(message: String? = null) : BaseException(message) {
        data object Unauthorized : Client(message = "Unauthorized Access.")

        data class Unhandled(val httpErrorCode: Int, override val message: String?) : Client(
            message = "Unhandled client error with code:${httpErrorCode}, and the failure reason: $message"
        )

       data class BodyError(val httpErrorCode: Int, override val message: String?) : Client(
            message = message
        ) {
            fun <ErrorBody> getErrorBody(type: Type): ErrorBody? {
                return message?.getModelFromJSON<ErrorBody>(type)
            }
        }
    }

    sealed class Server(message: String? = null) : BaseException(message) {
        data class InternalServerError(val httpErrorCode: Int, override val message: String?) :
            Server(message = "Internal server error with code:${httpErrorCode}, and the failure reason: $message")
    }

    data class Unknown(override val message: String?) : BaseException(message)
}