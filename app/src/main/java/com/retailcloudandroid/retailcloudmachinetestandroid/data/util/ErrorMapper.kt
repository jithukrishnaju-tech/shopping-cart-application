package com.retailcloudandroid.retailcloudmachinetestandroid.data.util

import com.retailcloudandroid.retailcloudmachinetestandroid.domain.util.DomainException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toDomainException(): DomainException {
    return when (this) {

        // No internet — device not connected
        is UnknownHostException ->
            DomainException.NoInternetException

        // Request took too long
        is SocketTimeoutException ->
            DomainException.RequestTimeoutException

        // General IO / network failure
        is IOException ->
            DomainException.NoInternetException

        // HTTP error codes from server
        is HttpException -> {
            when (this.code()) {
                400 -> DomainException.BadRequestException
                401 -> DomainException.UnauthorizedException
                403 -> DomainException.ForbiddenException
                404 -> DomainException.NotFoundException
                500, 502, 503 -> DomainException.ServerException
                else -> DomainException.UnknownException
            }
        }

        // Anything else
        else -> DomainException.UnknownException
    }
}
