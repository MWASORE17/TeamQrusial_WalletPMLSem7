package com.wenlong.qianbao.models.Auth;

import com.wenlong.qianbao.models.Transaction.HistoryData;

import java.util.ArrayList;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class AuthModel {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String remember_token;
    private String created_at;
    private String updated_at;
    public static ArrayList<AuthModel> authModelArrayList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}