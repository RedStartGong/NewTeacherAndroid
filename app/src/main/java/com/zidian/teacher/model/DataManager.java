package com.zidian.teacher.model;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.entity.remote.PersonInfo;
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

    public Observable<HttpResult<PersonInfo>> getPersonInfo(
            String teacherId, String token, String schoolId) {
        return service.getPersonInfo(teacherId, token, schoolId);
    }

    public Observable<NoDataResult> changePassword(
            String teacherId, String password, String password1, String password2,
            String token, String schoolId) {
        return service.changePassword(teacherId, password, password1, password2, token, schoolId);
    }

    public Observable<HttpResult<List<Course>>> getCourses(
            String teacherId, String token, String schoolId) {
        return service.getCourses(teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<Class>>> getClasses(
            String courseId, String teacherId, String token, String schoolId) {
        return service.getClasses(courseId, teacherId, token, schoolId);
    }

    public Observable<AttendanceStudent> getAttendanceStudent(
            String courseWeeklyId, String courseId, String className, String teacherId,
            String token, String schoolId) {
        return service.getAttendanceStudents(courseWeeklyId, courseId, className, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> setAttendance(
            String student, String courseId, String courseWeeklyId, String teacherId,
            String token, String schoolId) {
        return service.setAttendance(student, courseId, courseWeeklyId, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistcs(
            String courseId, String className, String teacherId, String token, String schoolId) {
        return service.getAttendanceStatistcs(courseId, className, teacherId, token, schoolId);
    }
}
