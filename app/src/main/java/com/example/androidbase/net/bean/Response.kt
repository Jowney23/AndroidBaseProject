package com.example.androidbase.net.bean

class Response<T> {
    // 返回的code
    var code = 0
    // 具体的数据结果
    var data: T? = null
    var message: String? = null

}
