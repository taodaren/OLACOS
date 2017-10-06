package net.osplay.service.entity;

import java.util.List;

/**
 * Created by fanyanmin on 2017/9/30.
 */

public class MyFocusBean {

    /**
     * rows : [{"FANS_COUNT":"1","FOCUS_COUNT":"0","ID":"b0447389afee41b988b6372bf641cc10","NICK_NAME":"纪星星"},{"FANS_COUNT":"1","FOCUS_COUNT":"0","HEAD_PATH":"/upLoadResource/351c38f1a9f748a3ac2c1d238487634d/351c38f1a9f748a3ac2c1d238487634d.png","ID":"9e23d87dc3e2446396998c0f40426727","INTRODUCE":"With a different perspective to see the world.","NICK_NAME":"taodaren"},{"FANS_COUNT":"1","FOCUS_COUNT":"4","HEAD_PATH":"/upLoadResource/02ecf8544c414d598f342d969c4164bd/02ecf8544c414d598f342d969c4164bd.png","ID":"6cad9193fbb24a4694e2bf4f50b402bc","INTRODUCE":"哈哈哈哈哈","NICK_NAME":"15563722426"},{"FANS_COUNT":"1","FOCUS_COUNT":"0","ID":"3fcc53aeefcc46baa0f7f579ceed1c74","NICK_NAME":"克里奥佩特拉"}]
     * total : 4
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
         * FANS_COUNT : 1
         * FOCUS_COUNT : 0
         * ID : b0447389afee41b988b6372bf641cc10
         * NICK_NAME : 纪星星
         * HEAD_PATH : /upLoadResource/351c38f1a9f748a3ac2c1d238487634d/351c38f1a9f748a3ac2c1d238487634d.png
         * INTRODUCE : With a different perspective to see the world.
         */

        private String FANS_COUNT;
        private String FOCUS_COUNT;
        private String ID;
        private String NICK_NAME;
        private String HEAD_PATH;
        private String INTRODUCE;

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

        public String getINTRODUCE() {
            return INTRODUCE;
        }

        public void setINTRODUCE(String INTRODUCE) {
            this.INTRODUCE = INTRODUCE;
        }
    }
}
