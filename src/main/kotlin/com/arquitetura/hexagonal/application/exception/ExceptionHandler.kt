package com.arquitetura.hexagonal.application.exception

import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val responses = ex.bindingResult.fieldErrors.map {
            ExceptionResponse(status.reasonPhrase, it.field.plus(it.defaultMessage))
        }
        return ResponseEntity(responses, status)
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException,
                                                      headers: HttpHeaders,
                                                      status: HttpStatus,
                                                      request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
            ExceptionResponse(
                status.reasonPhrase,
                "O parâmetro ${ex.parameterName} é obrigatório"
            ),
            status
        )
    }

    override fun handleTypeMismatch(ex: TypeMismatchException,
                                    headers: HttpHeaders,
                                    status: HttpStatus,
                                    request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(
            ExceptionResponse(
                status.reasonPhrase,
                "O parâmetro ${(ex as MethodArgumentTypeMismatchException).name} requer valores do tipo ${ex.requiredType}"
            ),
            status
        )
    }

    @ExceptionHandler(Throwable::class)
    fun handleOtherExceptions(ex: Throwable, req: WebRequest): ResponseEntity<Any> {

        val statusCode = if(ex is CustomException) {
            ex.statusCode ?: HttpStatus.INTERNAL_SERVER_ERROR
        } else {
            HttpStatus.INTERNAL_SERVER_ERROR
        }

        ex.printStackTrace()

        return ResponseEntity.status(statusCode.value()).body(
            ExceptionResponse(statusCode.reasonPhrase, "${ex.message}")
        )
    }
}
