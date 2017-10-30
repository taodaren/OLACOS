package net.osplay.service.entity;

/**
 * 获取当前签到用户的等级数据
 */

public class CheckLvBean {

    /**
     * PARTID : 1
     * CREATEID : 9e23d87dc3e2446396998c0f40426727
     * LAST : 3
     * MEMBER_RANK : 1
     * CREATDATE : 2017-10-30 11:08:51
     * FENSHU : 3
     * ID : f00fb4aab15149b5a9f78fc78363efcf
     * NEXTPOINT : 5
     */

    private String PARTID;
    private String CREATEID;
    private String LAST;
    private String MEMBER_RANK;//等级
    private String CREATDATE;
    private String FENSHU;//当前积分
    private String ID;
    private String NEXTPOINT;//满分

    public String getPARTID() {
        return PARTID;
    }

    public void setPARTID(String PARTID) {
        this.PARTID = PARTID;
    }

    public String getCREATEID() {
        return CREATEID;
    }

    public void setCREATEID(String CREATEID) {
        this.CREATEID = CREATEID;
    }

    public String getLAST() {
        return LAST;
    }

    public void setLAST(String LAST) {
        this.LAST = LAST;
    }

    public String getMEMBER_RANK() {
        return MEMBER_RANK;
    }

    public void setMEMBER_RANK(String MEMBER_RANK) {
        this.MEMBER_RANK = MEMBER_RANK;
    }

    public String getCREATDATE() {
        return CREATDATE;
    }

    public void setCREATDATE(String CREATDATE) {
        this.CREATDATE = CREATDATE;
    }

    public String getFENSHU() {
        return FENSHU;
    }

    public void setFENSHU(String FENSHU) {
        this.FENSHU = FENSHU;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNEXTPOINT() {
        return NEXTPOINT;
    }

    public void setNEXTPOINT(String NEXTPOINT) {
        this.NEXTPOINT = NEXTPOINT;
    }
}
