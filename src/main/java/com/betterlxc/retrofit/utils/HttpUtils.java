package com.betterlxc.retrofit.utils;

import lombok.extern.log4j.Log4j2;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by liuxincheng on 2018/8/31.
 */
@Log4j2
public class HttpUtils {


    /**
     * Execute net request
     *
     * @param call Net Request
     * @return Resolved response
     * @throws IOException
     */
    public static <T> T execute(Call<T> call) {
        try {
            Response<T> response = call.clone().execute();
            return extract(response);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

    /**
     * Extract net response
     *
     * @param response Original response
     * @return Resolved response
     * @throws IOException
     */
    public static <T> T extract(Response<T> response) {
        if (response.isSuccessful()) {
            return response.body();
        }

        throw new RuntimeException(response.toString());
    }

    /**
     * Extract error message
     *
     * @param responseBody Original response body
     * @return Error message
     */
    public static String readErrorMessage(ResponseBody responseBody) {
        try {
            return responseBody.string();
        } catch (IOException e) {
            log.error("Fail to read error message.", e);
            return "";
        } finally {
            responseBody.close();
        }
    }
}
