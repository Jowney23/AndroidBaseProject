package com.example.androidbase.repository.net.bean

class ServerResponse<T> {
    // 返回的code
    var reason: String? = null

    // 具体的数据结果
    var result: T? = null
    var errorCode: Int? = null

}
