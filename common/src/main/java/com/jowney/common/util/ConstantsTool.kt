package com.jowney.common.util
import com.jowney.common.BaseApplication
import java.io.File
import java.text.DecimalFormat
import java.util.*

object ConstantsTool {
    /******************** 存储相关常量  */
    /**
     * Byte与Byte的倍数
     */
    const val BYTE = 1

    /**
     * KB与Byte的倍数
     */
    const val KB = 1024

    /**
     * MB与Byte的倍数
     */
    const val MB = 1048576

    /**
     * GB与Byte的倍数
     */
    const val GB = 1073741824

    /**
     * 毫秒与毫秒的倍数
     */
    const val MSEC = 1
    /******************** 时间相关常量  */
    /**
     * 秒与毫秒的倍数
     */
    const val SEC = 1000

    /**
     * 分与毫秒的倍数
     */
    const val MIN = 60000

    /**
     * 时与毫秒的倍数
     */
    const val HOUR = 3600000

    /**
     * 天与毫秒的倍数
     */
    const val DAY = 86400000

    const val FAST_CLICK_TIME = 100
    const val VIBRATE_TIME = 100

    const val SP_SCAN_CODE = "SCAN_CODE"

    //微信统一下单接口
    const val WX_TOTAL_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder"

    //高德地图APP 包名
    const val GAODE_PACKAGE_NAME = "com.autonavi.minimap"

    //百度地图APP 包名
    const val BAIDU_PACKAGE_NAME = "com.baidu.BaiduMap"

    /**
     * 速度格式化
     */
    val FORMAT_ONE = DecimalFormat("#.#")

    /**
     * 距离格式化
     */
    @JvmField
    val FORMAT_TWO = DecimalFormat("#.##")

    /**
     * 速度格式化
     */
    val FORMAT_THREE = DecimalFormat("#.###")

    //默认保存下载文件目录
    val DOWNLOAD_DIR = FileTool.rootPath.toString() + File.separator + "Download"  + File.separator

    //图片缓存路径
    @JvmField
    val PICTURE_CACHE_PATH = FileTool.getCacheFolder(BaseApplication.application).toString() + File.separator + "Picture" + File.separator + "Cache" + File.separator

    //图片原始路径
    val PICTURE_ORIGIN_PATH = FileTool.rootPath.toString() + File.separator + "Picture" + File.separator + "Origin" + File.separator

    //图片压缩路径
    val PICTURE_COMPRESS_PATH = FileTool.rootPath.toString()+ File.separator + "Picture" + File.separator + "Compress" + File.separator

    //默认导出文件目录
    val EXPORT_FILE_PATH = FileTool.rootPath.toString() + File.separator + "ExportFile" + File.separator

    //图片名称
    val pictureName: String
        get() = TimeTool.getCurrentDateTime(DATE_FORMAT_LINK) + "_" + Random().nextInt(1000) + ".jpg"

    //Date格式
    const val DATE_FORMAT_LINK = "yyyyMMddHHmmssSSS"

    //Date格式 常用
    const val DATE_FORMAT_DETACH = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_DETACH_CN = "yyyy年MM月dd日 HH:mm:ss"
    const val DATE_FORMAT_DETACH_CN_SSS = "yyyy年MM月dd日 HH:mm:ss SSS"

    //Date格式 带毫秒
    const val DATE_FORMAT_DETACH_SSS = "yyyy-MM-dd HH:mm:ss SSS"

    //时间格式 分钟：秒钟 一般用于视频时间显示
    const val DATE_FORMAT_MM_SS = "mm:ss"

    // Performance testing notes (JDK 1.4, Jul03, scolebourne)
    // Whitespace:
    // Character.isWhitespace() is faster than WHITESPACE.indexOf()
    // where WHITESPACE is a string of all whitespace characters
    //
    // Character access:
    // String.charAt(n) versus toCharArray(), then array[n]
    // String.charAt(n) is about 15% worse for a 10K string
    // They are about equal for a length 50 string
    // String.charAt(n) is about 4 times better for a length 3 string
    // String.charAt(n) is best bet overall
    //
    // Append:
    // String.concat about twice as fast as StringBuffer.append
    // (not sure who tested this)
    /**
     * A String for a space character.
     *
     * @since 3.2
     */
    const val SPACE = " "

    enum class MemoryUnit {
        BYTE, KB, MB, GB
    }

    enum class TimeUnit {
        MSEC, SEC, MIN, HOUR, DAY
    }
}