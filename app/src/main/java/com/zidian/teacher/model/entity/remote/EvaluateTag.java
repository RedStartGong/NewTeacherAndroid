package com.zidian.teacher.model.entity.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/20.
 */

public class EvaluateTag {

    /**
     * packageName : 教评
     * threeindexname : 人格成长
     * indexName : 教学态度
     * label : [{"labelName":"自带爹妈光环"},{"labelName":"他是谁"}]
     * threeIndexQuestionTea : 问题
     */

    private String packageName;
    @SerializedName("threeindexname")
    private String threeIndexName;
    private String indexName;
    private String threeIndexQuestionTea;
    private List<LabelBean> label;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getThreeIndexName() {
        return threeIndexName;
    }

    public void setThreeIndexName(String threeIndexName) {
        this.threeIndexName = threeIndexName;
    }

    public String getThreeIndexQuestionTea() {
        return threeIndexQuestionTea;
    }

    public void setThreeIndexQuestionTea(String threeIndexQuestionTea) {
        this.threeIndexQuestionTea = threeIndexQuestionTea;
    }

    public String getThreeindexname() {
        return threeIndexName;
    }

    public void setThreeindexname(String threeIndexName) {
        this.threeIndexName = threeIndexName;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public List<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(List<LabelBean> label) {
        this.label = label;
    }

    public static class LabelBean {
        /**
         * labelName : 自带爹妈光环
         */

        private String labelName;

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }
    }
}
