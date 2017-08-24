package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/20.
 */

public class EvaluateTag {

    /**
     * threeIndexList : [{"threeIndexId":2,"threeIndexName":"教学资源","pushTeacherEvalItemsId":111,"tchQuestionStyle":"在您看来，这门课程的教学资源十分丰富？","labelList":[{"labelName":"不禁竖起了大拇指","labelId":6,"labelScore":5},{"labelName":"nice！","labelId":7,"labelScore":4},{"labelName":"能用","labelId":8,"labelScore":3},{"labelName":"凑凑才能用","labelId":9,"labelScore":2},{"labelName":"zero","labelId":10,"labelScore":1}],"evaluateLabel":6}]
     * customEvaluate : ””
     */

    private String customEvaluate;
    private List<ThreeIndexListBean> threeIndexList;

    public String getCustomEvaluate() {
        return customEvaluate;
    }

    public void setCustomEvaluate(String customEvaluate) {
        this.customEvaluate = customEvaluate;
    }

    public List<ThreeIndexListBean> getThreeIndexList() {
        return threeIndexList;
    }

    public void setThreeIndexList(List<ThreeIndexListBean> threeIndexList) {
        this.threeIndexList = threeIndexList;
    }

    public static class ThreeIndexListBean {
        /**
         * threeIndexId : 2
         * threeIndexName : 教学资源
         * pushTeacherEvalItemsId : 111
         * tchQuestionStyle : 在您看来，这门课程的教学资源十分丰富？
         * labelList : [{"labelName":"不禁竖起了大拇指","labelId":6,"labelScore":5},{"labelName":"nice！","labelId":7,"labelScore":4},{"labelName":"能用","labelId":8,"labelScore":3},{"labelName":"凑凑才能用","labelId":9,"labelScore":2},{"labelName":"zero","labelId":10,"labelScore":1}]
         * evaluateLabel : 6
         */

        private int threeIndexId;
        private String threeIndexName;
        private int pushTeacherEvalItemsId;
        private String tchQuestionStyle;
        private int evaluateLabel;
        private List<LabelListBean> labelList;

        public int getThreeIndexId() {
            return threeIndexId;
        }

        public void setThreeIndexId(int threeIndexId) {
            this.threeIndexId = threeIndexId;
        }

        public String getThreeIndexName() {
            return threeIndexName;
        }

        public void setThreeIndexName(String threeIndexName) {
            this.threeIndexName = threeIndexName;
        }

        public int getPushTeacherEvalItemsId() {
            return pushTeacherEvalItemsId;
        }

        public void setPushTeacherEvalItemsId(int pushTeacherEvalItemsId) {
            this.pushTeacherEvalItemsId = pushTeacherEvalItemsId;
        }

        public String getTchQuestionStyle() {
            return tchQuestionStyle;
        }

        public void setTchQuestionStyle(String tchQuestionStyle) {
            this.tchQuestionStyle = tchQuestionStyle;
        }

        public int getEvaluateLabel() {
            return evaluateLabel;
        }

        public void setEvaluateLabel(int evaluateLabel) {
            this.evaluateLabel = evaluateLabel;
        }

        public List<LabelListBean> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<LabelListBean> labelList) {
            this.labelList = labelList;
        }

        public static class LabelListBean {
            /**
             * labelName : 不禁竖起了大拇指
             * labelId : 6
             * labelScore : 5
             */

            private String labelName;
            private int labelId;
            private int labelScore;

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            public int getLabelId() {
                return labelId;
            }

            public void setLabelId(int labelId) {
                this.labelId = labelId;
            }

            public int getLabelScore() {
                return labelScore;
            }

            public void setLabelScore(int labelScore) {
                this.labelScore = labelScore;
            }
        }
    }
}
