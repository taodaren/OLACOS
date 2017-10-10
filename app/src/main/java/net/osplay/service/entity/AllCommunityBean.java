package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class AllCommunityBean {
    /**
     * rows : [{"AUTOGRAPH":"解救不开心","BACKGROUND":"/upLoadResource/1/1.jpg","CREATEDATE":"2017-9-30 22:01:03","CREATEID":"1","HEADID":"1","ID":"1","INTRODUCTION":"让工作都见鬼去吧！哈哈哈哈~","ISDELETE":"0","ISEXAMINE":"1","NAME":"开心一刻","PHOTO":"/upLoadResource/1/2.jpg","REASON":"没有原因，就是想申请，任性！","REGION":"北京"},{"CREATEDATE":"2017-10-10 13:26:45","CREATEID":"69f1badc98cc441c838310561d11bcb7","HEADID":"69f1badc98cc441c838310561d11bcb7","ID":"ded2c2c4286b4a7faf8956d980ee185b","ISDELETE":"0","ISEXAMINE":"2","NAME":"oooo","REASON":"无理由","REGION":"北京"}]
     * total : 2
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
         * AUTOGRAPH : 解救不开心
         * BACKGROUND : /upLoadResource/1/1.jpg
         * CREATEDATE : 2017-9-30 22:01:03
         * CREATEID : 1
         * HEADID : 1
         * ID : 1
         * INTRODUCTION : 让工作都见鬼去吧！哈哈哈哈~
         * ISDELETE : 0
         * ISEXAMINE : 1
         * NAME : 开心一刻
         * PHOTO : /upLoadResource/1/2.jpg
         * REASON : 没有原因，就是想申请，任性！
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


//    private int icon;
//    private String name;
//
//    public AllCommunityBean() {
//    }
//
//    public AllCommunityBean(int icon, String name) {
//        this.icon = icon;
//        this.name = name;
//    }
//
//    public int getIcon() {
//        return icon;
//    }
//
//    public void setIcon(int icon) {
//        this.icon = icon;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
