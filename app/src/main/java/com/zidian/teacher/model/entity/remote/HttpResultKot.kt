package com.zidian.teacher.model.entity.remote

/**
 * Created by GongCheng on 2017/4/1.
 */
class HttpResultKot<T>(val code : Int, val message : String , val data : T)