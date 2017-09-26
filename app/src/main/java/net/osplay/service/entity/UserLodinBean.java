package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public class UserLodinBean {

    /**
     * ok : true
     * member : [{"SCHOOL":"","ISDELETE":"0","SHENHE":"0","PHONE":"17611228377","CREATEDATE":"2017-09-12 10:46:12","STU_PATH":"","CN":"cSZXdcZX","NICK_NAME":"呆子","CARD":"622827199411061759","CARD_F_PATH":"/upLoadResource/e04bad69c8934abe86587da9028461e2/未标题-1(1).jpg","PASSWORD":"EryP//NCm+drNvl4WHrvIyUl2bt7AN5H46VbrRer/E3C9LwvndSg+dTdiD4=","FANS_COUNT":"0","CARD_B_PATH":"/upLoadResource/69886802c9784291b96db7db9e9254c7/需要修改的.jpg","ID":"69f1badc98cc441c838310561d11bcb7","FOCUS_COUNT":"0"}]
     */

    private String ok;
    private List<MemberBean> member;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public List<MemberBean> getMember() {
        return member;
    }

    public void setMember(List<MemberBean> member) {
        this.member = member;
    }

    public static class MemberBean {
        /**
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

        private String SCHOOL;
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

        public String getSCHOOL() {
            return SCHOOL;
        }

        public void setSCHOOL(String SCHOOL) {
            this.SCHOOL = SCHOOL;
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

        public String getSTU_PATH() {
            return STU_PATH;
        }

        public void setSTU_PATH(String STU_PATH) {
            this.STU_PATH = STU_PATH;
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

        public String getCARD() {
            return CARD;
        }

        public void setCARD(String CARD) {
            this.CARD = CARD;
        }

        public String getCARD_F_PATH() {
            return CARD_F_PATH;
        }

        public void setCARD_F_PATH(String CARD_F_PATH) {
            this.CARD_F_PATH = CARD_F_PATH;
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

        public String getCARD_B_PATH() {
            return CARD_B_PATH;
        }

        public void setCARD_B_PATH(String CARD_B_PATH) {
            this.CARD_B_PATH = CARD_B_PATH;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getFOCUS_COUNT() {
            return FOCUS_COUNT;
        }

        public void setFOCUS_COUNT(String FOCUS_COUNT) {
            this.FOCUS_COUNT = FOCUS_COUNT;
        }
    }
}
