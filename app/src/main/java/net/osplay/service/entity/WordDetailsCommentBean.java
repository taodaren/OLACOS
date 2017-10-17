package net.osplay.service.entity;

import java.util.List;

/**
 * 分页查询一级评论及一级评论下默认显示的二级评论
 */

public class WordDetailsCommentBean {

    /**
     * one : [{"ISDELETE":"0","MEMBERID":"186c8f7fa58640f5885292da943d2acd","ZANCOUNT":"0","BEENMEMBERID":"3fcc53aeefcc46baa0f7f579ceed1c74","CREATEDATE":"2017-10-11 08:42:26","TOPICID":"31aee539e09b43b1b7d220a074b468bc","HEAD_PATH":"/upLoadResource/9b7de03d867d447eb7c2fcb9dc41693f/9b7de03d867d447eb7c2fcb9dc41693f.png","NICK_NAME":"Lancelot","ATID":"iYtLmLc1ifNqTAL6NysrRQO7vsBKPGJB","PARENTID":"0","ATUSERS":"15563722426","ATUSERSID":"6cad9193fbb24a4694e2bf4f50b402bc","ID":"62a66f1ae6234f6d968f87814f03a863","CONTENT":""},{"ISDELETE":"0","MEMBERID":"3fcc53aeefcc46baa0f7f579ceed1c74","ZANCOUNT":"1","BEENMEMBERID":"3fcc53aeefcc46baa0f7f579ceed1c74","CREATEDATE":"2017-10-13 14:34:54","TOPICID":"31aee539e09b43b1b7d220a074b468bc","HEAD_PATH":"/upLoadResource/21ce1890cebb4c4189bdb9bd3e3aebef/21ce1890cebb4c4189bdb9bd3e3aebef.png","NICK_NAME":"克里奥佩特拉","ATID":"","PARENTID":"0","ATUSERS":"","ATUSERSID":"","ID":"07a03b333dbd476f9fb6f84b1426375f","CONTENT":"好想要"}]
     * two :
     * zan :
     */

    private String two;
    private String zan;
    private List<OneBean> one;

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public List<OneBean> getOne() {
        return one;
    }

    public void setOne(List<OneBean> one) {
        this.one = one;
    }

    public static class OneBean {
        /**
         * ISDELETE : 0
         * MEMBERID : 186c8f7fa58640f5885292da943d2acd
         * ZANCOUNT : 0
         * BEENMEMBERID : 3fcc53aeefcc46baa0f7f579ceed1c74
         * CREATEDATE : 2017-10-11 08:42:26
         * TOPICID : 31aee539e09b43b1b7d220a074b468bc
         * HEAD_PATH : /upLoadResource/9b7de03d867d447eb7c2fcb9dc41693f/9b7de03d867d447eb7c2fcb9dc41693f.png
         * NICK_NAME : Lancelot
         * ATID : iYtLmLc1ifNqTAL6NysrRQO7vsBKPGJB
         * PARENTID : 0
         * ATUSERS : 15563722426
         * ATUSERSID : 6cad9193fbb24a4694e2bf4f50b402bc
         * ID : 62a66f1ae6234f6d968f87814f03a863
         * CONTENT :
         */

        private String ISDELETE;
        private String MEMBERID;
        private String ZANCOUNT;
        private String BEENMEMBERID;
        private String CREATEDATE;
        private String TOPICID;
        private String HEAD_PATH;
        private String NICK_NAME;
        private String ATID;
        private String PARENTID;
        private String ATUSERS;
        private String ATUSERSID;
        private String ID;
        private String CONTENT;

        public String getISDELETE() {
            return ISDELETE;
        }

        public void setISDELETE(String ISDELETE) {
            this.ISDELETE = ISDELETE;
        }

        public String getMEMBERID() {
            return MEMBERID;
        }

        public void setMEMBERID(String MEMBERID) {
            this.MEMBERID = MEMBERID;
        }

        public String getZANCOUNT() {
            return ZANCOUNT;
        }

        public void setZANCOUNT(String ZANCOUNT) {
            this.ZANCOUNT = ZANCOUNT;
        }

        public String getBEENMEMBERID() {
            return BEENMEMBERID;
        }

        public void setBEENMEMBERID(String BEENMEMBERID) {
            this.BEENMEMBERID = BEENMEMBERID;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getTOPICID() {
            return TOPICID;
        }

        public void setTOPICID(String TOPICID) {
            this.TOPICID = TOPICID;
        }

        public String getHEAD_PATH() {
            return HEAD_PATH;
        }

        public void setHEAD_PATH(String HEAD_PATH) {
            this.HEAD_PATH = HEAD_PATH;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getATID() {
            return ATID;
        }

        public void setATID(String ATID) {
            this.ATID = ATID;
        }

        public String getPARENTID() {
            return PARENTID;
        }

        public void setPARENTID(String PARENTID) {
            this.PARENTID = PARENTID;
        }

        public String getATUSERS() {
            return ATUSERS;
        }

        public void setATUSERS(String ATUSERS) {
            this.ATUSERS = ATUSERS;
        }

        public String getATUSERSID() {
            return ATUSERSID;
        }

        public void setATUSERSID(String ATUSERSID) {
            this.ATUSERSID = ATUSERSID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }
    }
}
