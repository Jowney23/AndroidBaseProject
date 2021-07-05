package com.example.androidbase.repository.net.bean

data class JokeData(val data: List<DataBean>) {

    data class DataBean(val content: String,
                        val hashId: String,
                        val unixtime: Int,
                        val updatetime: String)
}