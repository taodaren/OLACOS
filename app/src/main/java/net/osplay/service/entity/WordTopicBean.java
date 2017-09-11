package net.osplay.service.entity;

/**
 * 社区 → 热区 → 专题
 */

public class WordTopicBean {
    private String name;
    private int imgId;

    public WordTopicBean(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
