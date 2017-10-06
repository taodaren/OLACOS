package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/6.
 */

public class MyFansBean {

    /**
     * rows : [{"FANSID":"69f1badc98cc441c838310561d11bcb7","FANS_COUNT":"1","FOCUS_COUNT":"5","GZ":"true","HEAD_PATH":"/upLoadResource/02ecf8544c414d598f342d969c4164bd/02ecf8544c414d598f342d969c4164bd.png","ID":"6cad9193fbb24a4694e2bf4f50b402bc","INTRODUCE":"哈哈哈哈哈","NICK_NAME":"15563722426"}]
     * total : 1
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
         * FANSID : 69f1badc98cc441c838310561d11bcb7
         * FANS_COUNT : 1
         * FOCUS_COUNT : 5
         * GZ : true
         * HEAD_PATH : /upLoadResource/02ecf8544c414d598f342d969c4164bd/02ecf8544c414d598f342d969c4164bd.png
         * ID : 6cad9193fbb24a4694e2bf4f50b402bc
         * INTRODUCE : 哈哈哈哈哈
         * NICK_NAME : 15563722426
         */

        private String FANSID;
        private String FANS_COUNT;
        private String FOCUS_COUNT;
        private String GZ;
        private String HEAD_PATH;
        private String ID;
        private String INTRODUCE;
        private String NICK_NAME;

        public String getFANSID() {
            return FANSID;
        }

        public void setFANSID(String FANSID) {
            this.FANSID = FANSID;
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

        public String getGZ() {
            return GZ;
        }

        public void setGZ(String GZ) {
            this.GZ = GZ;
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

        public String getINTRODUCE() {
            return INTRODUCE;
        }

        public void setINTRODUCE(String INTRODUCE) {
            this.INTRODUCE = INTRODUCE;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }
    }
}
