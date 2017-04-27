package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class CustomEva {

    /**
     * pages : 13
     * evaluateNum : 25
     * list : [{"mynickname":"大雄","likenum":0,"likenumtype":0,"evaluationcontent":"你这个人是","evaluationtime":"2016-10-28 11:15:04","appraiserid":"11011040105","deltype":1,"appraiserurl":"http://ling-zhi-jie.oss-cn-shanghai.aliyuncs.com/11011040105-HeadPortrait-8Z12%6055~46W9_I_%253M5Y%7D%60N.png?Expires=1791508963&OSSAccessKeyId=LTAIMUy5LebAYfzN&Signature=lXvocBbvB4Z1r6BZAs1R6gTxL0M%3D","customevaluationid":54,"byevaluatepersonid":"6283"}]
     */

    private int pages;
    private int evaluateNum;
    private List<ListBean> list;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * mynickname : 大雄
         * likenum : 0
         * likenumtype : 0
         * evaluationcontent : 你这个人是
         * evaluationtime : 2016-10-28 11:15:04
         * appraiserid : 11011040105
         * deltype : 1
         * appraiserurl : http://ling-zhi-jie.oss-cn-shanghai.aliyuncs.com/11011040105-HeadPortrait-8Z12%6055~46W9_I_%253M5Y%7D%60N.png?Expires=1791508963&OSSAccessKeyId=LTAIMUy5LebAYfzN&Signature=lXvocBbvB4Z1r6BZAs1R6gTxL0M%3D
         * customevaluationid : 54
         * byevaluatepersonid : 6283
         */

        @SerializedName("mynickname")
        private String nickname;
        @SerializedName("likenum")
        private int likeNum;
        @SerializedName("likenumtype")
        private int likeNumType;
        @SerializedName("evaluationcontent")
        private String content;
        @SerializedName("evaluationtime")
        private String time;
        @SerializedName("appraiserid")
        private String studentId;
        @SerializedName("deltype")
        private int deltTpe;
        @SerializedName("appraiserurl")
        private String headportraitUrl;
        @SerializedName("customevaluationid")
        private int evaluationId;
        @SerializedName("byevaluatepersonid")
        private String teacherId;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getLikeNumType() {
            return likeNumType;
        }

        public void setLikeNumType(int likeNumType) {
            this.likeNumType = likeNumType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public int getDeltTpe() {
            return deltTpe;
        }

        public void setDeltTpe(int deltTpe) {
            this.deltTpe = deltTpe;
        }

        public String getHeadportraitUrl() {
            return headportraitUrl;
        }

        public void setHeadportraitUrl(String headportraitUrl) {
            this.headportraitUrl = headportraitUrl;
        }

        public int getEvaluationId() {
            return evaluationId;
        }

        public void setEvaluationId(int evaluationId) {
            this.evaluationId = evaluationId;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }
    }
}
