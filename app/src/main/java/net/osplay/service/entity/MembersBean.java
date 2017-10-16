package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class MembersBean {

    /**
     * rows : [{"CREATEDATE":"2017-09-16 17:04:41","FANS_COUNT":"0","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png","ID":"14138d0f8a844f51a50409d646098ea0","ISDELETE":"0","NICK_NAME":"伤心小男孩","PASSWORD":"lK0wgczko8sASBq/UUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=","PHONE":"17600396592","POINTS":"0","SHENHE":"0"},{"CREATEDATE":"2017-09-13 11:15:53","FANS_COUNT":"0","FOCUS_COUNT":"0","ID":"494afdbee2d64e79a0197c1db69412ff","ISDELETE":"0","NICK_NAME":"zuoyeshenlan ","PASSWORD":"JMduxwVxfXEpaC8UViZ46ZNDbX9R3XcR7UYEWKDhETVE2f159xi2i6sj/oU=","PHONE":"17611370006","POINTS":"0","SHENHE":"1"},{"BIRTHDAY":"1988-01-14","CN":"陶禹臣","CREATEDATE":"2017-09-16 09:30:51","FANS_COUNT":"-3","FOCUS_COUNT":"1","HEAD_PATH":"/upLoadResource/a4f8274328894acb90323230255eaac3/a4f8274328894acb90323230255eaac3.png","ID":"9e23d87dc3e2446396998c0f40426727","INTRODUCE":"With a different perspective to see the world.","ISDELETE":"0","LOCAL_DRESS":"北京","NICK_NAME":"taodaren","PASSWORD":"A4gIIdlyrMW90Hgyw9Q5EIskxzpHDSfu2pWftH6DqZ17iN40tl80BbIAAb4=","PHONE":"18511211125","POINTS":"0","SHENHE":"0","TARGET":"","XINGZUO":"魔羯座"},{"CREATEDATE":"2017-10-11 09:18:16","FANS_COUNT":"0","FOCUS_COUNT":"4","HEAD_PATH":"/upLoadResource/e57c6c04273848e78bdcb6fe9496fc1c/e57c6c04273848e78bdcb6fe9496fc1c.png","ID":"bc47ceaa530a4d2181ea1271638e79b5","ISDELETE":"0","NICK_NAME":"刘善良","PASSWORD":"8w0pH8I8IF10vqNwldsoIkObbJAjkpdt2WduUYXvETqpkMca","PHONE":"18865595617","POINTS":"0","SHENHE":"0"},{"CREATEDATE":"2017-09-18 17:27:30","FANS_COUNT":"0","FOCUS_COUNT":"0","ID":"adb207fd4e3e4f7086c06a6f9c613109","ISDELETE":"0","NICK_NAME":"王尼玛是傻逼","PASSWORD":"nlHQRpgTS2C047DMSxK5Up2gHGYm0wnR7z/z4sB8zW5MeAlGHxxb98MB+sg=","PHONE":"17746560282","POINTS":"0"},{"CREATEDATE":"2017-09-16 17:04:41","FANS_COUNT":"0","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png","ID":"14138d0f8a844f51a50409d646098ea0","ISDELETE":"0","NICK_NAME":"伤心小男孩","PASSWORD":"lK0wgczko8sASBq/UUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=","PHONE":"17600396592","POINTS":"0","SHENHE":"0"},{"CREATEDATE":"2017-10-10 19:35:22","FANS_COUNT":"0","FOCUS_COUNT":"0","ID":"3d79e3dd532a4d6c944ceb5507dcef9f","ISDELETE":"0","NICK_NAME":"纪国斌","PASSWORD":"ZcobsJ/qNXtAtNmJUUWpXX4Ygl4pa/ihTVm0ehPjUCI/x0yGwGCAZL4yWys=","PHONE":"18211074952","POINTS":"0"},{"BIRTHDAY":"1994-11-06","CN":"宝宝","CREATEDATE":"2017-09-12 10:46:12","FANS_COUNT":"4","FOCUS_COUNT":"-1","HEAD_PATH":"/upLoadResource/2552c21ae0184de1aa52b86a657aa1b3/2552c21ae0184de1aa52b86a657aa1b3.png","ID":"69f1badc98cc441c838310561d11bcb7","INTRODUCE":"啊累累","ISDELETE":"0","LOCAL_DRESS":"北京","NICK_NAME":"呆子","PASSWORD":"ToC4zA4yG2i2M38bHoUBY2yZzy5ckOkoP9xJ3XbekmzosdQU","PHONE":"17611228377","POINTS":"0","SHENHE":"0","TARGET":"1","XINGZUO":"天蝎座"},{"CREATEDATE":"2017-09-16 09:39:23","FANS_COUNT":"1","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/65ac3f1c4979494783e37c07478364fc/65ac3f1c4979494783e37c07478364fc.png","ID":"0a7239b00a234176a973de6f1385ce55","ISDELETE":"0","NICK_NAME":"@王尼玛","PASSWORD":"lP3/7WQqUULAUionrh0QOxZ790vdwA9Y4MgBf5DJQxlUQb+1UwFcbCrvl5Y=","PHONE":"15688526912","POINTS":"0","SHENHE":"0"},{"CREATEDATE":"2017-10-06 11:58:27","FANS_COUNT":"0","FOCUS_COUNT":"1","HEAD_PATH":"/upLoadResource/7418762f785d4d8ca8daa76efb9c6d35/7418762f785d4d8ca8daa76efb9c6d35.png","ID":"0bb4214ebb0a44d2ad97fd6128c2dea0","ISDELETE":"0","NICK_NAME":"大素素","PASSWORD":"1S1eV3lNzmVHupMidzzRNGO59oImsO6fAz/kfkqo2JwLkeRZP8kDT9TU4As=","PHONE":"15254770919","POINTS":"0"}]
     * total : 38
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
         * BIRTHDAY : 1988-01-14
         * CN : 陶禹臣
         * INTRODUCE : With a different perspective to see the world.
         * LOCAL_DRESS : 北京
         * TARGET :
         * XINGZUO : 魔羯座
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
