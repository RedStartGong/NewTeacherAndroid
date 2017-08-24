package com.zidian.teacher.model.entity.remote;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by GongCheng on 2017/4/27.
 */

public class ColleagueEva {


    /**
     * twoIndexList : [{"twoIndexId":2,"twoIndexRate":0.4,"twoIndexScore":5,"twoIndexName":"师生互动"},{"twoIndexId":3,"twoIndexRate":0.6,"twoIndexScore":5,"twoIndexName":"教学态度/能力（师生和谐）"}]
     * oneIndexId : 2
     * oneIndexName : 师德评价
     * oneIndexScore : 5
     */

    private int oneIndexId;
    private String oneIndexName;
    private float oneIndexScore;
    private List<TwoIndexListBean> twoIndexList;

    public int getOneIndexId() {
        return oneIndexId;
    }

    public void setOneIndexId(int oneIndexId) {
        this.oneIndexId = oneIndexId;
    }

    public String getOneIndexName() {
        return oneIndexName;
    }

    public void setOneIndexName(String oneIndexName) {
        this.oneIndexName = oneIndexName;
    }

    public float getOneIndexScore() {
        return oneIndexScore;
    }

    public void setOneIndexScore(float oneIndexScore) {
        this.oneIndexScore = oneIndexScore;
    }

    public List<TwoIndexListBean> getTwoIndexList() {
        return twoIndexList;
    }

    public void setTwoIndexList(List<TwoIndexListBean> twoIndexList) {
        this.twoIndexList = twoIndexList;
    }

    public static class TwoIndexListBean implements Parcelable {
        /**
         * twoIndexId : 2
         * twoIndexRate : 0.4
         * twoIndexScore : 5
         * twoIndexName : 师生互动
         */

        private int twoIndexId;
        private float twoIndexRate;
        private float twoIndexScore;
        private String twoIndexName;

        public int getTwoIndexId() {
            return twoIndexId;
        }

        public void setTwoIndexId(int twoIndexId) {
            this.twoIndexId = twoIndexId;
        }

        public float getTwoIndexRate() {
            return twoIndexRate;
        }

        public void setTwoIndexRate(float twoIndexRate) {
            this.twoIndexRate = twoIndexRate;
        }

        public float getTwoIndexScore() {
            return twoIndexScore;
        }

        public void setTwoIndexScore(float twoIndexScore) {
            this.twoIndexScore = twoIndexScore;
        }

        public String getTwoIndexName() {
            return twoIndexName;
        }

        public void setTwoIndexName(String twoIndexName) {
            this.twoIndexName = twoIndexName;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.twoIndexId);
            dest.writeFloat(this.twoIndexRate);
            dest.writeFloat(this.twoIndexScore);
            dest.writeString(this.twoIndexName);
        }

        public TwoIndexListBean() {

        }

        public TwoIndexListBean(Parcel parcel) {
            this.twoIndexId = parcel.readInt();
            this.twoIndexRate = parcel.readFloat();
            this.twoIndexScore = parcel.readFloat();
            this.twoIndexName = parcel.readString();
        }

        public static final Creator<TwoIndexListBean> CREATOR = new Creator<TwoIndexListBean>() {
            @Override
            public TwoIndexListBean createFromParcel(Parcel in) {
                return new TwoIndexListBean(in);
            }

            @Override
            public TwoIndexListBean[] newArray(int size) {
                return new TwoIndexListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }
    }
}
