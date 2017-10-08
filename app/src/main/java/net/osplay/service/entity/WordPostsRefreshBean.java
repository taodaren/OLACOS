package net.osplay.service.entity;

import java.util.List;

/**
 * 热帖刷新
 */

public class WordPostsRefreshBean {

    private List<PartBean> part;//查询大区的信息
    private List<DataBean> data;//查询大区的帖子信息

    public List<PartBean> getPart() {
        return part;
    }

    public void setPart(List<PartBean> part) {
        this.part = part;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PartBean {
        /**
         * PART_PATH : /upLoadResource/c66d6fb8d921406e8edfeccf7566e120/cos.jpg
         * TOPICK_COUNT : 62
         * PART : COS社区
         * ID : 1
         */

        private String PART_PATH;//当前大区图片
        private String TOPICK_COUNT;//当前大区帖子总数
        private String PART;//当前大区名字
        private String ID;//当前大区id

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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }

    public static class DataBean {
        /**
         * PINGLUN_COUNT : 0
         * COVERIMG : /upLoadResource/2083e8c9c163475c93adac3dd8dcff85/【Cosplay】8.缘之空软萌少女 春日野穹COS 字数：236837.png
         * VIEW_COUNT : 4
         * TITLE : 缘之空软萌少女 春日野穹COS
         * PARTNAME : COS正片
         * ID : 70f5738b81454eddb0b65747fbc59f7e
         */

        private String PINGLUN_COUNT;//帖子评论数量
        private String COVERIMG;//帖子封面图
        private String VIEW_COUNT;//帖子标题
        private String TITLE;//帖子阅读量
        private String PARTNAME;//专区名字
        private String ID;//帖子id

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

        public String getVIEW_COUNT() {
            return VIEW_COUNT;
        }

        public void setVIEW_COUNT(String VIEW_COUNT) {
            this.VIEW_COUNT = VIEW_COUNT;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getPARTNAME() {
            return PARTNAME;
        }

        public void setPARTNAME(String PARTNAME) {
            this.PARTNAME = PARTNAME;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
    }
}
