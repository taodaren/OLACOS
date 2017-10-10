package net.osplay.service.entity;

import java.util.List;

public class MyCollectionBean {

    /**
     * rows : [{"BOUTIQUE":"1","COLLECT":"true","COLLECT_COUNT":"1","COVERIMG":"/upLoadResource/62aa85ec3b614e4ba989da44cfadb71f/【杂谈】20.《超人总动员》 平凡的你也是改变世界重要的齿轮 字数：1880169.png","CREATEDATE":"2017-09-16 17:33:09","HEAD_PATH":"/upLoadResource/2b46b52f1c9940e2b7b4d0fe37a0aa74/2b46b52f1c9940e2b7b4d0fe37a0aa74.png","ID":"e339c4c54d16453386a95a41ea0056bb","MEMID":"69f1badc98cc441c838310561d11bcb7","NICK_NAME":"呆子","PART":"电影","PINGLUN_COUNT":"0","TITLE":"《超人总动员》 平凡的你也是改变世界重要的齿轮","ZAN":"true","ZAN_COUNT":"1"},{"BOUTIQUE":"1","COLLECT":"true","COLLECT_COUNT":"1","COVERIMG":"/upLoadResource/6dc4189a004d4da194e88543cba279c4/timg.jpg","CREATEDATE":"2017-09-16 18:37:40","ID":"cb8fd9a8c2a0408cada2fcacf5eaf401","MEMID":"4a891cd377c84ef783cd145990048995","NICK_NAME":"我爱徐倩楠","PART":"综合周边","PINGLUN_COUNT":"0","TITLE":"我真是受不了现在淘宝卖家的脑洞了","ZAN":"true","ZAN_COUNT":"1"}]
     * total : 9
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
         * BOUTIQUE : 1
         * COLLECT : true
         * COLLECT_COUNT : 1
         * COVERIMG : /upLoadResource/62aa85ec3b614e4ba989da44cfadb71f/【杂谈】20.《超人总动员》 平凡的你也是改变世界重要的齿轮 字数：1880169.png
         * CREATEDATE : 2017-09-16 17:33:09
         * HEAD_PATH : /upLoadResource/2b46b52f1c9940e2b7b4d0fe37a0aa74/2b46b52f1c9940e2b7b4d0fe37a0aa74.png
         * ID : e339c4c54d16453386a95a41ea0056bb
         * MEMID : 69f1badc98cc441c838310561d11bcb7
         * NICK_NAME : 呆子
         * PART : 电影
         * PINGLUN_COUNT : 0
         * TITLE : 《超人总动员》 平凡的你也是改变世界重要的齿轮
         * ZAN : true
         * ZAN_COUNT : 1
         */

        private String BOUTIQUE;
        private String COLLECT;
        private String COLLECT_COUNT;
        private String COVERIMG;
        private String CREATEDATE;
        private String HEAD_PATH;
        private String ID;
        private String MEMID;
        private String NICK_NAME;
        private String PART;
        private String PINGLUN_COUNT;
        private String TITLE;
        private String ZAN;
        private String ZAN_COUNT;

        public String getBOUTIQUE() {
            return BOUTIQUE;
        }

        public void setBOUTIQUE(String BOUTIQUE) {
            this.BOUTIQUE = BOUTIQUE;
        }

        public String getCOLLECT() {
            return COLLECT;
        }

        public void setCOLLECT(String COLLECT) {
            this.COLLECT = COLLECT;
        }

        public String getCOLLECT_COUNT() {
            return COLLECT_COUNT;
        }

        public void setCOLLECT_COUNT(String COLLECT_COUNT) {
            this.COLLECT_COUNT = COLLECT_COUNT;
        }

        public String getCOVERIMG() {
            return COVERIMG;
        }

        public void setCOVERIMG(String COVERIMG) {
            this.COVERIMG = COVERIMG;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
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

        public String getMEMID() {
            return MEMID;
        }

        public void setMEMID(String MEMID) {
            this.MEMID = MEMID;
        }

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        public String getPART() {
            return PART;
        }

        public void setPART(String PART) {
            this.PART = PART;
        }

        public String getPINGLUN_COUNT() {
            return PINGLUN_COUNT;
        }

        public void setPINGLUN_COUNT(String PINGLUN_COUNT) {
            this.PINGLUN_COUNT = PINGLUN_COUNT;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getZAN() {
            return ZAN;
        }

        public void setZAN(String ZAN) {
            this.ZAN = ZAN;
        }

        public String getZAN_COUNT() {
            return ZAN_COUNT;
        }

        public void setZAN_COUNT(String ZAN_COUNT) {
            this.ZAN_COUNT = ZAN_COUNT;
        }
    }
}
