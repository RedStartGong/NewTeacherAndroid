package com.zidian.teacher.model.entity.remote;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class ColleagueEva {

    /**
     * twoIndexList : ["课程设置"]
     * indexName : （学科）专业建设与课程设置
     * indexScore : 4.0
     */

    private String indexName;
    private float indexScore;
    private List<String> twoIndexList;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public float getIndexScore() {
        return indexScore;
    }

    public void setIndexScore(float indexScore) {
        this.indexScore = indexScore;
    }

    public List<String> getTwoIndexList() {
        return twoIndexList;
    }

    public void setTwoIndexList(List<String> twoIndexList) {
        this.twoIndexList = twoIndexList;
    }
}
