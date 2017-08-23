package com.zidian.teacher.model;


import com.zidian.teacher.model.entity.remote.AttendanceStatistics;
import com.zidian.teacher.model.entity.remote.AttendanceStudent;
import com.zidian.teacher.model.entity.remote.CheckColleagueEva;
import com.zidian.teacher.model.entity.remote.CheckSupervisorEva;
import com.zidian.teacher.model.entity.remote.Class;
import com.zidian.teacher.model.entity.remote.ColleagueEva;
import com.zidian.teacher.model.entity.remote.Course;
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

    public Observable<LoginResult> login(String username, String password, int schoolId) {
        return service.login(username, password, schoolId);
    }

    public Observable<HttpResult<PersonInfo>> getPersonInfo(
            String teacherId, String teacherType, String token, int schoolId) {
        return service.getPersonInfo(teacherType);
    }

    public Observable<NoDataResult> changePassword(
            String teacherId, String password, String password1, String password2,
            String token, int schoolId) {
        return service.changePassword(teacherId, password, password1, password2, token, schoolId);
    }

    public Observable<HttpResult<List<Course>>> getCourses(
            String teacherId, String token, int schoolId) {
        return service.getCourses();
    }

    public Observable<HttpResult<List<Class>>> getClasses(
            String courseId, String teacherId, String token, int schoolId) {
        return service.getClasses(courseId);
    }

    public Observable<AttendanceStudent> getAttendanceStudent(
            String courseWeeklyId, String courseId, String className, String teacherId,
            String token, int schoolId) {
        return service.getAttendanceStudents(courseWeeklyId, courseId, className, teacherId,
                token, schoolId);
    }

    public Observable<NoDataResult> setAttendance(
            String student, String courseId, String courseWeeklyId, String teacherId,
            String token, int schoolId) {
        return service.setAttendance(student, courseId, courseWeeklyId, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<AttendanceStatistics>>> getAttendanceStatistics(
            String courseId, String className, String teacherId, String token, int schoolId) {
        return service.getAttendanceStatistics(courseId, className, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> feedback(String feedbackId, String feedbackInformation,
                                             String type, String token, int schoolId) {
        return service.feedback(feedbackId, feedbackInformation, type, token, schoolId);
    }

    public Observable<NoDataResult> setPortrait(RequestBody teacherId, RequestBody token,
                                                RequestBody schoolId, MultipartBody.Part file) {
        return service.setPortrait(teacherId, token, schoolId, file);
    }

    public Observable<NoDataResult> setPersonInfo(
            String motto, String phoneNumber, String teacherSex,
            String birthday, String nickName, String teacherId, String token, int schoolId) {
        return service.setPersonInfo(motto, phoneNumber, teacherSex, birthday, nickName, teacherId,
                token, schoolId);
    }

    public Observable<HttpResult<List<MyTask>>> getMyTasks(
            String requestState, String teacherId, String token, int schoolId) {
        return service.getMyTasks(requestState, teacherId, token, schoolId);
    }

    public Observable<InviteCourseResult> getInviteCourses(String teacherId, String token,
                                                           int schoolId) {
        return service.getInviteCourses(teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<EvaluateCourse>>> getEvaluateCourses(
            String teacherId, String token, int schoolId) {
        return service.getEvaluateCourses(teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<InviteTeacher>>> getInviteTeachers(
            String condition, String teacherId, String token, int schoolId) {
        return service.getInviteTeacher(condition, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> inviteOrApply(
            String teacherId, String teacherName, String requestedPerson, String requestType,
            String teacherCollege, String courseId, String courseName, String teachingCalendar,
            String classroom, String requestExplain, String token, int schoolId) {
        return service.inviteOrApply(teacherId, teacherName, requestedPerson, requestType, teacherCollege,
                courseId, courseName, teachingCalendar, classroom, requestExplain, token, schoolId);
    }

    public Observable<NoDataResult> addSupervisorEva(
            String teacherId, String teacherName, String college, String requestedPersonId, String requestedPersonName,
            String courseId, String courseName, String teachingCalendar, String classroom,
            String teacherType, String token, int schoolId) {
        return service.addSupervisorEva(teacherId, teacherName, college, requestedPersonId, requestedPersonName,
                courseId, courseName, teachingCalendar, classroom, teacherType, token, schoolId);
    }

    public Observable<HttpResult<List<EvaluateTag>>> getEvaluateTags(
            String packageName, String teacherId, String operatorType, String token,
            int schoolId) {
        return service.getEvaluateTags(packageName, teacherId, operatorType, token, schoolId);

    }

    public Observable<NoDataResult> evaluate(
            String evaluateType, String teacherType, String evaluatedId, String recordId,
            String evaluateLabel, String evaluateComment, String colleagueId, String token,
            int schoolId) {
        return service.evaluate(evaluateType, teacherType, evaluatedId, recordId, evaluateLabel,
                evaluateComment, colleagueId, token, schoolId);
    }

    public Observable<NoDataResult> changeEvaState(
            String recordId, String requestState, String teacherId, String token, int schoolId) {
        return service.changeEvaState(recordId, requestState, teacherId, token, schoolId);
    }

    public Observable<HttpResult<CheckColleagueEva>> checkColleagueEva(
            String recordId, String teacherId, String token, int schoolId) {
        return service.checkColleagueEva(recordId, teacherId, token, schoolId);
    }

    public Observable<HttpResult<CheckSupervisorEva>> checkSupervisorEva(
            String recordId, String teacherId, String token, int schoolId) {
        return service.checkSupervisorEva(recordId, teacherId, token, schoolId);
    }

    public Observable<NoDataResult> supervisorFeedback(
            String contentFeedback, String recordId, String teacherId, String token, int schoolId) {
        return service.supervisorFeedback(contentFeedback, recordId, teacherId, token, schoolId);
    }

    public Observable<HttpResult<StudentEva>> studentEva(String teacherId, String token, int schoolId) {
        return service.studentEva(teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<EvaTwoIndex>>> studentEvaTwoIndex(
            String indexName, String teacherId, String token, int schoolId) {
        return service.studentEvaTwoIndex(indexName, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<ColleagueEva>>> colleagueEva(
            String evaluateType, String teacherId, String token, int schoolId) {
        return service.colleagueEva(evaluateType, teacherId, token, schoolId);
    }

    public Observable<HttpResult<List<EvaTwoIndex>>> colleagueEvaTwoIndex(
            String evaluateType, String indexName, String teacherId, String token,
            int schoolId) {
        return service.colleagueEvaTwoIndex(evaluateType, indexName, teacherId, token, schoolId);
    }

    public Observable<HttpResult<CustomEva>> customEva(
            String startRow, String pageSize, String operatorId, String operatorType,
            String teacherId, String token, int schoolId) {
        return service.customEva(startRow, pageSize, operatorId, operatorType, teacherId, token, schoolId);
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
