package com.zidian.teacher.util;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

/**
 * Created by GongCheng on 2017/3/20.
 */

public class SharedPreferencesUtils {
    /**
     * Preference Key：用户名
     */
    private static final String SP_USERNAME = "username";
    /**
     * Preference Key：密码
     */
    private static final String SP_PASSWORD = "password";

    /**
     * Preference Key: token
     */
    private static final String SP_TOKEN = "token";
    /**
     * Preference Key: roleId
     */
    private static final String SP_ROLE_ID = "roleId";
    /**
     * Preference Key:colleges
     */
    private static final String SP_COLLEGES = "colleges";
    /**
     * Preference Key:isLogin
     */
    private static final String SP_IS_LOGIN = "isLogin";

    private static final String SP_SCHOOL_ID = "schoolId";

    private static final String SP_VERSION_NAME = "versionName";

    private static final String SP_CURRENT_WEEK = "currentWeek";

    private static final String SP_TEACHER_TYPE = "teacherType";

    private static final String SP_TEACHER_NAME = "teacherName";

    public static void init(Context context) {
        Hawk.init(context).build();
    }

    public static void setUsername(String username) {
        Hawk.put(SP_USERNAME, username);
    }

    public static String getUserName() {
        return Hawk.get(SP_USERNAME);
    }

    public static void setTeacherName(String teacherName) {
        Hawk.put(SP_TEACHER_NAME, teacherName);
    }

    public static String getTeacherName() {
        return Hawk.get(SP_TEACHER_NAME);
    }

    public static void setPassword(String password) {
        Hawk.put(SP_PASSWORD, password);
    }

    public static String getPassword() {
        return Hawk.get(SP_PASSWORD);
    }

    public static void setToken(String token) {
        Hawk.put(SP_TOKEN, token);
    }

    public static String getToken() {
        return Hawk.get(SP_TOKEN);
    }

    public static String getSchoolId() {
        return Hawk.get(SP_SCHOOL_ID);
    }

    public static void  setSchoolId(String schoolId) {
        Hawk.put(SP_SCHOOL_ID, schoolId);
    }

    public static String getVersionName() {
        return Hawk.get(SP_VERSION_NAME);
    }

    public static void setVersionName(String versionName) {
        Hawk.put(SP_VERSION_NAME, versionName);
    }

    public static void setCurrentWeek(int currentWeek) {
        Hawk.put(SP_CURRENT_WEEK, currentWeek);
    }

    public static int getCurrentWeek() {
        return Hawk.get(SP_CURRENT_WEEK, 0);
    }

    public static void setIsLogin(boolean isLogin) {
        Hawk.put(SP_IS_LOGIN, isLogin);
    }

    public static boolean getIsLogin() {
        return Hawk.get(SP_IS_LOGIN, false);
    }

    public static void setTeacherType(int teacherType) {
        Hawk.put(SP_TEACHER_TYPE, teacherType);
    }

    public static int getTeacherType() {
        return Hawk.get(SP_TEACHER_TYPE);
    }

    public static void clearAll() {
        String username = getUserName();
        String versionName = getVersionName();
        Hawk.deleteAll();
        setUsername(username);
        setVersionName(versionName);
    }
}
