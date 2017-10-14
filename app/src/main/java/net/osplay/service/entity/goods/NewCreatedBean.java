package net.osplay.service.entity.goods;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14.
 */

public class NewCreatedBean {

    /**
     * total : 1
     * rows : [{"ISDELETE":"0","ISEXAMINE":"1","CREATEDATE":"2017-10-13 15:13:14","BACKGROUND":"/upLoadResource/1fd3bdc627684bd796109efa2abbfb6c/img-8783f55a3f59617ddb43676ba8a50c4b.jpg","HDCOUNT":0,"NAME":"银河火箭队","REGION":"北京大兴","CREATEID":"14138d0f8a844f51a50409d646098ea0","ZPCOUNT":0,"AUTOGRAPH":"我们是穿梭在银河的火箭队","INTRODUCTION":"我们是穿梭在银河的火箭队","ID":"a4215d1f62b940cab98152a88ccb0419","PHOTO":"/upLoadResource/4be3880885054106adee0bad48625749/img-f3177a6446c286f30825befb4b515f3c.jpg","HEADID":"14138d0f8a844f51a50409d646098ea0","REASON":"解救全人类","MEMBERCOUNT":0}]
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
         * ISDELETE : 0
         * ISEXAMINE : 1
         * CREATEDATE : 2017-10-13 15:13:14
         * BACKGROUND : /upLoadResource/1fd3bdc627684bd796109efa2abbfb6c/img-8783f55a3f59617ddb43676ba8a50c4b.jpg
         * HDCOUNT : 0
         * NAME : 银河火箭队
         * REGION : 北京大兴
         * CREATEID : 14138d0f8a844f51a50409d646098ea0
         * ZPCOUNT : 0
         * AUTOGRAPH : 我们是穿梭在银河的火箭队
         * INTRODUCTION : 我们是穿梭在银河的火箭队
         * ID : a4215d1f62b940cab98152a88ccb0419
         * PHOTO : /upLoadResource/4be3880885054106adee0bad48625749/img-f3177a6446c286f30825befb4b515f3c.jpg
         * HEADID : 14138d0f8a844f51a50409d646098ea0
         * REASON : 解救全人类
         * MEMBERCOUNT : 0
         */

        private String ISDELETE;
        private String ISEXAMINE;
        private String CREATEDATE;
        private String BACKGROUND;
        private int HDCOUNT;
        private String NAME;
        private String REGION;
        private String CREATEID;
        private int ZPCOUNT;
        private String AUTOGRAPH;
        private String INTRODUCTION;
        private String ID;
        private String PHOTO;
        private String HEADID;
        private String REASON;
        private int MEMBERCOUNT;

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

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getBACKGROUND() {
            return BACKGROUND;
        }

        public void setBACKGROUND(String BACKGROUND) {
            this.BACKGROUND = BACKGROUND;
        }

        public int getHDCOUNT() {
            return HDCOUNT;
        }

        public void setHDCOUNT(int HDCOUNT) {
            this.HDCOUNT = HDCOUNT;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getREGION() {
            return REGION;
        }

        public void setREGION(String REGION) {
            this.REGION = REGION;
        }

        public String getCREATEID() {
            return CREATEID;
        }

        public void setCREATEID(String CREATEID) {
            this.CREATEID = CREATEID;
        }

        public int getZPCOUNT() {
            return ZPCOUNT;
        }

        public void setZPCOUNT(int ZPCOUNT) {
            this.ZPCOUNT = ZPCOUNT;
        }

        public String getAUTOGRAPH() {
            return AUTOGRAPH;
        }

        public void setAUTOGRAPH(String AUTOGRAPH) {
            this.AUTOGRAPH = AUTOGRAPH;
        }

        public String getINTRODUCTION() {
            return INTRODUCTION;
        }

        public void setINTRODUCTION(String INTRODUCTION) {
            this.INTRODUCTION = INTRODUCTION;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPHOTO() {
            return PHOTO;
        }

        public void setPHOTO(String PHOTO) {
            this.PHOTO = PHOTO;
        }

        public String getHEADID() {
            return HEADID;
        }

        public void setHEADID(String HEADID) {
            this.HEADID = HEADID;
        }

        public String getREASON() {
            return REASON;
        }

        public void setREASON(String REASON) {
            this.REASON = REASON;
        }

        public int getMEMBERCOUNT() {
            return MEMBERCOUNT;
        }

        public void setMEMBERCOUNT(int MEMBERCOUNT) {
            this.MEMBERCOUNT = MEMBERCOUNT;
        }
    }
}
