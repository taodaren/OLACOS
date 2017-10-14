package net.osplay.service.entity;

/**
 * 社区 → 热区 → 专题
 */

public class WordTopicBean {

    /**
     * PART_PATH : /upLoadResource/c66d6fb8d921406e8edfeccf7566e120/cos.jpg
     * PART : COS社区
     * ID : 1
     */

    private String PART_PATH;//大区图片地址
    private String PART;//大区名称
    private String ID;//大区id

    public WordTopicBean(String PART_PATH, String PART) {
        this.PART_PATH = PART_PATH;
        this.PART = PART;
    }

    public String getPART_PATH() {
        return PART_PATH;
    }

    public void setPART_PATH(String PART_PATH) {
        this.PART_PATH = PART_PATH;
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
