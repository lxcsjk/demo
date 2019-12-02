package com.betterlxc.retrofit.client;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import java.util.Map;

/**
 * @author liuxincheng
 * @date 2019-05-21
 */
public interface TestClient {

    /**
     * 获取菜单
     *
     * @param storeId
     * @return
     */
    @GET("Goods/GetDishes")
    Call<JSONObject> getDishes(@Header("Authorization") String auth,
                               @Query("storeid") Integer storeId);


    /**
     * 获取台位信息
     *
     * @param map
     * @return
     */
    @GET("Store/GetDesk")
    Call<JSONObject> getDesk(@Header("Authorization") String auth,
                             @QueryMap Map<String, Object> map);

    /**
     * 获取token
     *
     * @param map
     * @return
     */
    @Headers({
        "Content-Type:application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("token")
    Call<JSONObject> token(@QueryMap Map<String, String> maps, @FieldMap Map<String, String> map);

    /**
     * 获取token
     *
     * @param map
     * @return
     */
    @Headers({
        "Content-Type:application/json"
    })
    @POST("token")
    Call<JSONObject> tokenJson(@Body Map<String, String> map);


    /**
     * 上传单文件
     *
     * @param description
     * @param file
     * @return
     */
    @Multipart
    @POST("upload")
    Call<Response<String>> upload(@Part("description") String description,
                                  @Part MultipartBody.Part file);


    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String fileUrl);


    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST
    Call<ResponseBody> doFormPost(@Url String url, @FieldMap @NonNull Map<String, String> param);

}
