package net.osplay.ui.adapter;

import java.util.List;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyPostsBean {


    /**
     * rows : [{"BOUTIQUE":"1","COLLECT_COUNT":"0","COVERIMG":"/upLoadResource/4f0fcde73a244e37a3f14d7d855cf38b/【资讯】24.《少女与战车》手游推出多位角色泳装形象福利 字数：461110.png","CREATEDATE":"2017-09-16 17:41:57","HEAD_PATH":"/upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png","ID":"007d551712ca431d81fb6086df25c9ed","NICK_NAME":"伤心小男孩","PART":"手游","PINGLUN_COUNT":"0","TITLE":"《少女与战车》手游推出多位角色泳装形象福利","ZAN_COUNT":"0"}]
     * total : 27
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
         * COLLECT_COUNT : 0
         * COVERIMG : /upLoadResource/4f0fcde73a244e37a3f14d7d855cf38b/【资讯】24.《少女与战车》手游推出多位角色泳装形象福利 字数：461110.png
         * CREATEDATE : 2017-09-16 17:41:57
         * HEAD_PATH : /upLoadResource/5f162825dd714c549cce22da687a4b16/5f162825dd714c549cce22da687a4b16.png
         * ID : 007d551712ca431d81fb6086df25c9ed
         * NICK_NAME : 伤心小男孩
         * PART : 手游
         * PINGLUN_COUNT : 0
         * TITLE : 《少女与战车》手游推出多位角色泳装形象福利
         * ZAN_COUNT : 0
         */

        private String BOUTIQUE;
        private String COLLECT_COUNT;
        private String COVERIMG;
        private String CREATEDATE;
        private String HEAD_PATH;
        private String ID;
        private String NICK_NAME;
        private String PART;
        private String PINGLUN_COUNT;
        private String TITLE;
        private String ZAN_COUNT;

        public String getBOUTIQUE() {
            return BOUTIQUE;
        }

        public void setBOUTIQUE(String BOUTIQUE) {
            this.BOUTIQUE = BOUTIQUE;
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

        public String getZAN_COUNT() {
            return ZAN_COUNT;
        }

        public void setZAN_COUNT(String ZAN_COUNT) {
            this.ZAN_COUNT = ZAN_COUNT;
        }
    }
}
