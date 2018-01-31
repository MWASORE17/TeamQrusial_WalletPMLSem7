package com.wenlong.qianbao.base;

import java.util.ArrayList;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class BaseCallback<T> {
    private Boolean status;
    private String message;
    private T data;
    private ArrayList<T> data_array;

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ArrayList<T> getData_array() {
        return data_array;
    }
}
