package com.tsl.androidbase.repository.net.throwable


class ApiException(var code: Int, override var message: String?) : Exception() {

}
