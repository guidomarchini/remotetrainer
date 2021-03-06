package unq.edu.remotetrainer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import unq.edu.remotetrainer.application.rest.exception.EntityNotFoundException
import unq.edu.remotetrainer.application.rest.exception.ValidationError
import unq.edu.remotetrainer.application.utils.ErrorResponse

/**
 * Defines the exceptions return http status code
 */
@ControllerAdvice
class RemoteTrainerAdvice {
    companion object {
        @JvmStatic
        private val logger: Logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun entityNotFoundHandler(exception: EntityNotFoundException): ErrorResponse {
        logger.error(exception.toString(), exception)
        return ErrorResponse(exception.localizedMessage)
    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun invalidRequestHandler(error: ValidationError): ErrorResponse {
        logger.error("validation error: $error", error)
        return ErrorResponse(error.localizedMessage)
    }
}