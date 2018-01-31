package com.wenlong.qianbao.retrofit;

import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.models.Transaction.HistoryData;
import com.wenlong.qianbao.models.Transaction.Kategori;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    Call<BaseCallback<AuthModel>> postRegister(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("login")
    Call<BaseCallback<AuthModel>> postLogin(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("transaksi")
    Call<BaseCallback<HistoryData>> postTransaksi(@FieldMap Map<String,String> map);

    @GET("getTotalBalance")
    Call<BaseCallback<Integer>> getTotalBalance(@Query("id_user") String id_user);

    @GET("getExpense")
    Call<BaseCallback<Integer>> getExpense(@Query("id_user") String id_user);

    @GET("getUsername")
    Call<BaseCallback<AuthModel>> getUsername(@Query("id") String id);

    @GET("getHistoryTransaksi")
    Call<BaseCallback<HistoryData>> getHistoryTransaksi(@Query("id_user") String id_user);

    @GET("getPersenItem")
    Call<BaseCallback<Kategori>> getPersenItem(@Query("id_user") String id_user);

    //Value by category
    @GET("getKategoriOthers")
    Call<BaseCallback<HistoryData>> getKategoriOthers(@Query("id_user") String id_user);
    @GET("getTotalKategoriFoodsAndDrinks")
    Call<BaseCallback<HistoryData>> getTotalKategoriFoodsAndDrinks(@Query("id_user") String id_user);
    @GET("getTotalKategoriTraveling")
    Call<BaseCallback<HistoryData>> getTotalKategoriTraveling(@Query("id_user") String id_user);
    @GET("getTotalKategoriShopping")
    Call<BaseCallback<HistoryData>> getTotalKategoriShopping(@Query("id_user") String id_user);
    @GET("getTotalKategoriTransport")
    Call<BaseCallback<HistoryData>> getTotalKategoriTransport(@Query("id_user") String id_user);


}
