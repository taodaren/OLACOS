package net.osplay.module_secondhand;

/**
 * 二手交易实体类
 */

public class SecondhandBean {
    private int imgId;
    private String price;
    private String title;

    public SecondhandBean(int imgId, String price, String title) {
        this.imgId = imgId;
        this.price = price;
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
