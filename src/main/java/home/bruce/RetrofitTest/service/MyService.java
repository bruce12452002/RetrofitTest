package home.bruce.RetrofitTest.service;

import home.bruce.RetrofitTest.entity.Animal;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface MyService {
    // ======================================= GET =======================================
    @GET("/t1Get/{name}")
    Call<String> t1Get(@Path("name") String str);

    @GET("/t2Get/{name}")
    Call<ResponseBody> t2Get(@Path("name") String str);

    @GET("/t3Get")
    Call<String> t3Get(@Query("name") String str);

    /**
     * Use JsonReader.setLenient(true) to accept malformed JSON
     * Call<String> 報了上面的錯
     * 本來回傳 String，而controller 回傳有成功，但 gson 解析失敗
     * 改用 ResponseBody
     */
    @GET("/t4Get")
    Call<ResponseBody> t4Get(@QueryMap Map<String, String> map);

    /**
     * 有時候不想用原本的網址時，可用 @Url
     * 使用 @Url 時，@GET 和 @POST 後面不可以有任何參數
     *
     * @Url 要全路徑，如果不是全路徑，會是拼接的路徑
     */
    @GET
    Call<ResponseBody> t5Get(@Url String url, @QueryMap Map<String, String> map);

    @GET("/t6Get/{name}")
    Observable<String> t6Get(@Path("name") String str);

    // ======================================= POST =======================================
    @POST("/t11Post")
    @FormUrlEncoded
    // 使用 @Field 一定要加，錯誤訊息很明顯
    Call<ResponseBody> t11Post(@Field("name") String str);

    @POST("/t12Post/{address}")
    Call<ResponseBody> t12Post(@Path("address") String str);

    @POST("/t13Post")
    @FormUrlEncoded
        // 使用 @FieldMap 一定要加，錯誤訊息很明顯
    Call<ResponseBody> t13Post(@FieldMap Map<String, String> map);

    @POST("/t14Post")
    Call<ResponseBody> t14Post(@Body Animal animal);

    @POST("/t15Post")
    Call<ResponseBody> t15Post(@Body List<Animal> animals);

    @POST
    Call<ResponseBody> t16Post(@Url String url, @Body Animal animal);

    @POST("/t17Post")
    @FormUrlEncoded
        // 使用 @Field 一定要加，錯誤訊息很明顯
    Observable<ResponseBody> t17Post(@Field("name") String str);
}
