package net.osplay.service.entity;

import java.util.List;

/**
 * 专区分区帖子列表实体类
 */

public class WordTopicListBean {

    /**
     * total : 1
     * rows : [{"PINGLUN_COUNT":"0","COVERIMG":"/upLoadResource/34520819a194447584a9fb768618053b/木道公社-轻武器（枪械类道具）2439.png","BOUTIQUE":"0","ZAN_COUNT":"0","ZAN":"false","CREATEDATE":"2017-09-16 18:28:14","COLLECT_COUNT":"1","USERID":"9e23d87dc3e2446396998c0f40426727","COLLECT":"false","TITLE":"【展示】木道公社-轻武器（枪械类道具）","ID":"984a7a46016c426c8883893f7e850e1a","NICK_NAME":"taodaren"}]
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
         * PINGLUN_COUNT : 0
         * COVERIMG : /upLoadResource/34520819a194447584a9fb768618053b/木道公社-轻武器（枪械类道具）2439.png
         * BOUTIQUE : 0
         * ZAN_COUNT : 0
         * ZAN : false
         * CREATEDATE : 2017-09-16 18:28:14
         * COLLECT_COUNT : 1
         * USERID : 9e23d87dc3e2446396998c0f40426727
         * COLLECT : false
         * TITLE : 【展示】木道公社-轻武器（枪械类道具）
         * ID : 984a7a46016c426c8883893f7e850e1a
         * NICK_NAME : taodaren
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
    }
}
