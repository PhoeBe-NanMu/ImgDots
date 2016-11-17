package com.lnyp.imgdots.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by leiyang on 2016/11/11.
 */

public class PointInfo implements Parcelable {
    private String pointName = "NULL";
    private String pointNum= "NULL";
    private String pointSimpleInfo= "NULL";

    public PointInfo(){

    }

    protected PointInfo(Parcel in) {
        pointName = in.readString();
        pointNum = in.readString();
        pointSimpleInfo = in.readString();
    }

    public static final Creator<PointInfo> CREATOR = new Creator<PointInfo>() {
        @Override
        public PointInfo createFromParcel(Parcel in) {
            return new PointInfo(in);
        }

        @Override
        public PointInfo[] newArray(int size) {
            return new PointInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pointName);
        parcel.writeString(pointNum);
        parcel.writeString(pointSimpleInfo);
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointNum() {
        return pointNum;
    }

    public void setPointNum(String pointNum) {
        this.pointNum = pointNum;
    }

    public String getPointSimpleInfo() {
        return pointSimpleInfo;
    }

    public void setPointSimpleInfo(String pointSimpleInfo) {
        this.pointSimpleInfo = pointSimpleInfo;
    }


}
