package com.uc.utsprogtech.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SaveData implements Parcelable {

    public static ArrayList<User> saveList = new ArrayList<>();

    public SaveData(Parcel in) {
    }

    public SaveData(String fname, String address, String s) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SaveData> CREATOR = new Creator<SaveData>() {
        @Override
        public SaveData createFromParcel(Parcel in) {
            return new SaveData(in);
        }

        @Override
        public SaveData[] newArray(int size) {
            return new SaveData[size];
        }
    };
}
