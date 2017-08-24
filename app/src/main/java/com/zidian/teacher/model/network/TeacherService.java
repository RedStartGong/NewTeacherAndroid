package com.zidian.teacher.model.network;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.College;
import com.zidian.teacher.model.entity.remote.CoursePlan;
import com.zidian.teacher.model.entity.remote.EvaCourse;
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
import com.zidian.teacher.model.entity.remote.EvaTeacher;

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
    @POST("teacherAppL/updPassword")
    Observable<NoDataResult> changePassword(
            @Field("teacherId") int teacherId, @Field("password") String password,
            @Field("password1") String password1, @Field("password2") String password2);

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
/********************************************************************/
    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("page/suggestionFeedback/add")
    Observable<NoDataResult> feedback(
            @Field("feedbackId") String feedbackId, @Field("feedbackInformation") String feedbackInformation,
            @Field("type") String type, @Field("token") String token, @Field("schoolId") int schoolId);


    /**
     * 修改个人信息(带头像)
     */
    @Multipart
    @POST("page/studentApp/updateMe")
    Observable<NoDataResult> changeUserInfo(
            @Part("teacherId") RequestBody teacherId, @Part("aliasName") RequestBody aliasName,
            @Part("phone") RequestBody phone, @Part("signName") RequestBody signName, @Part("birthday") RequestBody birthday,
            @Part("sex") RequestBody sex, @Part MultipartBody.Part iconUrl);

    /**************************************评价部分**************************************/

    /**
     * 获取学院列表
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/selectAllCollege")
    Observable<HttpResult<List<College>>> getColleges(@Field("myId") int teacherId);

    /**
     * 获取一个学院的教师
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/selectTeacherByCollege")
    Observable<HttpResult<List<EvaTeacher>>> getTeachers(@Field("myId")int teacherId,
                                                         @Field("collegeId") int collegeId);
    /**
     * 获取某个教师所教的课程
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/selectCourseByTeacher")
    Observable<HttpResult<List<EvaCourse>>> getEvaCourses(
            @Field("myId") int teacherId, @Field("teacherId") int evaTeacherId);

    /**
     * 通过老师跟课程获取课堂
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/selectCoursePlan")
    Observable<HttpResult<List<CoursePlan>>> getCoursePlans(
            @Field("myId") int teacherId, @Field("teacherId") int evaTeacherId, @Field("courseId") int courseId);
    /**
     * 邀请评价或申请评价他人
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/invitationToEvaluate")
    Observable<NoDataResult> inviteOrApply(
            @Field("fromTeacherId") int teacherId, @Field("toTeacherId") String toTeacherId,
            @Field("requestType") int requestType, @Field("requestMessage") String requestMessage,
            @Field("evaluateType") int evaluateType, @Field("coursePlanId") int coursePlanId);
    /**
     * 查看我的任务
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/myAssignment")
    Observable<HttpResult<List<MyTask>>> getMyTasks(
            @Field("requestState") int requestState, @Field("myId") int teacherId);

    /**
     * 修改评价状态
     */
    @FormUrlEncoded
    @POST("EvaluateByTeacher/updateRequestState")
    Observable<NoDataResult> changeEvaState(
            @Field("requestEvalMessageId") int requestEvalMessageId, @Field("requestState") int requestState,
            @Field("teacherId") int teacherId);



    /**
     * 查看学生评价统计
     */
    @FormUrlEncoded
    @POST("EvaluateTeacherPort/selectTeacherStuEvalScore")
    Observable<HttpResult<StudentEva>> studentEva(
            @Field("teacherId") int teacherId);

    /**
     * 查看学生评价二级指标
     */
    @FormUrlEncoded
    @POST("EvaluateTeacherPort/selectTeacherStuEvalScoreTwo")
    Observable<HttpResult<List<EvaTwoIndex>>> studentEvaTwoIndex(
            @Field("indexOneId") int indexOneId, @Field("teacherId") int teacherId);

    /**
     * 查看同行评价
     */
    @FormUrlEncoded
    @POST("EvaluateTeacherPort/selectTeacherTchEvalScore")
    Observable<HttpResult<List<ColleagueEva>>> colleagueEva(@Field("teacherId") int teacherId);

    /**
     * 查看督导评价
     */
    @FormUrlEncoded
    @POST("EvaluateTeacherPort/selectTeacherSupEvalScore")
    Observable<HttpResult<List<ColleagueEva>>> supervisorEva(@Field("teacherId") int teacherId);

    /**
     * 查看自定义评价
     */
    @FormUrlEncoded
    @POST("EvaluateTeacherPort/selectTeacherEvaluateCustom")
    Observable<HttpResult<List<CustomEva>>> customEva(
            @Field("startRow") int startRow, @Field("pageSize") int pageSize,
            @Field("teacherId") int teacherId);


    /**********************未完成**********************/
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
