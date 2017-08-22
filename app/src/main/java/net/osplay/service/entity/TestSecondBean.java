package net.osplay.service.entity;

/**
 * 二手交易测试实体类
 */

public class TestSecondBean {
    //商品标题
    private String goodsBrief;
    //商品价格
    private String shopPrice;
    //商品图片
    private String goodsImg;

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "TestSecondBean{" +
                "goodsBrief='" + goodsBrief + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                '}';
    }
}
