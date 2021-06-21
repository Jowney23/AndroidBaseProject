package com.jowney.common.net.interceptor;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class LogInterceptor implements Interceptor {
    private final static String TAG = "Log_Interceptor";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        //log 信息
        StringBuilder builder = new StringBuilder();
        Request request = chain.request();
        Headers requestHeaders = request.headers();
        boolean hasRequestHeader = requestHeaders.size() != 0;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        //请求行
        String requestStartMessage = "Request:\n " + request.method() + " " + request.url() + " " + protocol + "\n";
        builder.append(requestStartMessage);

        //请求头
        if (hasRequestHeader) {
            for (int i = 0, count = requestHeaders.size(); i < count; i++) {
                String name = requestHeaders.name(i);
                builder.append(name + ": " + requestHeaders.value(i)+"\n");
            }
        }else {
            builder.append("request header don't have member");
        }

        //请求体
        if (!hasRequestBody) {
            builder.append("\n请求结束 --> END " + request.method());
        } else if (bodyEncoded(request.headers())) {
            builder.append("\n请求结束 --> END " + request.method() + " (encoded body omitted)");
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (isPlaintext(buffer)) {
                builder.append(buffer.readString(charset));
                builder.append("\n请求结束 --> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)");
            } else {
                builder.append("\n请求结束 --> END " + request.method() + " (binary "
                        + requestBody.contentLength() + "-byte body omitted)");
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            builder.append("\n请求出错 ----> " + e.getMessage());
            Log.d(TAG, "请求信息如下:\n" + builder);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        Headers responseHeaders = response.request().headers();
        builder.append("\n响应头 ----> \n" + responseHeaders.toString());
        if (requestBody != null) {
            long contentLength = responseBody.contentLength();
            builder.append("响应码 ----> " + response.code() + " 用时:(" + tookMs + "ms)");

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    builder.append("\nCouldn't decode the response body; charset is likely malformed.");
                    builder.append("\n<-- END HTTP");
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                builder.append("\n<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                return response;
            }

            if (contentLength != 0) {
                builder.append("\n响应体 ---->");
                builder.append("\n" + buffer.clone().readString(charset));
            }
            builder.append("\n<-- 请求结束 END HTTP (" + buffer.size() + "-byte body)");
        }


        Log.d(TAG, "\n--------------------Net Log Interceptor--------------------------" + builder);
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    private boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
