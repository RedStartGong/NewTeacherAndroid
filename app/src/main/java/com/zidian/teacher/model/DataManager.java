package com.zidian.teacher.model;


import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.Questionnaire;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.model.network.TeacherService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * DataManager for application
 * Created by GongCheng on 2017/3/14.
 */
@Singleton
public final class DataManager {
    private TeacherService service;

    @Inject
    public DataManager(TeacherService service) {
        this.service = service;
    }


    public Observable<HttpResult<List<School>>> getSchools() {
        return service.getSchools();
    }

    public Observable<LoginResult> login(String username, String password, String schoolId) {
        return service.login(username, password, schoolId);
    }

    public Observable<HttpResult<Questionnaire>> getQuestionnaire(
            String startRow, String pageSize, String teacherId, String token, String schoolId) {
        return service.getQuestionnaire(startRow, pageSize, teacherId, token, schoolId);
    }

}
