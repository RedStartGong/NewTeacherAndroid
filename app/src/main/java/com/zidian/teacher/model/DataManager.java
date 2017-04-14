package com.zidian.teacher.model;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.model.entity.remote.Questionnaire;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.model.network.TeacherService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
            String teacherId, String teacherType, String token, String schoolId) {
        return service.getPersonInfo(teacherId, teacherType, token, schoolId);
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
        return service.getAttendanceStudents(courseWeeklyId, courseId, className, teacherId,
                token, schoolId);
    }

    public Observable<NoDataResult> setAttendance(
            String student, String courseId, String courseWeeklyId, String teacherId,
            String token, String schoolId) {
        return service.setAttendance(student, courseId, courseWeeklyId, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistics(
            String courseId, String className, String teacherId, String token, String schoolId) {
        return service.getAttendanceStatistics(courseId, className, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> feedback(String feedbackId, String feedbackInformation,
                                             String type, String token, String schoolId) {
        return service.feedback(feedbackId, feedbackInformation, type, token, schoolId);
    }

    public Observable<NoDataResult> setPortrait(RequestBody teacherId, RequestBody token,
                                                RequestBody schoolId, MultipartBody.Part file) {
        return service.setPortrait(teacherId, token, schoolId, file);
    }

    public Observable<NoDataResult> setPersonInfo(
            String motto, String phoneNumber, String teacherSex,
            String birthday, String nickName, String teacherId, String token, String schoolId) {
        return service.setPersonInfo(motto, phoneNumber, teacherSex, birthday, nickName, teacherId,
                token, schoolId);
    }

    public Observable<HttpResult<List<MyTask>>> getMyTasks(
            String requestState, String teacherId, String token, String schoolId) {
        return service.getMyTasks(requestState, teacherId, token, schoolId);
    }

    public Observable<InviteCourseResult> getInviteCourses(String teacherId, String token, String schoolId) {
        return service.getInviteCourses(teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<InviteTeacher>>> getInviteTeachers(String condition, String teacherId, String token, String schoolId) {
        return service.getInviteTeacher(condition, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> inviteOrApply(
            String teacherId, String teacherName, String requestedPerson, String requestType,
            String teacherCollege, String courseId, String courseName, String teachingCalendar,
            String classroom, String requestExplain, String token, String schoolId) {
        return service.inviteOrApply(teacherId, teacherName, requestedPerson, requestType, teacherCollege,
                courseId, courseName, teachingCalendar, classroom, requestExplain, token, schoolId);
    }
}
