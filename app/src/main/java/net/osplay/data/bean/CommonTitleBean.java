package net.osplay.data.bean;

/**
 * Created by admin on 2017/10/9.
 */

public class CommonTitleBean {
    private String title;
    private int imgID;

    public CommonTitleBean() {
    }

    public CommonTitleBean(String title, int imgID) {
        this.title = title;
        this.imgID = imgID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
