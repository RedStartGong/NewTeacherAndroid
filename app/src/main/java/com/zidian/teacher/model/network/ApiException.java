package com.zidian.teacher.model.network;

/**
 * Created by GongCheng on 2017/3/30.
 */

public class ApiException extends RuntimeException {
    public ApiException(String msg) {
        super(msg);
    }
}
