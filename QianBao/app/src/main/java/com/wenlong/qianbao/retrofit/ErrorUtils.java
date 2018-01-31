package com.wenlong.qianbao.retrofit;


import com.wenlong.qianbao.base.ApiErrorCallback;

import retrofit2.Response;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class ErrorUtils {
    private static ApiErrorCallback error;
    public static ApiErrorCallback parseError(Response<?> response) {


        if(response.code()==404){
            error =  new ApiErrorCallback();
            error.setError("Error: Not Found");
            return error;
        }
        else if(response.code()==500){
            error =  new ApiErrorCallback();
            error.setError("Error: Internal Server Error");
            return error;
        }
        else if(response.code()==403){
            error =  new ApiErrorCallback();
            error.setError("Error: Forbidden");
            return error;
        }
        else {
            error = new ApiErrorCallback();
            error.setError("Error "+response.code()+" : There is problem with server");
            return error;
        }
    }

}
