package net.osplay.data.bean;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/10/12.
 */
@Entity
public class Community {
    private String AUTOGRAPH;
    private String BACKGROUND;
    private String CREATEDATE;
    private String CREATEID;
    private String HEADID;
    private String ID;
    private String INTRODUCTION;
    private String ISDELETE;
    private String ISEXAMINE;
    private String NAME;
    private String PHOTO;
    private String REASON;
    private String REGION;
    @Generated(hash = 1109462474)
    public Community(String AUTOGRAPH, String BACKGROUND, String CREATEDATE,
            String CREATEID, String HEADID, String ID, String INTRODUCTION,
            String ISDELETE, String ISEXAMINE, String NAME, String PHOTO,
            String REASON, String REGION) {
        this.AUTOGRAPH = AUTOGRAPH;
        this.BACKGROUND = BACKGROUND;
        this.CREATEDATE = CREATEDATE;
        this.CREATEID = CREATEID;
        this.HEADID = HEADID;
        this.ID = ID;
        this.INTRODUCTION = INTRODUCTION;
        this.ISDELETE = ISDELETE;
        this.ISEXAMINE = ISEXAMINE;
        this.NAME = NAME;
        this.PHOTO = PHOTO;
        this.REASON = REASON;
        this.REGION = REGION;
    }
    @Generated(hash = 1247825642)
    public Community() {
    }
    public String getAUTOGRAPH() {
        return this.AUTOGRAPH;
    }
    public void setAUTOGRAPH(String AUTOGRAPH) {
        this.AUTOGRAPH = AUTOGRAPH;
    }
    public String getBACKGROUND() {
        return this.BACKGROUND;
    }
    public void setBACKGROUND(String BACKGROUND) {
        this.BACKGROUND = BACKGROUND;
    }
    public String getCREATEDATE() {
        return this.CREATEDATE;
    }
    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }
    public String getCREATEID() {
        return this.CREATEID;
    }
    public void setCREATEID(String CREATEID) {
        this.CREATEID = CREATEID;
    }
    public String getHEADID() {
        return this.HEADID;
    }
    public void setHEADID(String HEADID) {
        this.HEADID = HEADID;
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getINTRODUCTION() {
        return this.INTRODUCTION;
    }
    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }
    public String getISDELETE() {
        return this.ISDELETE;
    }
    public void setISDELETE(String ISDELETE) {
        this.ISDELETE = ISDELETE;
    }
    public String getISEXAMINE() {
        return this.ISEXAMINE;
    }
    public void setISEXAMINE(String ISEXAMINE) {
        this.ISEXAMINE = ISEXAMINE;
    }
    public String getNAME() {
        return this.NAME;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public String getPHOTO() {
        return this.PHOTO;
    }
    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }
    public String getREASON() {
        return this.REASON;
    }
    public void setREASON(String REASON) {
        this.REASON = REASON;
    }
    public String getREGION() {
        return this.REGION;
    }
    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

}

