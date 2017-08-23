package com.zidian.teacher.model.network;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
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

    /**
     * 教师登录
     */
    @FormUrlEncoded
    @POST("page/shiro/loginTeacher")
    Observable<LoginResult> login(
            @Field("username") String username, @Field("password") String password,
            @Field("schoolId") int schoolId);

    /**
     * 获取个人信息
     */
    @FormUrlEncoded
    @POST("teacher/selectMydata")
    Observable<HttpResult<PersonInfo>> getPersonInfo(@Field("teacherType") String teacherType
    );

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("teacher/updatePassword")
    Observable<NoDataResult> changePassword(
            @Field("teacherId") String teacherId, @Field("password") String password,
            @Field("password1") String password1, @Field("password2") String password2,
            @Field("token") String token, @Field("schoolId") int schoolId
    );

    /**
     * 获取课程表
     */
    @FormUrlEncoded
    @POST("page/courseL/teacherSelectCourseZ")
    Observable<HttpResult<List<Course>>> getCourses(@Field("teacherId") int teacherId, @Field("week") int week);

    /**
     * 查看课程时间
     */
    @FormUrlEncoded
    @POST("page/courseL/selectCourseT")
    Observable<HttpResult<CourseTime>> getCourseTime(
            @Field("teacherId") int teacherId, @Field("week") int week);
    /**
     * 得到本节课上课班级
     */
    @FormUrlEncoded
    @POST("attendanceRecordsL/selectClass")
    Observable<HttpResult<List<StudentClass>>> getClasses(@Field("teacherId") int teacherId, @Field("courseId") int courseId);

    /**
     * 查看本节考勤学生
     */
    @FormUrlEncoded
    @POST("attendanceRecordsL/select")
    Observable<HttpResult<List<AttendanceStudent>>> getAttendanceStudents(
            @Field("teacherId") int teacherId, @Field("courseId") int courseId,
            @Field("coursePlanId") int coursePlanId, @Field("className") String className);

    /**
     * 设置考勤信息
     */
    @FormUrlEncoded
    @POST("attendanceRecordsL/add")
    Observable<NoDataResult> setAttendance(
            @Field("student") String student, @Field("courseId") int courseId,
            @Field("coursePlanId") int coursePlanId, @Field("teacherId") int teacherId);

    /**
     * 考勤统计
     */
    @FormUrlEncoded
    @POST("attendanceRecordsL/selectAll")
    Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistics(
            @Field("courseId") int courseId, @Field("className") String className,
            @Field("teacherId") int teacherId);

    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("page/suggestionFeedback/add")
    Observable<NoDataResult> feedback(
            @Field("feedbackId") String feedbackId, @Field("feedbackInformation") String feedbackInformation,
            @Field("type") String type, @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 修改头像
     */
    @Multipart
    @POST("teacher/updateTeacherHeadPortrait")
    Observable<NoDataResult> setPortrait(@Part("teacherId") RequestBody teacherId,@Part("token") RequestBody token,
                                         @Part("schoolId") RequestBody schoolId, @Part MultipartBody.Part file);

    /**
     * 修改个人信息
     */
    @FormUrlEncoded
    @POST("teacher/updateMydata")
    Observable<NoDataResult> setPersonInfo(
            @Field("personalizedSignature") String motto, @Field("teacherphoneNumber")
            String phoneNumber, @Field("teacherSex") String teacherSex, @Field("mybirthd") String birthday,
            @Field("mynickname") String nickName, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 我的任务
     */
    @FormUrlEncoded
    @POST("ToEvaluateOthers/myAssignment")
    Observable<HttpResult<List<MyTask>>> getMyTasks(
            @Field("requestState") String requestState, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 获取邀请评价时的课程信息
     */
    @FormUrlEncoded
    @POST("page/evaluateBySupervisor/inviteSelectAll")
    Observable<InviteCourseResult> getInviteCourses(@Field("teacherId") String teacherId, @Field("token") String token,
                                                    @Field("schoolId") int schoolId);

    /**
     * 获取评价他人时的课程信息
     */
    @FormUrlEncoded
    @POST("page/evaluateBySupervisor/selectAll")
    Observable<HttpResult<List<EvaluateCourse>>> getEvaluateCourses(
            @Field("teacherId") String teacherId, @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 获取邀请评价的教师
     */
    @FormUrlEncoded
    @POST("page/evaluateBySupervisor/selectTeacher")
    Observable<HttpResult<List<InviteTeacher>>> getInviteTeacher(@Field("condition") String condition, @Field("teacherId")
            String teacherId, @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 邀请评价或申请评价他人
     */
    @FormUrlEncoded
    @POST("ToEvaluateOthers/invitationToEvaluate")
    Observable<NoDataResult> inviteOrApply(
            @Field("initiateTheRequestId") String teacherId, @Field("initiateTheRequestName") String teacherName,
            @Field("theRequestedPerson") String requestedPerson, @Field("requestType") String requestType,
            @Field("teacherCollege") String teacherCollege, @Field("courseId") int courseId,
            @Field("courseName") String courseName, @Field("teachingCalendar") String teachingCalendar,
            @Field("courseClassroom") String classroom, @Field("requestExplain") String requestExplain,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 添加督导评价
     */
    @FormUrlEncoded
    @POST("page/evaluateBySupervisor/add")
    Observable<NoDataResult> addSupervisorEva(
            @Field("initateTheRequestId") String teacherId, @Field("initateTheRequestName") String teacherName,
            @Field("theRequestedPersonCollege") String theRequestedPersonCollege,
            @Field("theRequestedPersonId") String requestedPersonId,
            @Field("theRequestedPersonName") String requestedPersonName, @Field("courseId") int courseId,
            @Field("courseName") String courseName, @Field("teachingCalendarTime") String teachingCalendar,
            @Field("courseClassroom") String classroom, @Field("teacherType") String teacherType,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 同行评价，查看标签
     */
    @FormUrlEncoded
    @POST("Label/selectAllLabelName")
    Observable<HttpResult<List<EvaluateTag>>> getEvaluateTags(
            @Field("packageName") String packageName, @Field("operatorId") String teacherId,
            @Field("operatorType") String operatorType, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 评价别人
     */
    @FormUrlEncoded
    @POST("ToEvaluateOthers/evaluationColleagues")
    Observable<NoDataResult> evaluate(
            @Field("evaluateType") String evaluateType, @Field("teacherType") String teacherType,
            @Field("byEvaluatePersonId") String evaluatedId, @Field("recordId") String recordId,
            @Field("evaluateLabel") String evaluateLabel, @Field("evaluateComment") String evaluateComment,
            @Field("colleagueId") String colleagueId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 修改评价状态
     */
    @FormUrlEncoded
    @POST("ToEvaluateOthers/updateRequestState")
    Observable<NoDataResult> changeEvaState(
            @Field("recordId") String recordId, @Field("requestState") String requestState,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 查看评价详情
     */
    @GET("ToEvaluateOthers/evaluationDetails")
    Observable<HttpResult<CheckColleagueEva>> checkColleagueEva(
            @Query("recordId") String recordId, @Query("teacherId") String teacherId,
            @Query("token") String token, @Query("schoolId") int schoolId);

    /**
     * 查看督导评价详情
     */
    @GET("page/evaluateBySupervisor/evaluationDetails")
    Observable<HttpResult<CheckSupervisorEva>> checkSupervisorEva(
            @Query("recordId") String recordId, @Query("teacherId") String teacherId,
            @Query("token") String token, @Query("schoolId") int schoolId);

    /**
     * 添加督导评价反馈，contentFeedback 为空时表示确认
     */
    @FormUrlEncoded
    @POST("page/evaluateBySupervisor/addFeedback")
    Observable<NoDataResult> supervisorFeedback(
            @Field("contentFeedback") String contentFeedback, @Field("recordId") String recordId,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 查看学生评价统计
     */
    @FormUrlEncoded
    @POST("EvaluateByStudent/studentEvaluation")
    Observable<HttpResult<StudentEva>> studentEva(
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 查看学生评价二级指标
     */
    @FormUrlEncoded
    @POST("EvaluateByStudent/studentEvaluationTwoIndex")
    Observable<HttpResult<List<EvaTwoIndex>>> studentEvaTwoIndex(
            @Field("indexName") String indexName, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 查看督导/同行评价
     */
    @FormUrlEncoded
    @POST("EvaluateTeacher/teacherEvaluation")
    Observable<HttpResult<List<ColleagueEva>>> colleagueEva(
            @Field("evaluateType") String evaluateType, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 查看督导/同行评价二级指标
     */
    @FormUrlEncoded
    @POST("EvaluateTeacher/teacherEvaluateTwoIndex")
    Observable<HttpResult<List<EvaTwoIndex>>> colleagueEvaTwoIndex(
            @Field("evaluateType") String evaluateType, @Field("indexName") String indexName,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 自定义评价
     */
    @FormUrlEncoded
    @POST("EvaluateTeacher/CustomEvaluation")
    Observable<HttpResult<CustomEva>> customEva(
            @Field("startrow") String startRow, @Field("pageSize") String pageSize,
            @Field("operatorId") String operatorId, @Field("operatorType") String operatorType,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 教师获取需要填写的问卷
     */
    @FormUrlEncoded
    @POST("QuestionnaireTea/selectTeaQues")
    Observable<HttpResult<QuesSurveyList>> quesSurveyList(
            @Field("startrow") String startRow, @Field("pageSize") String pageSize,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 教师查看需要填写问卷的详情
     */
    @FormUrlEncoded
    @POST("QuestionnaireTea/teaSelectQuestionnaireParticulars")
    Observable<HttpResult<QuesSurveyDetail>> quesSurveyDetail(
            @Field("questionnaireId") String questionnaireId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 教师提交问卷
     */
    @FormUrlEncoded
    @POST("QuestionnaireTea/teaInsertQuestionnaireSubmit")
    Observable<NoDataResult> quesSubmit(
            @Field("questionnaireSubmit") String questionnaireSubmit, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 查看指定教师发布的全部问卷
     */
    @FormUrlEncoded
    @POST("Questionnaire/selectQuestionnaire")
    Observable<HttpResult<MyQuesList>> myQuesList(
            @Field("startrow") String startRow, @Field("pageSize") String pageSize,
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 查看指定教师发布的问卷统计详情
     */
    @FormUrlEncoded
    @POST("Questionnaire/selectQuestionnaireStatistics")
    Observable<HttpResult<List<MyQuesDetail>>> myQuesDetail(
            @Field("questionnaireId") String questionnaireId, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);

    /**
     * 查看教师所教的所有班级
     */
    @FormUrlEncoded
    @POST("teacher/selectClassByTeacher")
    Observable<SelectClass> getAllClasses(
            @Field("teacherId") String teacherId, @Field("token") String token,
            @Field("schoolId") int schoolId);

    /**
     * 教师发布问卷
     */
    @FormUrlEncoded
    @POST("Questionnaire/teacherInsertQuestionnaire")
    Observable<NoDataResult> addQuestionnaire(
            @Field("questionnaire") String questionnaire, @Field("teacherId") String teacherId,
            @Field("token") String token, @Field("schoolId") int schoolId);
}
