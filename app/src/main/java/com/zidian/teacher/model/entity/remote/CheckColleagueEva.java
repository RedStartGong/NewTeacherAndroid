package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/24.
 */

public class CheckColleagueEva {

    /**
     * evaluateComment : 自定义评价内容！！！！
     * mapList : [{"labelThreeIndexName":"重点学科建设精品课程设置","threeIndexQuestionTea":"据您了解，这门课程是否符合重点学科建设精品课程设置？","mycChoiceLabel":"走向人生巅峰","labelList":["走向人生巅峰","大力丸","有长进","强行精品","啥玩意儿？"]},{"labelThreeIndexName":"教学大纲","threeIndexQuestionTea":"在您看来，这门课程的教学大纲设计合理，有利于学生实现教学目标？","mycChoiceLabel":"可以","labelList":["逆天","合理有效","可以","五毛大纲","我上我也行"]}]
     */

    private String evaluateComment;
    private List<MapListBean> mapList;

    public String getEvaluateComment() {
        return evaluateComment;
    }

    public void setEvaluateComment(String evaluateComment) {
        this.evaluateComment = evaluateComment;
    }

    public List<MapListBean> getMapList() {
        return mapList;
    }

    public void setMapList(List<MapListBean> mapList) {
        this.mapList = mapList;
    }

    public static class MapListBean {
        /**
         * labelThreeIndexName : 重点学科建设精品课程设置
         * threeIndexQuestionTea : 据您了解，这门课程是否符合重点学科建设精品课程设置？
         * mycChoiceLabel : 走向人生巅峰
         * labelList : ["走向人生巅峰","大力丸","有长进","强行精品","啥玩意儿？"]
         */

        private String labelThreeIndexName;
        private String threeIndexQuestionTea;
        private String mycChoiceLabel;
        private List<String> labelList;

        public String getLabelThreeIndexName() {
            return labelThreeIndexName;
        }

        public void setLabelThreeIndexName(String labelThreeIndexName) {
            this.labelThreeIndexName = labelThreeIndexName;
        }

        public String getThreeIndexQuestionTea() {
            return threeIndexQuestionTea;
        }

        public void setThreeIndexQuestionTea(String threeIndexQuestionTea) {
            this.threeIndexQuestionTea = threeIndexQuestionTea;
        }

        public String getMycChoiceLabel() {
            return mycChoiceLabel;
        }

        public void setMycChoiceLabel(String mycChoiceLabel) {
            this.mycChoiceLabel = mycChoiceLabel;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }
}
