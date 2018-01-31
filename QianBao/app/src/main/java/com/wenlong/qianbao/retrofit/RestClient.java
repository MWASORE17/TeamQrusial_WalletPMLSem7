package com.wenlong.qianbao.retrofit;

import android.util.Log;

import com.wenlong.qianbao.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class RestClient {
    private static String Base_url = Config.URL;
    private static ApiInterface gitApiInterface;
    private static String apitoken;

    //for jwt
    public static ApiInterface getOauth() {
        if (gitApiInterface == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .header("Accept", "application/json")
                                    .method(original.method(), original.body())
                                    .build();
                            Log.d("REQUESTTTT",""+request);
                            Response response = chain.proceed(request);
                            return response;
                        }
                    }).build();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(ApiInterface.class);
        }
        return gitApiInterface ;
    }

    //for JWT
    public static ApiInterface getClient(String token) {
        apitoken = token;
        if (gitApiInterface == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            String basic = "Bearer " + apitoken;
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("Content-Type", "application/x-www-form-urlencoded")
                                    .header("Accept", "application/json")
                                    .header("Authorization", basic)
                                    .method(original.method(), original.body())
                                    .build();
                            Log.d("REQUESTTTT",""+request);
                            Response response = chain.proceed(request);
                            return response;
                        }
                    }).build();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(ApiInterface.class);
        }
        return gitApiInterface ;
    }

}
