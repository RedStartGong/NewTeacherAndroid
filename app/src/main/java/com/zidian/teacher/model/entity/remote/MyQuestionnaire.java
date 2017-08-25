package com.zidian.teacher.model.entity.remote;

/**
 * Created by GongCheng on 2017/5/3.
 */

public class MyQuestionnaire {

    /**
     * questionnaireId : 5
     * questionnaireName : 发布一张问卷
     * releaseTime : 1501039494000
     * quesFillOutNum : 2
     * quesPushNum : 3
     */

    private int questionnaireId;
    private String questionnaireName;
    private long releaseTime;
    private int quesFillOutNum;
    private int quesPushNum;

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getQuesFillOutNum() {
        return quesFillOutNum;
    }

    public void setQuesFillOutNum(int quesFillOutNum) {
        this.quesFillOutNum = quesFillOutNum;
    }

    public int getQuesPushNum() {
        return quesPushNum;
    }

    public void setQuesPushNum(int quesPushNum) {
        this.quesPushNum = quesPushNum;
    }
}
