package com.example.androidbase.repository.net.bean

data class ServerResponse<T>(
    val resultcode: String,
    val reason: String,
    var result: T?,
    val error_code: Int
)
