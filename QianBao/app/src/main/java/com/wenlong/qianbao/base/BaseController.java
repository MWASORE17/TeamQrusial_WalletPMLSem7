package com.wenlong.qianbao.base;

import android.content.Context;

/**
 * Created by Ferik Enedy on 12/13/2017.
 */

public class BaseController {
    private Context context;

    protected BaseController(Context paramContext)
    {
        this.context = paramContext;
    }

    public Context getContext()
    {
        return this.context;
    }
}
