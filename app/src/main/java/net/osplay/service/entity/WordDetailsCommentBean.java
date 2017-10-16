package net.osplay.service.entity;

import java.util.List;

/**
 * 分页查询一级评论及一级评论下默认显示的二级评论
 */

public class WordDetailsCommentBean {

    /**
     * one : [{"ISDELETE":"0","MEMBERID":"69f1badc98cc441c838310561d11bcb7","ZANCOUNT":"0","BEENMEMBERID":"9e23d87dc3e2446396998c0f40426727","CREATEDATE":"2017-09-28 21:49:25","TOPICID":"faa555050fd74f9a9944405b6b42c573","HEAD_PATH":"/upLoadResource/2552c21ae0184de1aa52b86a657aa1b3/2552c21ae0184de1aa52b86a657aa1b3.png","NICK_NAME":"呆子","ATID":"","PARENTID":"0","ATUSERS":"","ATUSERSID":"","COUNT":3,"ID":"541cde0c7d784f80b4291f494c837443","CONTENT":"6666"}]
     * two : [{"ISDELETE":"0","MEMBERID":"9e23d87dc3e2446396998c0f40426727","ZANCOUNT":"0","BEENMEMBERID":"69f1badc98cc441c838310561d11bcb7","CREATEDATE":"2017-09-29 14:38:39","TOPICID":"faa555050fd74f9a9944405b6b42c573","HEAD_PATH":"/upLoadResource/a4f8274328894acb90323230255eaac3/a4f8274328894acb90323230255eaac3.png","NICK_NAME":"taodaren","ATID":"","PARENTID":"541cde0c7d784f80b4291f494c837443","ATUSERS":"","ATUSERSID":"","ID":"41b5b06f48ed4f9b8ed71cab60f3f98b","CONTENT":"2333333333"},{"ISDELETE":"0","MEMBERID":"6cad9193fbb24a4694e2bf4f50b402bc","ZANCOUNT":"0","BEENMEMBERID":"9e23d87dc3e2446396998c0f40426727","CREATEDATE":"2017-10-08 10:06:06","TOPICID":"faa555050fd74f9a9944405b6b42c573","HEAD_PATH":"/upLoadResource/29d2bc2f7bfd469aa4251d67f887de46/29d2bc2f7bfd469aa4251d67f887de46.png","NICK_NAME":"15563722426","ATID":"","PARENTID":"541cde0c7d784f80b4291f494c837443","ATUSERS":"","ATUSERSID":"","ID":"c04dc57a35724b179ce08d6c225c5fc5","CONTENT":"回复 @taodaren   哈哈哈"},{"ISDELETE":"0","MEMBERID":"6cad9193fbb24a4694e2bf4f50b402bc","ZANCOUNT":"0","BEENMEMBERID":"69f1badc98cc441c838310561d11bcb7","CREATEDATE":"2017-10-08 10:06:12","TOPICID":"faa555050fd74f9a9944405b6b42c573","HEAD_PATH":"/upLoadResource/29d2bc2f7bfd469aa4251d67f887de46/29d2bc2f7bfd469aa4251d67f887de46.png","NICK_NAME":"15563722426","ATID":"","PARENTID":"541cde0c7d784f80b4291f494c837443","ATUSERS":"","ATUSERSID":"","ID":"5e06c218896249eb89494a18ec391f86","CONTENT":"哈哈哈哈"}]
     * zan :
     */

    private String zan;//用户点赞的一级和二级评论评论的id（查询出的数据就是用户点赞的评论的id）
    private List<OneBean> one;//一级评论内容
    private List<TwoBean> two;//二级评论内容

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

    public List<TwoBean> getTwo() {
        return two;
    }

    public void setTwo(List<TwoBean> two) {
        this.two = two;
    }

    public static class OneBean {
        /**
         * ISDELETE : 0
         * MEMBERID : 69f1badc98cc441c838310561d11bcb7
         * ZANCOUNT : 0
         * BEENMEMBERID : 9e23d87dc3e2446396998c0f40426727
         * CREATEDATE : 2017-09-28 21:49:25
         * TOPICID : faa555050fd74f9a9944405b6b42c573
         * HEAD_PATH : /upLoadResource/2552c21ae0184de1aa52b86a657aa1b3/2552c21ae0184de1aa52b86a657aa1b3.png
         * NICK_NAME : 呆子
         * ATID :
         * PARENTID : 0
         * ATUSERS :
         * ATUSERSID :
         * COUNT : 3
         * ID : 541cde0c7d784f80b4291f494c837443
         * CONTENT : 6666
         */

        private String ISDELETE;
        private String MEMBERID;//评论人id
        private String ZANCOUNT;//评论点赞数
        private String BEENMEMBERID;//被回复人id（指作者）
        private String CREATEDATE;//评论时间
        private String TOPICID;//帖子id
        private String HEAD_PATH;//评论人头像
        private String NICK_NAME;//评论人昵称
        private String ATID;//at表的id
        private String PARENTID;//"0"代表一级评论
        private String ATUSERS;//@的用户昵称，昵称中间用","隔开
        private String ATUSERSID;//@的用户id,中间用","隔开与ATUSERS顺序一致
        private int COUNT;//当前一级评论下的二级评论数量（注：是数字类型非字符串）
        private String ID;//评论id
        private String CONTENT;//评论内容

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

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
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

    public static class TwoBean {
        /**
         * ISDELETE : 0
         * MEMBERID : 9e23d87dc3e2446396998c0f40426727
         * ZANCOUNT : 0
         * BEENMEMBERID : 69f1badc98cc441c838310561d11bcb7
         * CREATEDATE : 2017-09-29 14:38:39
         * TOPICID : faa555050fd74f9a9944405b6b42c573
         * HEAD_PATH : /upLoadResource/a4f8274328894acb90323230255eaac3/a4f8274328894acb90323230255eaac3.png
         * NICK_NAME : taodaren
         * ATID :
         * PARENTID : 541cde0c7d784f80b4291f494c837443
         * ATUSERS :
         * ATUSERSID :
         * ID : 41b5b06f48ed4f9b8ed71cab60f3f98b
         * CONTENT : 2333333333
         */

        private String ISDELETE;
        private String MEMBERID;//评论人id
        private String ZANCOUNT;//评论点赞数
        private String BEENMEMBERID;//被回复人id
        private String CREATEDATE;//评论日期
        private String TOPICID;//帖子id
        private String HEAD_PATH;//评论人头像
        private String NICK_NAME;//评论人昵称
        private String ATID;//@表id
        private String PARENTID;//父评论id（遍历一级评论，直到找到一级评论的id等于PARENTID）
        private String ATUSERS;//@的所有用户，用","隔开
        private String ATUSERSID;//@的用户id,用","隔开
        private String ID;//二级评论ID
        private String CONTENT;//评论内容

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
