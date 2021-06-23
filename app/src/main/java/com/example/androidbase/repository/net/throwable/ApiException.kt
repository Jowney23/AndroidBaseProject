package com.example.androidbase.repository.net.throwable


class ApiException : Exception {
    private var code: Int

    private var displayMessage: String?



    constructor(code: Int, displayMessage: String?) {
        this.code = code
        this.displayMessage = displayMessage
    }

    constructor(
        code: Int,
        message: String?,
        displayMessage: String
    ) : super(message) {
        this.code = code
        this.displayMessage = displayMessage
    }
}
