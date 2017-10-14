package net.osplay.service.entity;

import java.util.List;

/**
 * Created by acer-PC on 2017/9/1.
 */

public class RecommendBean {

    /**
     * total : 1
     * rows : [{"NAME":"开心一刻","BACKGROUND":"/upLoadResource/1/1.jpg","MARK":"1","corporationID":"1","SH_COUNT":"11","LASTUPDATEID":"1","CREATEDATE":"2017-10-11 23:24:46","CONTENT":"1111","COLLECT_COUNT":"1","ZAN_COUNT":"11","INTRODUCTION":"让工作都见鬼去吧！哈哈哈哈~","ISDELETE":"0","ID":"1","LASTUPDATEDATE":"11","PL_COUNT":"11","BM_COUNT":"1","AUTOGRAPH":"解救不开心","PHOTO":"/upLoadResource/1/2.jpg","TITLE":"11"}]
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
         * NAME : 开心一刻
         * BACKGROUND : /upLoadResource/1/1.jpg
         * MARK : 1
         * corporationID : 1
         * SH_COUNT : 11
         * LASTUPDATEID : 1
         * CREATEDATE : 2017-10-11 23:24:46
         * CONTENT : 1111
         * COLLECT_COUNT : 1
         * ZAN_COUNT : 11
         * INTRODUCTION : 让工作都见鬼去吧！哈哈哈哈~
         * ISDELETE : 0
         * ID : 1
         * LASTUPDATEDATE : 11
         * PL_COUNT : 11
         * BM_COUNT : 1
         * AUTOGRAPH : 解救不开心
         * PHOTO : /upLoadResource/1/2.jpg
         * TITLE : 11
         */

        private String NAME;
        private String BACKGROUND;
        private String MARK;
        private String corporationID;
        private String SH_COUNT;
        private String LASTUPDATEID;
        private String CREATEDATE;
        private String CONTENT;
        private String COLLECT_COUNT;
        private String ZAN_COUNT;
        private String INTRODUCTION;
        private String ISDELETE;
        private String ID;
        private String LASTUPDATEDATE;
        private String PL_COUNT;
        private String BM_COUNT;
        private String AUTOGRAPH;
        private String PHOTO;
        private String TITLE;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getBACKGROUND() {
            return BACKGROUND;
        }

        public void setBACKGROUND(String BACKGROUND) {
            this.BACKGROUND = BACKGROUND;
        }

        public String getMARK() {
            return MARK;
        }

        public void setMARK(String MARK) {
            this.MARK = MARK;
        }

        public String getCorporationID() {
            return corporationID;
        }

        public void setCorporationID(String corporationID) {
            this.corporationID = corporationID;
        }

        public String getSH_COUNT() {
            return SH_COUNT;
        }

        public void setSH_COUNT(String SH_COUNT) {
            this.SH_COUNT = SH_COUNT;
        }

        public String getLASTUPDATEID() {
            return LASTUPDATEID;
        }

        public void setLASTUPDATEID(String LASTUPDATEID) {
            this.LASTUPDATEID = LASTUPDATEID;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getCONTENT() {
            return CONTENT;
        }

        public void setCONTENT(String CONTENT) {
            this.CONTENT = CONTENT;
        }

        public String getCOLLECT_COUNT() {
            return COLLECT_COUNT;
        }

        public void setCOLLECT_COUNT(String COLLECT_COUNT) {
            this.COLLECT_COUNT = COLLECT_COUNT;
        }

        public String getZAN_COUNT() {
            return ZAN_COUNT;
        }

        public void setZAN_COUNT(String ZAN_COUNT) {
            this.ZAN_COUNT = ZAN_COUNT;
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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLASTUPDATEDATE() {
            return LASTUPDATEDATE;
        }

        public void setLASTUPDATEDATE(String LASTUPDATEDATE) {
            this.LASTUPDATEDATE = LASTUPDATEDATE;
        }

        public String getPL_COUNT() {
            return PL_COUNT;
        }

        public void setPL_COUNT(String PL_COUNT) {
            this.PL_COUNT = PL_COUNT;
        }

        public String getBM_COUNT() {
            return BM_COUNT;
        }

        public void setBM_COUNT(String BM_COUNT) {
            this.BM_COUNT = BM_COUNT;
        }

        public String getAUTOGRAPH() {
            return AUTOGRAPH;
        }

        public void setAUTOGRAPH(String AUTOGRAPH) {
            this.AUTOGRAPH = AUTOGRAPH;
        }

        public String getPHOTO() {
            return PHOTO;
        }

        public void setPHOTO(String PHOTO) {
            this.PHOTO = PHOTO;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }
    }
}
