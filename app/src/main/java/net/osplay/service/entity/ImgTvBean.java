package net.osplay.service.entity;

/**
 * Created by Administrator on 2017/9/7.
 */

public class ImgTvBean {
    private int imgPicture;
    private String tvText;

    public ImgTvBean(int imgPicture, String tvText) {
        this.imgPicture = imgPicture;
        this.tvText = tvText;
    }

    public int getImgPicture() {
        return imgPicture;
    }

    public void setImgPicture(int imgPicture) {
        this.imgPicture = imgPicture;
    }

    public String getTvText() {
        return tvText;
    }

    public void setTvText(String tvText) {
        this.tvText = tvText;
    }
}
