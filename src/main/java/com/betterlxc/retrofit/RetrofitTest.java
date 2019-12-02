package com.betterlxc.retrofit;

import com.betterlxc.retrofit.client.TestClient;
import com.betterlxc.retrofit.converter.ToStringConverterFactory;
import com.betterlxc.retrofit.interceptor.HttpLoggingInterceptor;
import com.betterlxc.retrofit.utils.HttpUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2019-05-21
 */
@Slf4j
public class RetrofitTest {

    private static final String BASE_URL = "http://betterlxc.com/images/";
    private static final Cache<String, String> TOKEN_CACHE = CacheBuilder.newBuilder()
        .expireAfterWrite(3000, TimeUnit.SECONDS)
        .maximumSize(1000)
        .recordStats() // 统计功能
        .removalListener(notification -> log.info("Remove a map entry which key is {},value is {},cause is {}.\n", notification.getKey(),
            notification.getValue(), notification.getCause().name()))
        .build();
    private TestClient testClient;

    @Before
    public void build() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("demo");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build();


        Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ToStringConverterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create());

        testClient = builder.build().create(TestClient.class);
    }

    @Test
    public void test1() throws ExecutionException {
//        String token = "Bearer " + TOKEN_CACHE.get("token", () -> {
//            JSONObject jsonObject = HttpUtils.execute(testClient.token(ImmutableMap.of(
//                "grant_type", "client_credentials",
//                "client_id", "Tasty",
//                "client_secret", "")
//            ));
//
//            return jsonObject.getString("access_token");
//        });

//        HttpUtils.execute(testClient.getDishes(token, 10001));
    }


    @Test
    public void test2() throws ExecutionException {
//        String token = "Bearer " + TOKEN_CACHE.get("token", () -> {
//            JSONObject jsonObject = HttpUtils.execute(testClient.token(ImmutableMap.of(
//                "grant_type", "client_credentials",
//                "client_id", "Tasty",
//                "client_secret", "")
//            ));
//
//            return jsonObject.getString("access_token");
//        });
//
//        HttpUtils.execute(testClient.getDesk(token, ImmutableMap.of("storeid", "10001", "deskname", "12")
//        ));
    }


    @Test
    public void test3() {


        HttpUtils.execute(testClient.token(ImmutableMap.of(
            "grant_type", "client_credentials",
            "client_id", "Tasty",
            "client_secret", ""), ImmutableMap.of(
            "grant_type", "client_credentials",
            "client_id", "Tasty",
            "client_secret", "")
        ));
    }

    @Test
    public void test4() {
        HttpUtils.execute(testClient.tokenJson(ImmutableMap.of(
            "grant_type", "client_credentials",
            "client_id", "Tasty",
            "client_secret", "")
        ));
    }

    @Test
    public void test5() {
        File image = new File("/Users/lxc/Desktop/WX20181217-144311.png");

        RequestBody requestFile =
            RequestBody.create(MediaType.parse("image/png"), image);
        MultipartBody.Part body =
            MultipartBody.Part.createFormData("img", image.getName(), requestFile);

        HttpUtils.execute(testClient.upload("test", body));
    }

    @Test
    public void test6() {
        ResponseBody responseBody = HttpUtils.execute(testClient.download("http://betterlxc.com/images/avatar.jpg"));
        System.out.println(responseBody);
    }

    @Test
    public void test7() {
        ResponseBody responseBody = HttpUtils.execute(testClient.doFormPost("http://betterlxc.com/images/", ImmutableMap.of(
            "grant_type", "client_credentials",
            "client_id", "Tasty",
            "client_secret", "")
        ));
    }
}
