package com.zidian.teacher.model;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
import com.zidian.teacher.model.entity.remote.EvaTeacher;
import com.zidian.teacher.model.entity.remote.StudentClass;
import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.model.entity.remote.Course;
import com.zidian.teacher.model.entity.remote.CourseTime;
import com.zidian.teacher.model.entity.remote.CustomEva;
import com.zidian.teacher.model.entity.remote.EvaTwoIndex;
import com.zidian.teacher.model.entity.remote.EvaluateCourse;
import com.zidian.teacher.model.entity.remote.EvaluateTag;
import com.zidian.teacher.model.entity.remote.HttpResult;
import com.zidian.teacher.model.entity.remote.InviteCourseResult;
import com.zidian.teacher.model.entity.remote.InviteTeacher;
import com.zidian.teacher.model.entity.remote.LoginResult;
import com.zidian.teacher.model.entity.remote.MyQuesDetail;
import com.zidian.teacher.model.entity.remote.MyQuesList;
import com.zidian.teacher.model.entity.remote.MyTask;
import com.zidian.teacher.model.entity.remote.NoDataResult;
import com.zidian.teacher.model.entity.remote.PersonInfo;
import com.zidian.teacher.model.entity.remote.QuesSurveyDetail;
import com.zidian.teacher.model.entity.remote.QuesSurveyList;
import com.zidian.teacher.model.entity.remote.School;
import com.zidian.teacher.model.entity.remote.SelectClass;
import com.zidian.teacher.model.entity.remote.StudentEva;
import com.zidian.teacher.model.network.TeacherService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    /**
     * 登录
     */
    public Observable<LoginResult> login(String username, String password, int schoolId) {
        return service.login(username, password, schoolId);
    }

    /**
     * 获取个人信息
     */
    // TODO: 2017/8/23 尚未完成
    public Observable<HttpResult<PersonInfo>> getPersonInfo(
            String teacherId, String teacherType, String token, int schoolId) {
        return service.getPersonInfo(teacherType);
    }

    /**
     * 修改密码
     */
    public Observable<NoDataResult> changePassword(
            int teacherId, String password, String password1, String password2) {
        return service.changePassword(teacherId, password, password1, password2);
    }

    /**
     * 按周次获取课程表
     */
    public Observable<HttpResult<List<Course>>> getCourses(
            int teacherId, int week) {
        return service.getCourses(teacherId, week);
    }

    /**
     * 获取课程时间
     */
    public Observable<HttpResult<CourseTime>> getCourseTime(int teacherId, int week) {
        return service.getCourseTime(teacherId, week);
    }

    /**
     * 获取班级
     */
    public Observable<HttpResult<List<StudentClass>>> getClasses(int teacherId, int courseId) {
        return service.getClasses(teacherId, courseId);
    }

    /**
     * 本节考勤
     */
    public Observable<HttpResult<List<AttendanceStudent>>> getAttendanceStudent(
            int teacherId, int courseId, int coursePlanId, String className) {
        return service.getAttendanceStudents(teacherId, courseId, coursePlanId, className);
    }

    /**
     * 设置考勤信息
     */
    public Observable<NoDataResult> setAttendance(
            String student, int courseId, int coursePlanId, int teacherId) {
        return service.setAttendance(student, courseId, coursePlanId, teacherId);
    }

    /**
     * 查看考勤统计
     */
    public Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistics(
            int courseId, String className, int teacherId) {
        return service.getAttendanceStatistics(courseId, className, teacherId);
    }

    public Observable<NoDataResult> feedback(String feedbackId, String feedbackInformation,
                                             String type, String token, int schoolId) {
        return service.feedback(feedbackId, feedbackInformation, type, token, schoolId);
    }

    /**
     * 修改个人信息(带头像)
     */
    public Observable<NoDataResult> changeUserInfo(
            RequestBody teacherId, RequestBody aliasName, RequestBody phone,
            RequestBody signName, RequestBody birthday, RequestBody sex,
            MultipartBody.Part iconUrl) {
        return service.changeUserInfo(teacherId, aliasName, phone, signName, birthday, sex, iconUrl);
    }

    /**
     * 获取学院列表
     */
    public Observable<HttpResult<List<College>>> getColleges(int teacherId) {
        return service.getColleges(teacherId);
    }

    /**
     * 获取一个学院的教师
     */
    public Observable<HttpResult<List<EvaTeacher>>> getTeachers(int teacherId, int collegeId) {
        return service.getTeachers(teacherId, collegeId);
    }

    /**
     * 获取某个教师所教的课程
     */
    public Observable<HttpResult<List<EvaCourse>>> getEvaCourses(
            int teacherId, int evaTeacherId) {
        return service.getEvaCourses(teacherId, evaTeacherId);
    }

    /**
     * 通过老师跟课程获取课堂
     */
    public Observable<HttpResult<List<CoursePlan>>> getCoursePlans(
            int teacherId, int evaTeacherId, int courseId) {
        return service.getCoursePlans(teacherId, evaTeacherId, courseId);
    }

    /**
     * 查看我的任务
     */
    public Observable<HttpResult<List<MyTask>>> getMyTasks(int requestState, int teacherId) {
        return service.getMyTasks(requestState, teacherId);
    }


    /**
     * 邀请或者申请评价别人
     */
    public Observable<NoDataResult> inviteOrApply(int teacherId, String toTeacherId,
                                                  int requestType, String requestMessage,
                                                  int evaluateType, int coursePlanId) {
        return service.inviteOrApply(teacherId, toTeacherId, requestType,
                requestMessage, evaluateType, coursePlanId);
    }

    /**
     * 获取评价的标签/查看同行或者督导评价的结果
     */
    public Observable<HttpResult<EvaluateTag>> getEvaluateTag(
            int requestEvalMessageId, int teacherId) {
        return service.getEvaluateTag(requestEvalMessageId, teacherId);

    }

    /**
     * 进行同行/督导评价
     */
    public Observable<NoDataResult> evaluate(int requestEvalMessageId, int toTeacherId,
                                             int evaluateType, String evaluateContent,
                                             String customEva, int teacherId) {
        return service.evaluate(requestEvalMessageId, toTeacherId, evaluateType,
                evaluateContent, customEva, teacherId);
    }

    /**
     * 修改我的任务-评价状态
     */
    public Observable<NoDataResult> changeEvaState(
            int requestEvalMessageId, int requestState, int teacherId) {
        return service.changeEvaState(requestEvalMessageId, requestState, teacherId);
    }

    /**
     * 添加督导评价反馈，dissentDesc 为空时表示确认
     */
    public Observable<NoDataResult> supervisorFeedback(int requestEvalMessageId, int toState,
                                                       String dissentDesc, int teacherId) {
        return service.supervisorFeedback(requestEvalMessageId, toState, dissentDesc, teacherId);
    }

    public Observable<HttpResult<StudentEva>> studentEva(int teacherId) {
        return service.studentEva(teacherId);
    }

    public Observable<HttpResult<List<EvaTwoIndex>>> studentEvaTwoIndex(
            int indexOneId, int teacherId) {
        return service.studentEvaTwoIndex(indexOneId, teacherId);
    }

    /**
     * 查看同行评价统计
     */
    public Observable<HttpResult<List<ColleagueEva>>> colleagueEva(int teacherId) {
        return service.colleagueEva(teacherId);
    }

    /**
     * 查看督导评价统计
     */
    public Observable<HttpResult<List<ColleagueEva>>> supervisorEva(int teacherId) {
        return service.supervisorEva(teacherId);
    }


    public Observable<HttpResult<List<CustomEva>>> customEva(
            int startRow, int pageSize, int teacherId) {
        return service.customEva(startRow, pageSize, teacherId);
    }

    public Observable<HttpResult<QuesSurveyList>> quesSurveyList(
            String startRow, String pageSize, String teacherId, String token,
            int schoolId) {
        return service.quesSurveyList(startRow, pageSize, teacherId, token, schoolId);
    }

    public Observable<HttpResult<QuesSurveyDetail>> quesSurveyDetail(
            String questionnaireId, String teacherId, String token, int schoolId) {
        return service.quesSurveyDetail(questionnaireId, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> quesSubmit(
            String questionnaireSubmit, String teacherId, String token, int schoolId) {
        return service.quesSubmit(questionnaireSubmit, teacherId, token, schoolId);
    }

    public Observable<HttpResult<MyQuesList>> myQuesList(
            String startRow, String pageSize, String teacherId, String token,
            int schoolId) {
        return service.myQuesList(startRow, pageSize, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<MyQuesDetail>>> myQuesDetail(
            String questionnaireId, String teacherId, String token, int schoolId) {
        return service.myQuesDetail(questionnaireId, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> addQuestionnaire(
            String questionnaire, String teacherId, String token, int schoolId) {
        return service.addQuestionnaire(questionnaire, teacherId, token, schoolId);
    }

    public Observable<SelectClass> getAllClasses(
            String teacherId, String token, int schoolId) {
        return service.getAllClasses(teacherId, token, schoolId);
    }


}
