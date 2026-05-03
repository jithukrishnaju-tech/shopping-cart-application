package com.retailcloudandroid.retailcloudmachinetestandroid.domain.util

sealed class DomainException(message: String) : Exception(message) {

    data object NoInternetException :
        DomainException("No internet connection. Please check your network.")

    data object RequestTimeoutException :
        DomainException("Request timed out. Please try again.")

    // HTTP status code errors
    data object BadRequestException :
        DomainException("Bad request. Please try again.")        // 400

    data object UnauthorizedException :
        DomainException("Session expired. Please login again.")  // 401

    data object ForbiddenException :
        DomainException("You don't have permission to do this.") // 403

    data object NotFoundException :
        DomainException("The requested data was not found.")     // 404

    data object ServerException :
        DomainException("Server error. Please try again later.") // 500, 502, 503

    data object UnknownException :
        DomainException("Something went wrong. Please try again.")
}
