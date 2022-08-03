package home.bruce.RetrofitTest;

import home.bruce.RetrofitTest.service.MyService;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.jooq.lambda.fi.util.function.CheckedConsumer.unchecked;

@SpringBootTest
public class RetrofitTest1Get {
    @Autowired
    private MyService myService;

    @Test
    public void test1() throws IOException {
        Call<String> call = myService.t1Get("7788");
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body); // GET 時，body 是 null

        // execute 為同步調用
        Response<String> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(System.out::println);
    }

    @Test
    public void test1_1() throws IOException {
        Call<ResponseBody> call = myService.t2Get("7788");
        System.out.println(call.request().method());

        // enqueue 為異步調用
        call.enqueue(new Callback<ResponseBody>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Optional.ofNullable(response.body()).ifPresent(
                        unchecked(rb -> System.out.println("onResponse=" + rb.string())));
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                System.out.println("onFailure=" + throwable.getMessage());
            }
        });


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException {
        Call<ResponseBody> call = myService.t2Get("5566");
        System.out.println(call.request().method());
        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test3() throws IOException {
        Call<String> call = myService.t3Get("monkey");
        System.out.println(call.request().method());

        // execute 為同步調用
        Response<String> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(System.out::println);
    }

    @Test
    public void test4() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("key1", "ooo");
                put("key2", "xxx");
            }
        };
        Call<ResponseBody> call = myService.t4Get(map);
        System.out.println(call.request().method());

        // execute 為同步調用
        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test5() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("key1", "ooo");
                put("key2", "xxx");
            }
        };
        Call<ResponseBody> call = myService.t5Get("http://127.0.0.1:8088/getUri/", map);
        System.out.println(call.request().method());
        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test6() {
        // 如果 Unable to create call adapter for io.reactivex.Observable<java.lang.String>
        // 設定檔要增加 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        Observable<String> observable = myService.t6Get("7788");
        Disposable disposable = observable.subscribe(System.out::println);
        if (!disposable.isDisposed()) disposable.dispose();
    }
}
