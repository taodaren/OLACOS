package net.osplay.service.entity;

import java.util.List;

/**
 * Created by fanyanmin on 2017/9/29.
 */

public class MyAreaBean {

    /**
     * total : 5
     * rows : [{"PART_PATH":"/upLoadResource/cb428cb775de4f03a623214a00b737a9/周边.jpg","TOPICK_COUNT":"35","PART":"周边","MEMBER_COUNT":"3","ID":"8"},{"PART_PATH":"/upLoadResource/0eb9d565b30049d8b1488ba3b5312c75/同人.jpg","TOPICK_COUNT":"19","PART":"同人","MEMBER_COUNT":"3","ID":"7"},{"PART_PATH":"/upLoadResource/8b31f99d65724fffb1896a718c63f761/服装.jpg","TOPICK_COUNT":"33","PART":"服装","MEMBER_COUNT":"7","ID":"2"},{"PART_PATH":"/image/main/theme/themem04.jpg","TOPICK_COUNT":"42","PART":"游戏","MEMBER_COUNT":"4","ID":"4"},{"PART_PATH":"/upLoadResource/9fd3fcd37f564911a935e409b2e5f411/动画.jpg","TOPICK_COUNT":"52","PART":"动漫","MEMBER_COUNT":"2","ID":"5"}]
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
         * PART_PATH : /upLoadResource/cb428cb775de4f03a623214a00b737a9/周边.jpg
         * TOPICK_COUNT : 35
         * PART : 周边
         * MEMBER_COUNT : 3
         * ID : 8
         */

        private String PART_PATH;
        private String TOPICK_COUNT;
        private String PART;
        private String MEMBER_COUNT;
        private String ID;

        public String getPART_PATH() {
            return PART_PATH;
        }

        public void setPART_PATH(String PART_PATH) {
            this.PART_PATH = PART_PATH;
        }

        public String getTOPICK_COUNT() {
            return TOPICK_COUNT;
        }

        public void setTOPICK_COUNT(String TOPICK_COUNT) {
            this.TOPICK_COUNT = TOPICK_COUNT;
        }

        public String getPART() {
            return PART;
        }

        public void setPART(String PART) {
            this.PART = PART;
        }

        public String getMEMBER_COUNT() {
            return MEMBER_COUNT;
        }

        public void setMEMBER_COUNT(String MEMBER_COUNT) {
            this.MEMBER_COUNT = MEMBER_COUNT;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
