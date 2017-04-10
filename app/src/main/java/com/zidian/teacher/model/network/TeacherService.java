package com.zidian.teacher.model.network;


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
import com.zidian.teacher.util.SharedPreferencesUtils;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("teacher/updatePassword")
    Observable<NoDataResult> changePassword(
            @Field("teacherId") String teacherId, @Field("password") String password,
            @Field("password1") String password1, @Field("password2") String password2,
            @Field("token") String token, @Field("schoolId") String schoolId
    );

    /**
     * 获取课程表
     */
    @FormUrlEncoded
    @POST("page/course/select")
    Observable<HttpResult<List<Course>>> getCourses(
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    /**
     * 得到本节课上课班级
     */
    @FormUrlEncoded
    @POST("page/attendance/selectClass")
    Observable<HttpResult<List<Class>>> getClasses(
            @Field("courseId") String courseId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    /**
     * 查看本节考勤学生
     */
    @FormUrlEncoded
    @POST("page/attendance/select")
    Observable<AttendanceStudent> getAttendanceStudents(
            @Field("courseWeeklyId") String courseWeeklyId, @Field("courseId") String courseId,
            @Field("className") String className, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    /**
     * 设置考勤信息
     */
    @FormUrlEncoded
    @POST("page/attendance/add")
    Observable<NoDataResult> setAttendance(
            @Field("student") String student, @Field("courseId") String courseId,
            @Field("courseWeeklyId") String courseWeeklyId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") String schoolId);

    /**
     * 考勤统计
     */
    @FormUrlEncoded
    @POST("page/attendance/selectAll")
    Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistcs(
            @Field("courseId") String courseId, @Field("className") String className,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") String schoolId);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("page/suggestionFeedback/add")
    Observable<NoDataResult> feedback(
            @Field("feedbackId") String feedbackId, @Field("feedbackInformation") String feedbackInformation,
            @Field("type") String type, @Field("token") String token, @Field("schoolId") String schoolId);

    @Multipart
    @POST("teacher/updateTeacherHeadPortrait")
    Observable<NoDataResult> setPortrait(@Part("teacherId") RequestBody teacherId, @Part("token") RequestBody token,
                                         @Part("schoolId") RequestBody schoolId, @Part MultipartBody.Part file);
}
