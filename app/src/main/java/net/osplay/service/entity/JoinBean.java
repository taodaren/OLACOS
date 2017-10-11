package net.osplay.service.entity;

import java.util.List;

/**
 * 加入过社团
 */

public class JoinBean {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * CORPORATIONID : 1
         * CREATEDATE : 2017-10-11 10:26:21
         * CREATEID : 69f1badc98cc441c838310561d11bcb7
         * ID : 9ec69bdec0fb4348b245064921e6416a
         * ISADMIN : 0
         * ISDELETE : 0
         * ISEXAMINE : 0
         * MEMBERID : 69f1badc98cc441c838310561d11bcb7
         */

        private String CORPORATIONID;
        private String CREATEDATE;
        private String CREATEID;
        private String ID;
        private String ISADMIN;
        private String ISDELETE;
        private String ISEXAMINE;
        private String MEMBERID;

        public String getCORPORATIONID() {
            return CORPORATIONID;
        }

        public void setCORPORATIONID(String CORPORATIONID) {
            this.CORPORATIONID = CORPORATIONID;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getCREATEID() {
            return CREATEID;
        }

        public void setCREATEID(String CREATEID) {
            this.CREATEID = CREATEID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getISADMIN() {
            return ISADMIN;
        }

        public void setISADMIN(String ISADMIN) {
            this.ISADMIN = ISADMIN;
        }

        public String getISDELETE() {
            return ISDELETE;
        }

        public void setISDELETE(String ISDELETE) {
            this.ISDELETE = ISDELETE;
        }

        public String getISEXAMINE() {
            return ISEXAMINE;
        }

        public void setISEXAMINE(String ISEXAMINE) {
            this.ISEXAMINE = ISEXAMINE;
        }

        public String getMEMBERID() {
            return MEMBERID;
        }

        public void setMEMBERID(String MEMBERID) {
            this.MEMBERID = MEMBERID;
        }
        @Override
        public String toString() {
            return "RowsBean{" +
                    "CORPORATIONID='" + CORPORATIONID + '\'' +
                    ", CREATEDATE='" + CREATEDATE + '\'' +
                    ", CREATEID='" + CREATEID + '\'' +
                    ", ID='" + ID + '\'' +
                    ", ISADMIN='" + ISADMIN + '\'' +
                    ", ISDELETE='" + ISDELETE + '\'' +
                    ", ISEXAMINE='" + ISEXAMINE + '\'' +
                    ", MEMBERID='" + MEMBERID + '\'' +
                    '}';
        }
    }
}
