package com.wenlong.qianbao.controllers;

import android.content.Context;
import android.view.View;

import com.squareup.otto.Produce;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseController;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.models.Transaction.HistoryData;
import com.wenlong.qianbao.models.Transaction.Kategori;
import com.wenlong.qianbao.retrofit.ApiInterface;
import com.wenlong.qianbao.retrofit.ErrorUtils;
import com.wenlong.qianbao.retrofit.RestClient;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class AuthController extends BaseController {
    private static AuthController _instance;

    protected AuthController(Context paramContext) {
        super(paramContext);
    }

    public static AuthController getInstance(Context paramContext)
    {
        if (_instance == null) {
            _instance = new AuthController(paramContext);
        }
        return _instance;
    }

    @Produce
    public void postRegister(final BaseInterface baseInterface, String token, Map<String,String> map){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<AuthModel>> call = service.postRegister(map);
        call.enqueue(new Callback<BaseCallback<AuthModel>>() {

            @Override
            public void onResponse(Call<BaseCallback<AuthModel>> call, Response<BaseCallback<AuthModel>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<AuthModel> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);

                }
            }

            @Override
            public void onFailure(Call<BaseCallback<AuthModel>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void postLogin(final BaseInterface baseInterface, String token, Map<String,String> map){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<AuthModel>> call = service.postLogin(map);
        call.enqueue(new Callback<BaseCallback<AuthModel>>() {

            @Override
            public void onResponse(Call<BaseCallback<AuthModel>> call, Response<BaseCallback<AuthModel>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<AuthModel> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);

                }
            }

            @Override
            public void onFailure(Call<BaseCallback<AuthModel>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void getHistoryTransaksi(final BaseInterface baseInterface, String id_user){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.getHistoryTransaksi(id_user);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void getPersenItem(final BaseInterface baseInterface, String id_user){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<Kategori>> call = service.getPersenItem(id_user);
        call.enqueue(new Callback<BaseCallback<Kategori>>() {

            @Override
            public void onResponse(Call<BaseCallback<Kategori>> call, Response<BaseCallback<Kategori>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<Kategori> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<Kategori>> call, Throwable t) {

            }
        });
    }


    @Produce
    public void postTransaksi(Map<String, String> map){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.postTransaksi(map);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
//                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);

                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {
            }
        });
    }

    @Produce
    public void getTotalBalance(final BaseInterface baseInterface, String id_user){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<Integer>> call = service.getTotalBalance(id_user);
        call.enqueue(new Callback<BaseCallback<Integer>>() {

            @Override
            public void onResponse(Call<BaseCallback<Integer>> call, Response<BaseCallback<Integer>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<Integer> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<Integer>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void getExpense(final BaseInterface baseInterface, String id_user){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<Integer>> call = service.getExpense(id_user);
        call.enqueue(new Callback<BaseCallback<Integer>>() {

            @Override
            public void onResponse(Call<BaseCallback<Integer>> call, Response<BaseCallback<Integer>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<Integer> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<Integer>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void getUsername(final BaseInterface baseInterface, String id){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<AuthModel>> call = service.getUsername(id);
        call.enqueue(new Callback<BaseCallback<AuthModel>>() {

            @Override
            public void onResponse(Call<BaseCallback<AuthModel>> call, Response<BaseCallback<AuthModel>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<AuthModel> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<AuthModel>> call, Throwable t) {

            }
        });
    }

    @Produce
    public void getTotalKategoriFoodsAndDrinks(final BaseInterface baseInterface, String id){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.getTotalKategoriFoodsAndDrinks(id);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {

            }
        });
    }
    @Produce
    public void getTotalKategoriTraveling(final BaseInterface baseInterface, String id){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.getKategoriOthers(id);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {

            }
        });
    }
    @Produce
    public void getTotalKategoriShopping(final BaseInterface baseInterface, String id){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.getKategoriOthers(id);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {

            }
        });
    }
    @Produce
    public void getTotalKategoriTransport(final BaseInterface baseInterface, String id){
        ApiInterface service = RestClient.getClient("");
        Call<BaseCallback<HistoryData>> call = service.getKategoriOthers(id);
        call.enqueue(new Callback<BaseCallback<HistoryData>>() {

            @Override
            public void onResponse(Call<BaseCallback<HistoryData>> call, Response<BaseCallback<HistoryData>> response) {
                if (response.isSuccessful()) {
                    BaseCallback<HistoryData> data = response.body();
                    baseInterface.onSuccess(data);
                } else {
                    ApiErrorCallback error = ErrorUtils.parseError(response);
                }
            }

            @Override
            public void onFailure(Call<BaseCallback<HistoryData>> call, Throwable t) {

            }
        });
    }
}