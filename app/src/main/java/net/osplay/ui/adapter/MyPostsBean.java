package net.osplay.ui.adapter;

import java.util.List;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyPostsBean {

    /**
     * total : 25
     * rows : [{"COVERIMG":"/upLoadResource/c2e8595799f14ab39649b51e910e805e/图片2.png","BOUTIQUE":"1","PART":"老番","CREATEDATE":"2017-09-16 16:20:18","TITLE":"动漫人物大变脸 让网友最有感的四大天王出炉惹","HEAD_PATH":"/upLoadResource/2b46b52f1c9940e2b7b4d0fe37a0aa74/2b46b52f1c9940e2b7b4d0fe37a0aa74.png","ID":"03e42e4dd61b46bb825dcce71ac3a056","NICK_NAME":"呆子"},{"COVERIMG":"/upLoadResource/c4aa9c952e9c47dba3f189206c20a280/timg (2).jpg","BOUTIQUE":"1","PART":"零食","CREATEDATE":"2017-09-16 15:46:03","TITLE":"各种零食推荐","HEAD_PATH":"/upLoadResource/2b46b52f1c9940e2b7b4d0fe37a0aa74/2b46b52f1c9940e2b7b4d0fe37a0aa74.png","ID":"0c89c9821a2a46bda31b0430527ba78c","NICK_NAME":"呆子"}]
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
         * COVERIMG : /upLoadResource/c2e8595799f14ab39649b51e910e805e/图片2.png
         * BOUTIQUE : 1
         * PART : 老番
         * CREATEDATE : 2017-09-16 16:20:18
         * TITLE : 动漫人物大变脸 让网友最有感的四大天王出炉惹
         * HEAD_PATH : /upLoadResource/2b46b52f1c9940e2b7b4d0fe37a0aa74/2b46b52f1c9940e2b7b4d0fe37a0aa74.png
         * ID : 03e42e4dd61b46bb825dcce71ac3a056
         * NICK_NAME : 呆子
         */

        private String COVERIMG;
        private String BOUTIQUE;
        private String PART;
        private String CREATEDATE;
        private String TITLE;
        private String HEAD_PATH;
        private String ID;
        private String NICK_NAME;

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

        public String getPART() {
            return PART;
        }

        public void setPART(String PART) {
            this.PART = PART;
        }

        public String getCREATEDATE() {
            return CREATEDATE;
        }

        public void setCREATEDATE(String CREATEDATE) {
            this.CREATEDATE = CREATEDATE;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
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

        public String getNICK_NAME() {
            return NICK_NAME;
        }

        public void setNICK_NAME(String NICK_NAME) {
            this.NICK_NAME = NICK_NAME;
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "COVERIMG='" + COVERIMG + '\'' +
                    ", BOUTIQUE='" + BOUTIQUE + '\'' +
                    ", PART='" + PART + '\'' +
                    ", CREATEDATE='" + CREATEDATE + '\'' +
                    ", TITLE='" + TITLE + '\'' +
                    ", HEAD_PATH='" + HEAD_PATH + '\'' +
                    ", ID='" + ID + '\'' +
                    ", NICK_NAME='" + NICK_NAME + '\'' +
                    '}';
        }
    }
}
