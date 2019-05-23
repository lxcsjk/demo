package com.betterlxc.retrofit.interceptor;


import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2018/9/3
 */
@Log4j2
public class HttpLoggingInterceptor implements Interceptor {

    private static final int SUCCESS = 200;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static String PROJ_NAME = "mwee";

    public HttpLoggingInterceptor() {
    }

    public HttpLoggingInterceptor(String projName) {
        PROJ_NAME = projName;
    }

    public static String getUrlSuffix(String url) {
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }

        if (url.endsWith("/")) {
            return getUrlSuffix(url.substring(0, url.length() - 1));
        }

        return StringUtils.substring(url, url.lastIndexOf("/") + 1);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            log.warn("请求异常： " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        RequestBody requestBody = request.body();

        String requestStr = "";
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
                if (charset != null) {
                    requestStr = buffer.readString(charset);
                } else {
                    requestStr = buffer.readString(UTF8);
                }
            }
        }


        ResponseBody responseBody = response.body();
        String url = response.request().url().toString();

        String responseStr = "";
        if (responseBody != null && HttpHeaders.hasBody(response) && response.code() == SUCCESS) {

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset;
            MediaType contentType = responseBody.contentType();

            if (contentType != null) {
                charset = contentType.charset(UTF8);
                if (charset != null) {
                    responseStr = buffer.clone().readString(charset);
                } else {
                    responseStr = buffer.clone().readString(UTF8);
                }
            }
            log.info("请求路径： [" + request.method() + ": " + response.request().url() + "]  请求参数： [" + requestStr + "]  返回信息： [" + responseStr + "]  耗时： [" + tookMs + "ms" + "]");
        } else {
            log.error("请求路径： [" + request.method() + ": " + response.request().url() + "]  请求参数： [" + requestStr + "]  错误码： [" + response.code() + "]  耗时： [" + tookMs + "ms" + "]");
        }
        return response;
    }

    private String getMetricsPrefixName(String url) {
        String urlMethod = getUrlSuffix(url);
        return PROJ_NAME + "." + urlMethod;
    }
}
