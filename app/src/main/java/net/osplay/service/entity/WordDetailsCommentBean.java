package net.osplay.service.entity;

import java.util.List;

/**
 * 分页查询一级评论及一级评论下默认显示的二级评论
 */

public class WordDetailsCommentBean {
    private List<OneBean> one;
    private List<TwoBean> two;
    private List<?> zan;

    public List<OneBean> getOne() {
        return one;
    }

    public void setOne(List<OneBean> one) {
        this.one = one;
    }

    public List<TwoBean> getTwo() {
        return two;
    }

    public void setTwo(List<TwoBean> two) {
        this.two = two;
    }

    public List<?> getZan() {
        return zan;
    }

    public void setZan(List<?> zan) {
        this.zan = zan;
    }

    public static class OneBean {
        /**
         * ISDELETE : 0
         * MEMBERID : 6cad9193fbb24a4694e2bf4f50b402bc
         * ZANCOUNT : 0
         * BEENMEMBERID : 1a73c69f9ad14e2193c477794105cd87
         * CREATEDATE : 2017-09-23 20:47:04
         * TOPICID : a98196e87c504cb6ade986a4e3b54efe
         * HEAD_PATH : /upLoadResource/29d2bc2f7bfd469aa4251d67f887de46/29d2bc2f7bfd469aa4251d67f887de46.png
         * NICK_NAME : 15563722426
         * ATID :
         * PARENTID : 0
         * ATUSERS :
         * ATUSERSID :
         * ID : ac59dbd95e804b36802e7238af81c2c7
         * CONTENT :  ←_←
         * COUNT : 2
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
        private int COUNT;

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

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
        }
    }

    public static class TwoBean {
        /**
         * ISDELETE : 0
         * MEMBERID : 6cad9193fbb24a4694e2bf4f50b402bc
         * ZANCOUNT : 1
         * BEENMEMBERID : 6cad9193fbb24a4694e2bf4f50b402bc
         * CREATEDATE : 2017-09-27 16:28:26
         * TOPICID : a98196e87c504cb6ade986a4e3b54efe
         * HEAD_PATH : /upLoadResource/29d2bc2f7bfd469aa4251d67f887de46/29d2bc2f7bfd469aa4251d67f887de46.png
         * NICK_NAME : 15563722426
         * ATID :
         * PARENTID : 645d4fc95c7f486a9625b5cd493158ef
         * ATUSERS :
         * ATUSERSID :
         * ID : d202f2d593c04dd79324556e1a6c2114
         * CONTENT :  o(≧v≦)o~~
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
