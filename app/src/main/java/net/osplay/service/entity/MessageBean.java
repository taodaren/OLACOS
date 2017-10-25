package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MessageBean {

    /**
     * rows : [{"CREATEDATE":"2017-09-16 17:04:41","FANS_COUNT":"0","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png","ID":"14138d0f8a844f51a50409d646098ea0","ISDELETE":"0","NICK_NAME":"伤心小男孩","PASSWORD":"lK0wgczko8sASBq/UUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=","PHONE":"17600396592","POINTS":"0","SHENHE":"0"},{"CREATEDATE":"2017-09-16 17:03:44","FANS_COUNT":"4","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/ca9e4947996f49c6841a3314fa6fb3fd/ca9e4947996f49c6841a3314fa6fb3fd.png","ID":"c9b9ef1c42c84f9298160c3e7bc5088e","ISDELETE":"0","NICK_NAME":"鹿小青","PASSWORD":"W8gYV3qMiaH320Yd7n3tZTN+w6kIJWsoR30PYN/+l3qGECcdeETqYWoeIOA=","PHONE":"13573799215","POINTS":"0","REASON":"","SHENHE":"0"},{"CREATEDATE":"2017-10-10 19:35:22","FANS_COUNT":"0","FOCUS_COUNT":"0","ID":"3d79e3dd532a4d6c944ceb5507dcef9f","ISDELETE":"0","NICK_NAME":"纪国斌","PASSWORD":"ZcobsJ/qNXtAtNmJUUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=","PHONE":"18211074952","POINTS":"0"},{"CREATEDATE":"2017-09-16 09:43:53","FANS_COUNT":"0","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/45544d9660af40d2bedafb099fc647cd/45544d9660af40d2bedafb099fc647cd.png","ID":"9d7d0f062dfb476c909c21dfe56b2503","ISDELETE":"0","NICK_NAME":"山东谢广坤","PASSWORD":"cd8LYhoSksn3Wwpe1d+u5PdlJWWngWH4JTm7pLMJ3ahkgJyU","PHONE":"17694465721","POINTS":"0","REASON":"仰慕斌哥很久了","SHENHE":"0"},{"BIRTHDAY":"1994-11-06","CN":"宝宝","CREATEDATE":"2017-09-12 10:46:12","FANS_COUNT":"4","FOCUS_COUNT":"-1","HEAD_PATH":"/upLoadResource/2552c21ae0184de1aa52b86a657aa1b3/2552c21ae0184de1aa52b86a657aa1b3.png","ID":"69f1badc98cc441c838310561d11bcb7","INTRODUCE":"啊累累","ISDELETE":"0","LOCAL_DRESS":"北京","NICK_NAME":"呆子","PASSWORD":"ToC4zA4yG2i2M38bHoUBY2yZzy5ckOkoP9xJ3XbekmzosdQU","PHONE":"17611228377","POINTS":"0","SHENHE":"0","TARGET":"1","XINGZUO":"天蝎座"}]
     * total : 5
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * CREATEDATE : 2017-09-16 17:04:41
         * FANS_COUNT : 0
         * FOCUS_COUNT : 0
         * HEAD_PATH : /upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png
         * ID : 14138d0f8a844f51a50409d646098ea0
         * ISDELETE : 0
         * NICK_NAME : 伤心小男孩
         * PASSWORD : lK0wgczko8sASBq/UUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=
         * PHONE : 17600396592
         * POINTS : 0
         * SHENHE : 0
         * REASON :
         * BIRTHDAY : 1994-11-06
         * CN : 宝宝
         * INTRODUCE : 啊累累
         * LOCAL_DRESS : 北京
         * TARGET : 1
         * XINGZUO : 天蝎座
         */

        private String CREATEDATE;
        private String FANS_COUNT;
        private String FOCUS_COUNT;
        private String HEAD_PATH;
        private String ID;
        private String ISDELETE;
        private String NICK_NAME;
        private String PASSWORD;
        private String PHONE;
        private String POINTS;
        private String SHENHE;
        private String REASON;
        private String BIRTHDAY;
        private String CN;
        private String INTRODUCE;
        private String LOCAL_DRESS;
        private String TARGET;
        private String XINGZUO;

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getFANS_COUNT() {
            return FANS_COUNT;
        }

        public void setFANS_COUNT(String FANS_COUNT) {
            this.FANS_COUNT = FANS_COUNT;
        }

        public String getFOCUS_COUNT() {
            return FOCUS_COUNT;
        }

        public void setFOCUS_COUNT(String FOCUS_COUNT) {
            this.FOCUS_COUNT = FOCUS_COUNT;
        }

        public String getHEAD_PATH() {
            return HEAD_PATH;
        }

        public void setHEAD_PATH(String HEAD_PATH) {
            this.HEAD_PATH = HEAD_PATH;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getISDELETE() {
            return ISDELETE;
        }

        public void setISDELETE(String ISDELETE) {
            this.ISDELETE = ISDELETE;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
            this.PASSWORD = PASSWORD;
        }

        public String getPHONE() {
            return PHONE;
        }

        public void setPHONE(String PHONE) {
            this.PHONE = PHONE;
        }

        public String getPOINTS() {
            return POINTS;
        }

        public void setPOINTS(String POINTS) {
            this.POINTS = POINTS;
        }

        public String getSHENHE() {
            return SHENHE;
        }

        public void setSHENHE(String SHENHE) {
            this.SHENHE = SHENHE;
        }

        public String getREASON() {
            return REASON;
        }

        public void setREASON(String REASON) {
            this.REASON = REASON;
        }

        public String getBIRTHDAY() {
            return BIRTHDAY;
        }

        public void setBIRTHDAY(String BIRTHDAY) {
            this.BIRTHDAY = BIRTHDAY;
        }

        public String getCN() {
            return CN;
        }

        public void setCN(String CN) {
            this.CN = CN;
        }

        public String getINTRODUCE() {
            return INTRODUCE;
        }

        public void setINTRODUCE(String INTRODUCE) {
            this.INTRODUCE = INTRODUCE;
        }

        public String getLOCAL_DRESS() {
            return LOCAL_DRESS;
        }

        public void setLOCAL_DRESS(String LOCAL_DRESS) {
            this.LOCAL_DRESS = LOCAL_DRESS;
        }

        public String getTARGET() {
            return TARGET;
        }

        public void setTARGET(String TARGET) {
            this.TARGET = TARGET;
        }

        public String getXINGZUO() {
            return XINGZUO;
        }

        public void setXINGZUO(String XINGZUO) {
            this.XINGZUO = XINGZUO;
        }
    }
}
