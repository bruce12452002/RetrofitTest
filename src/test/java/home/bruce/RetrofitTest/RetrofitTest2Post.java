package home.bruce.RetrofitTest;

import home.bruce.RetrofitTest.entity.Animal;
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
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.jooq.lambda.fi.util.function.CheckedConsumer.unchecked;

@SpringBootTest
public class RetrofitTest2Post {
    @Autowired
    private MyService myService;

    @Test
    public void test11() throws IOException {
        Call<ResponseBody> call = myService.t11Post("monkey");
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body);

        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test12() throws IOException {
        Call<ResponseBody> call = myService.t12Post("7788");
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body);

        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test13() throws IOException {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("key1", "ooo");
                put("key2", "xxx");
            }
        };
        Call<ResponseBody> call = myService.t13Post(map);
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body);

        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test14() throws IOException {
        Call<ResponseBody> call = myService.t14Post(new Animal(1, "cat"));
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body);

        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test15() throws IOException {
        Call<ResponseBody> call = myService.t15Post(Arrays.asList(
                new Animal(1, "tom"), new Animal(2, "jerry")));
        System.out.println(call.request().method());
        Request request = call.request();
        RequestBody body = request.body();
        System.out.println("body=" + body);

        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(
                unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test16() throws IOException {
        Call<ResponseBody> call = myService.t16Post(
                "http://127.0.0.1:8088/postUri/", new Animal(888, "tiger"));
        System.out.println(call.request().method());
        Response<ResponseBody> response = call.execute();
        Optional.ofNullable(response.body()).ifPresent(unchecked(rb -> System.out.println(rb.string())));
    }

    @Test
    public void test17() {
        Observable<ResponseBody> observable = myService.t17Post("monkey");
        Disposable disposable = observable.subscribe(c -> System.out.println(c.string()));
        if (!disposable.isDisposed()) disposable.dispose();
    }
}
