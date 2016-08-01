package com.library.dto;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by longbh on 16/6/19.
 */
public class ThemeDto implements Parcelable{

    /**
     * createDate : 1466921665481
     * gcId : 23
     * hxGroupId : 211986463129076164
     * id : 16
     * name : 群聊
     * updateDate : 1466921665481
     */

    private long createDate;
    private int gcId;
    private String hxGroupId;
    private int id;
    private String name;
    private long updateDate;

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getGcId() {
        return gcId;
    }

    public void setGcId(int gcId) {
        this.gcId = gcId;
    }

    public String getHxGroupId() {
        return hxGroupId;
    }

    public void setHxGroupId(String hxGroupId) {
        this.hxGroupId = hxGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //一定要按顺序写
        dest.writeLong(createDate);
        dest.writeInt(gcId);
        dest.writeString(hxGroupId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLong(updateDate);
    }


    public static Creator<ThemeDto> CREATOR = new Creator<ThemeDto>() {

        @Override
        public ThemeDto[] newArray(int size) {
            return new ThemeDto[size];
        }

        @Override
        public ThemeDto createFromParcel(Parcel source) {
            ThemeDto themeDto = new ThemeDto();
            themeDto.setCreateDate(source.readLong());
            themeDto.setGcId(source.readInt());
            themeDto.setHxGroupId(source.readString());
            themeDto.setId(source.readInt());
            themeDto.setName(source.readString());
            themeDto.setUpdateDate(source.readLong());
            return themeDto;
        }
    };
}
