package com.example.lastfmapp.api

data class ApiResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResource<T> = ApiResource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResource<T> =
            ApiResource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiResource<T> = ApiResource(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
