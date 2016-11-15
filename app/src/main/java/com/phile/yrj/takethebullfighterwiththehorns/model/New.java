package com.phile.yrj.takethebullfighterwiththehorns.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */

public class New implements Parcelable{
    private int id;
    private String title;
    private String body;
    private Date date;
    private String imgUrl;

    public static final Comparator<New> ASC_DATE_COMPARATOR = new Comparator<New>() {
        @Override
        public int compare(New o1, New o2) {
            return o1.date.compareTo(o2.date);
        }
    };
    public static final Comparator<New> DESC_DATE_COMPARATOR = new Comparator<New>() {
        @Override
        public int compare(New o1, New o2) {
            return -1 * o1.date.compareTo(o2.date);
        }
    };

    public static final Parcelable.Creator<New> CREATOR
            = new Parcelable.Creator<New>() {
        public New createFromParcel(Parcel in) {
            return new New(in);
        }

        public New[] newArray(int size) {
            return new New[size];
        }
    };
    public New(int id, String title, String body, Date date, String imgUrl){
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.imgUrl = imgUrl;
    }
    public New(Parcel in) {
        readFromParcel(in);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (this.id != id)
            this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (this.title != title)
            this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        if (this.body != body)
            this.body = body;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        if (this.date != date)
            this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl){
        if (this.imgUrl != imgUrl)
            this.imgUrl = imgUrl;
    }

    public String getFormatedDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String result = df.format(this.date);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(title);
        dest.writeString(body);
        /* I've read that consume much performance
           dest.writeSerializable(date);*/
        dest.writeLong(date.getTime());
        dest.writeString(imgUrl);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
        date = new Date(in.readLong());
        imgUrl = in.readString();
    }
}
