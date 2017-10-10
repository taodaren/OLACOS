package net.osplay.service.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 * 检查是否加入或创建过社团
 */

public class IsJoinBean {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * CN : 无相关查询数据！
         */

        private String CN;

        public String getCN() {
            return CN;
        }

        public void setCN(String CN) {
            this.CN = CN;
        }
    }
}
