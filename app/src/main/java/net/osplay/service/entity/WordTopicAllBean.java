package net.osplay.service.entity;

import java.util.List;

/**
 * 社区帖子详情--全部
 */

public class WordTopicAllBean {

    /**
     * total : 27
     * rows : [{"PINGLUN_COUNT":"1","COVERIMG":"/upLoadResource/2a40340a5c06479caeea39f8007dda4b/1496453261173.jpg","BOUTIQUE":"1","ZAN_COUNT":"1","ZAN":"false","CREATEDATE":"2017-09-21 18:59:40","COLLECT_COUNT":"2","USERID":"3f68feea89784e20873f1de2e694880c","COLLECT":"false","TITLE":"问问","ID":"7517c971cdf94713b33f66472ef9ad39","NICK_NAME":"雪颜er"},{"COVERIMG":"/upLoadResource/24447a99880c40f7aab44e2fd44217c5/QQ截图20170901095901.jpg","BOUTIQUE":"1","ZAN_COUNT":"1","ZAN":"false","CREATEDATE":"2017-09-16 18:37:02","COLLECT":"false","HEAD_PATH":"/upLoadResource/f54acfc07faa465a8ce61366eefb6a9e/f54acfc07faa465a8ce61366eefb6a9e.png","NICK_NAME":"江","PINGLUN_COUNT":"3","COLLECT_COUNT":"6","USERID":"667b6b89c10f41c5aba9980fa47c8b76","TITLE":"sdfsdf","ID":"2aa2027c86f545ba954d6923d24b5817"}]
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
         * PINGLUN_COUNT : 1
         * COVERIMG : /upLoadResource/2a40340a5c06479caeea39f8007dda4b/1496453261173.jpg
         * BOUTIQUE : 1
         * ZAN_COUNT : 1
         * ZAN : false
         * CREATEDATE : 2017-09-21 18:59:40
         * COLLECT_COUNT : 2
         * USERID : 3f68feea89784e20873f1de2e694880c
         * COLLECT : false
         * TITLE : 问问
         * ID : 7517c971cdf94713b33f66472ef9ad39
         * NICK_NAME : 雪颜er
         * HEAD_PATH : /upLoadResource/f54acfc07faa465a8ce61366eefb6a9e/f54acfc07faa465a8ce61366eefb6a9e.png
         */

        private String PINGLUN_COUNT;
        private String COVERIMG;
        private String BOUTIQUE;
        private String ZAN_COUNT;
        private String ZAN;
        private String CREATEDATE;
        private String COLLECT_COUNT;
        private String USERID;
        private String COLLECT;
        private String TITLE;
        private String ID;
        private String NICK_NAME;
        private String HEAD_PATH;

        public String getPINGLUN_COUNT() {
            return PINGLUN_COUNT;
        }

        public void setPINGLUN_COUNT(String PINGLUN_COUNT) {
            this.PINGLUN_COUNT = PINGLUN_COUNT;
        }

        public String getCOVERIMG() {
            return COVERIMG;
        }

        public void setCOVERIMG(String COVERIMG) {
            this.COVERIMG = COVERIMG;
        }

        public String getBOUTIQUE() {
            return BOUTIQUE;
        }

        public void setBOUTIQUE(String BOUTIQUE) {
            this.BOUTIQUE = BOUTIQUE;
        }

        public String getZAN_COUNT() {
            return ZAN_COUNT;
        }

        public void setZAN_COUNT(String ZAN_COUNT) {
            this.ZAN_COUNT = ZAN_COUNT;
        }

        public String getZAN() {
            return ZAN;
        }

        public void setZAN(String ZAN) {
            this.ZAN = ZAN;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getCOLLECT_COUNT() {
            return COLLECT_COUNT;
        }

        public void setCOLLECT_COUNT(String COLLECT_COUNT) {
            this.COLLECT_COUNT = COLLECT_COUNT;
        }

        public String getUSERID() {
            return USERID;
        }

        public void setUSERID(String USERID) {
            this.USERID = USERID;
        }

        public String getCOLLECT() {
            return COLLECT;
        }

        public void setCOLLECT(String COLLECT) {
            this.COLLECT = COLLECT;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getHEAD_PATH() {
            return HEAD_PATH;
        }

        public void setHEAD_PATH(String HEAD_PATH) {
            this.HEAD_PATH = HEAD_PATH;
        }
    }
}
