package net.osplay.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2017/9/27.
 * <p>
 * SCHOOL :
 * ISDELETE : 0
 * SHENHE : 0
 * PHONE : 17611228377
 * CREATEDATE : 2017-09-12 10:46:12
 * STU_PATH :
 * CN : cSZXdcZX
 * NICK_NAME : 呆子
 * CARD : 622827199411061759
 * CARD_F_PATH : /upLoadResource/e04bad69c8934abe86587da9028461e2/未标题-1(1).jpg
 * PASSWORD : EryP//NCm+drNvl4WHrvIyUl2bt7AN5H46VbrRer/E3C9LwvndSg+dTdiD4=
 * FANS_COUNT : 0
 * CARD_B_PATH : /upLoadResource/69886802c9784291b96db7db9e9254c7/需要修改的.jpg
 * ID : 69f1badc98cc441c838310561d11bcb7
 * FOCUS_COUNT : 0
 */
@Entity

public class Account {
    @Id
    private Long id;
    private String SCHOOL;//学校
    private String ISDELETE;
    private String SHENHE;
    private String PHONE;
    private String CREATEDATE;
    private String STU_PATH;
    private String CN;
    private String NICK_NAME;
    private String CARD;
    private String CARD_F_PATH;
    private String PASSWORD;
    private String FANS_COUNT;
    private String CARD_B_PATH;
    private String ID;
    private String FOCUS_COUNT;
    private String HEAD_PATH;
    private String BIRTHDAY;
    private String INTRODUCE;
    private String LOCAL_DRESS;
    private String POINTS;
    private String TARGET;
    private String XINGZUO;
    @Generated(hash = 1756785368)
    public Account(Long id, String SCHOOL, String ISDELETE, String SHENHE,
            String PHONE, String CREATEDATE, String STU_PATH, String CN,
            String NICK_NAME, String CARD, String CARD_F_PATH, String PASSWORD,
            String FANS_COUNT, String CARD_B_PATH, String ID, String FOCUS_COUNT,
            String HEAD_PATH, String BIRTHDAY, String INTRODUCE, String LOCAL_DRESS,
            String POINTS, String TARGET, String XINGZUO) {
        this.id = id;
        this.SCHOOL = SCHOOL;
        this.ISDELETE = ISDELETE;
        this.SHENHE = SHENHE;
        this.PHONE = PHONE;
        this.CREATEDATE = CREATEDATE;
        this.STU_PATH = STU_PATH;
        this.CN = CN;
        this.NICK_NAME = NICK_NAME;
        this.CARD = CARD;
        this.CARD_F_PATH = CARD_F_PATH;
        this.PASSWORD = PASSWORD;
        this.FANS_COUNT = FANS_COUNT;
        this.CARD_B_PATH = CARD_B_PATH;
        this.ID = ID;
        this.FOCUS_COUNT = FOCUS_COUNT;
        this.HEAD_PATH = HEAD_PATH;
        this.BIRTHDAY = BIRTHDAY;
        this.INTRODUCE = INTRODUCE;
        this.LOCAL_DRESS = LOCAL_DRESS;
        this.POINTS = POINTS;
        this.TARGET = TARGET;
        this.XINGZUO = XINGZUO;
    }
    @Generated(hash = 882125521)
    public Account() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSCHOOL() {
        return this.SCHOOL;
    }
    public void setSCHOOL(String SCHOOL) {
        this.SCHOOL = SCHOOL;
    }
    public String getISDELETE() {
        return this.ISDELETE;
    }
    public void setISDELETE(String ISDELETE) {
        this.ISDELETE = ISDELETE;
    }
    public String getSHENHE() {
        return this.SHENHE;
    }
    public void setSHENHE(String SHENHE) {
        this.SHENHE = SHENHE;
    }
    public String getPHONE() {
        return this.PHONE;
    }
    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    public String getCREATEDATE() {
        return this.CREATEDATE;
    }
    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }
    public String getSTU_PATH() {
        return this.STU_PATH;
    }
    public void setSTU_PATH(String STU_PATH) {
        this.STU_PATH = STU_PATH;
    }
    public String getCN() {
        return this.CN;
    }
    public void setCN(String CN) {
        this.CN = CN;
    }
    public String getNICK_NAME() {
        return this.NICK_NAME;
    }
    public void setNICK_NAME(String NICK_NAME) {
        this.NICK_NAME = NICK_NAME;
    }
    public String getCARD() {
        return this.CARD;
    }
    public void setCARD(String CARD) {
        this.CARD = CARD;
    }
    public String getCARD_F_PATH() {
        return this.CARD_F_PATH;
    }
    public void setCARD_F_PATH(String CARD_F_PATH) {
        this.CARD_F_PATH = CARD_F_PATH;
    }
    public String getPASSWORD() {
        return this.PASSWORD;
    }
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public String getFANS_COUNT() {
        return this.FANS_COUNT;
    }
    public void setFANS_COUNT(String FANS_COUNT) {
        this.FANS_COUNT = FANS_COUNT;
    }
    public String getCARD_B_PATH() {
        return this.CARD_B_PATH;
    }
    public void setCARD_B_PATH(String CARD_B_PATH) {
        this.CARD_B_PATH = CARD_B_PATH;
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getFOCUS_COUNT() {
        return this.FOCUS_COUNT;
    }
    public void setFOCUS_COUNT(String FOCUS_COUNT) {
        this.FOCUS_COUNT = FOCUS_COUNT;
    }
    public String getHEAD_PATH() {
        return this.HEAD_PATH;
    }
    public void setHEAD_PATH(String HEAD_PATH) {
        this.HEAD_PATH = HEAD_PATH;
    }
    public String getBIRTHDAY() {
        return this.BIRTHDAY;
    }
    public void setBIRTHDAY(String BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }
    public String getINTRODUCE() {
        return this.INTRODUCE;
    }
    public void setINTRODUCE(String INTRODUCE) {
        this.INTRODUCE = INTRODUCE;
    }
    public String getLOCAL_DRESS() {
        return this.LOCAL_DRESS;
    }
    public void setLOCAL_DRESS(String LOCAL_DRESS) {
        this.LOCAL_DRESS = LOCAL_DRESS;
    }
    public String getPOINTS() {
        return this.POINTS;
    }
    public void setPOINTS(String POINTS) {
        this.POINTS = POINTS;
    }
    public String getTARGET() {
        return this.TARGET;
    }
    public void setTARGET(String TARGET) {
        this.TARGET = TARGET;
    }
    public String getXINGZUO() {
        return this.XINGZUO;
    }
    public void setXINGZUO(String XINGZUO) {
        this.XINGZUO = XINGZUO;
    }

}

