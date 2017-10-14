package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class MemberInfoBean {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * TARGET : 1
         * ISDELETE : 0
         * SHENHE : 0
         * ISEXAMINE : 0
         * PHONE : 17611228377
         * CREATEDATE : 2017-09-12 10:46:12
         * XINGZUO : 天蝎座
         * HEAD_PATH : /upLoadResource/2552c21ae0184de1aa52b86a657aa1b3/2552c21ae0184de1aa52b86a657aa1b3.png
         * CN : 宝宝
         * NICK_NAME : 呆子
         * INTRODUCE : 啊累累
         * PASSWORD : ToC4zA4yG2i2M38bHoUBY2yZzy5ckOkoP9xJ3XbekmzosdQU
         * FANS_COUNT : 4
         * ID : 69f1badc98cc441c838310561d11bcb7
         * POINTS : 0
         * LOCAL_DRESS : 北京
         * FOCUS_COUNT : -1
         * BIRTHDAY : 1994-11-06
         */

        private String TARGET;
        private String ISDELETE;
        private String SHENHE;
        private String ISEXAMINE;
        private String PHONE;
        private String CREATEDATE;
        private String XINGZUO;
        private String HEAD_PATH;
        private String CN;
        private String NICK_NAME;
        private String INTRODUCE;
        private String PASSWORD;
        private String FANS_COUNT;
        private String ID;
        private String POINTS;
        private String LOCAL_DRESS;
        private String FOCUS_COUNT;
        private String BIRTHDAY;

        public String getTARGET() {
            return TARGET;
        }

        public void setTARGET(String TARGET) {
            this.TARGET = TARGET;
        }

        public String getISDELETE() {
            return ISDELETE;
        }

        public void setISDELETE(String ISDELETE) {
            this.ISDELETE = ISDELETE;
        }

        public String getSHENHE() {
            return SHENHE;
        }

        public void setSHENHE(String SHENHE) {
            this.SHENHE = SHENHE;
        }

        public String getISEXAMINE() {
            return ISEXAMINE;
        }

        public void setISEXAMINE(String ISEXAMINE) {
            this.ISEXAMINE = ISEXAMINE;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getXINGZUO() {
            return XINGZUO;
        }

        public void setXINGZUO(String XINGZUO) {
            this.XINGZUO = XINGZUO;
        }

        public String getHEAD_PATH() {
            return HEAD_PATH;
        }

        public void setHEAD_PATH(String HEAD_PATH) {
            this.HEAD_PATH = HEAD_PATH;
        }

        public String getCN() {
            return CN;
        }

        public void setCN(String CN) {
            this.CN = CN;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getINTRODUCE() {
            return INTRODUCE;
        }

        public void setINTRODUCE(String INTRODUCE) {
            this.INTRODUCE = INTRODUCE;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
            this.PASSWORD = PASSWORD;
        }

        public String getFANS_COUNT() {
            return FANS_COUNT;
        }

        public void setFANS_COUNT(String FANS_COUNT) {
            this.FANS_COUNT = FANS_COUNT;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPOINTS() {
            return POINTS;
        }

        public void setPOINTS(String POINTS) {
            this.POINTS = POINTS;
        }

        public String getLOCAL_DRESS() {
            return LOCAL_DRESS;
        }

        public void setLOCAL_DRESS(String LOCAL_DRESS) {
            this.LOCAL_DRESS = LOCAL_DRESS;
        }

        public String getFOCUS_COUNT() {
            return FOCUS_COUNT;
        }

        public void setFOCUS_COUNT(String FOCUS_COUNT) {
            this.FOCUS_COUNT = FOCUS_COUNT;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }
    }
}
