package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class AssociationInfoBean {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * AUTOGRAPH : 撸到天亮
         * BACKGROUND : /upLoadResource/706296e297ad4f2f94ff67b0347477b3/mmexport1507132049167.jpg
         * CREATEDATE : 2017-10-12 19:49:55
         * CREATEID : 14138d0f8a844f51a50409d646098ea0
         * HEADID : 14138d0f8a844f51a50409d646098ea0
         * ID : ad3ffcb74f0b427982bfb10615f8da0b
         * INTRODUCTION : android技术交流
         * ISDELETE : 0
         * ISEXAMINE : 1
         * NAME : IT
         * PHOTO : /upLoadResource/6f536453aedc4964bd38e8279e9b8963/IMG_20171009_130310.jpg
         * REASON : 技术交流，提升水平
         * REGION : 北京
         */

        private String AUTOGRAPH;
        private String BACKGROUND;
        private String CREATEDATE;
        private String CREATEID;
        private String HEADID;
        private String ID;
        private String INTRODUCTION;
        private String ISDELETE;
        private String ISEXAMINE;
        private String NAME;
        private String PHOTO;
        private String REASON;
        private String REGION;

        public String getAUTOGRAPH() {
            return AUTOGRAPH;
        }

        public void setAUTOGRAPH(String AUTOGRAPH) {
            this.AUTOGRAPH = AUTOGRAPH;
        }

        public String getBACKGROUND() {
            return BACKGROUND;
        }

        public void setBACKGROUND(String BACKGROUND) {
            this.BACKGROUND = BACKGROUND;
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

        public String getHEADID() {
            return HEADID;
        }

        public void setHEADID(String HEADID) {
            this.HEADID = HEADID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getINTRODUCTION() {
            return INTRODUCTION;
        }

        public void setINTRODUCTION(String INTRODUCTION) {
            this.INTRODUCTION = INTRODUCTION;
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

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getPHOTO() {
            return PHOTO;
        }

        public void setPHOTO(String PHOTO) {
            this.PHOTO = PHOTO;
        }

        public String getREASON() {
            return REASON;
        }

        public void setREASON(String REASON) {
            this.REASON = REASON;
        }

        public String getREGION() {
            return REGION;
        }

        public void setREGION(String REGION) {
            this.REGION = REGION;
        }
    }
}
