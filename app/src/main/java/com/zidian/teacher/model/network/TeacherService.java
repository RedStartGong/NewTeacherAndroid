package com.zidian.teacher.model.network;


import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.model.entity.remote.Questionnaire;
import com.zidian.teacher.model.entity.remote.School;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * TeacherService
 * Created by GongCheng on 2017/3/16.
 */

public interface TeacherService {
    /**
     * get Schools
     */
    @POST("page/shiro/school")
    Observable<HttpResult<List<School>>> getSchools();

    @FormUrlEncoded
    @POST("page/shiro/loginTeacher")
    Observable<LoginResult> login(
            @Field("username") String username, @Field("password") String password,
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("page/stuCou/select")
    Observable<Object> getClassSchedule(
            @Field("studentId") String studentId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("Questionnaire/selectQuestionnaire")
    Observable<HttpResult<Questionnaire>> getQuestionnaire(
            @Field("startrow") String startRow, @Field("pageSize") String pageSize,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("Questionnaire/selectQuestionnaireStatistics")
    Observable<HttpResult<Object>> getQuestionnaireResult(
            @Field("questionnaireId") String questionnaireId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("teacher/selectMydata")
    Observable<HttpResult<PersonInfo>> getPersonInfo(
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("teacher/updatePassword")
    Observable<NoDataResult> changePassword(
            @Field("teacherId") String teacherId, @Field("password") String password,
            @Field("password1") String password1, @Field("password2") String password2,
            @Field("token") String token, @Field("schoolId")String schoolId
    );
    
    @FormUrlEncoded
    @POST("page/course/select")
    Observable<HttpResult<List<Course>>> getCourses(
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("page/attendance/selectClass")
    Observable<HttpResult<List<Class>>> getClasses(
            @Field("courseId") String courseId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("page/attendance/select")
    Observable<AttendanceStudent> getAttendanceStudents(
            @Field("courseWeeklyId") String courseWeeklyId, @Field("courseId") String courseId,
            @Field("className") String className, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    @FormUrlEncoded
    @POST("page/attendance/add")
    Observable<NoDataResult> setAttendance(
            @Field("student") String student, @Field("courseId") String courseId,
            @Field("courseWeeklyId") String courseWeeklyId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);
}
