package home.bruce.RetrofitTest.config;

import home.bruce.RetrofitTest.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Bean
    public MyService myService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:8080/") // 最後要有/，錯誤訊息說一定要加
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 整合 RxJava 時使用
                .build();
        return retrofit.create(MyService.class);
    }
}
