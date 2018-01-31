package com.wenlong.qianbao.base;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public interface BaseInterface {
    <T> void onSuccess(BaseCallback<T> baseGenericCallback);
    void onError(ApiErrorCallback apiErrorCallback);

}
