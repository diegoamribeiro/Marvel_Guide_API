package com.diegoribeiro.marvelguide.utils

sealed class ResourceProject<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): ResourceProject<T>(data)
    class Error<T>(message: String, data: T? = null): ResourceProject<T>(data, message)
    class Loading<T>: ResourceProject<T>()
}