package net.osplay.service.entity;

/**
 * 热帖详情
 */

public class WordDetailsPostsBean {

    /**
     * ISDELETE : 0
     * COVERIMG : /upLoadResource/2a40340a5c06479caeea39f8007dda4b/1496453261173.jpg
     * BOUTIQUE : 1
     * MANAAGE : 1
     * ZAN_COUNT : 1
     * VIEW_COUNT : 1
     * ZAN : false
     * CREATEDATE : 2017-09-21 18:59:40
     * HINTTEXT : null
     * COLLECT : false
     * ORIGINAL : 0
     * NICK_NAME : 雪颜er
     * PARTID : 10
     * PINGLUN_COUNT : 1
     * DISTRICT : 山东省,济南市
     * TOP : 1
     * PART : 道具
     * COLLECT_COUNT : 2
     * USERID : 3f68feea89784e20873f1de2e694880c
     * HINTIMG : null
     * TITLE : 问问
     * ID : 7517c971cdf94713b33f66472ef9ad39
     * CONTENT : <p><img src="/qda/upLoadResource/image/20170921/1505991600259061171.jpg" title="1505991600259061171.jpg" alt="1496453261173.jpg"/></p>
     * BIGPARTID : 1
     */

    private String ISDELETE;
    private String COVERIMG;//封面图
    private String BOUTIQUE;//"0"：精品；"1"非精品
    private String MANAAGE;//"1"：管理员  其他：成员
    private String ZAN_COUNT;//点赞数量
    private String VIEW_COUNT;//阅读量
    private String ZAN;//是否点赞  "true"已点赞 "false"未点赞
    private String CREATEDATE;//发布时间
    private String HINTTEXT;
    private String COLLECT;//是否收藏 "true"已收藏 "false"未收藏
    private String ORIGINAL;//"0"：原创 ，其他："非原创"
    private String NICK_NAME;//作者昵称
    private String PARTID;//专区 id
    private String PINGLUN_COUNT;//评论数
    private String DISTRICT;//帖子发布地区
    private String TOP;//"0"：置顶  1："非置顶"
    private String PART;//专区名（来自专区）
    private String COLLECT_COUNT;//收藏数量
    private String USERID;//作者 id
    private String HINTIMG;
    private String TITLE;
    private String ID;//帖子 id
    private String CONTENT;//帖子内容（注：内容为html可直接展示）
    private String BIGPARTID;//大区 id

    public String getISDELETE() {
        return ISDELETE;
    }

    public void setISDELETE(String ISDELETE) {
        this.ISDELETE = ISDELETE;
    }

    public String getCOVERIMG() {
        return COVERIMG;
    }

    public void setCOVERIMG(String COVERIMG) {
        this.COVERIMG = COVERIMG;
    }

    public String getBOUTIQUE() {
        return BOUTIQUE;
    }

    public void setBOUTIQUE(String BOUTIQUE) {
        this.BOUTIQUE = BOUTIQUE;
    }

    public String getMANAAGE() {
        return MANAAGE;
    }

    public void setMANAAGE(String MANAAGE) {
        this.MANAAGE = MANAAGE;
    }

    public String getZAN_COUNT() {
        return ZAN_COUNT;
    }

    public void setZAN_COUNT(String ZAN_COUNT) {
        this.ZAN_COUNT = ZAN_COUNT;
    }

    public String getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    public void setVIEW_COUNT(String VIEW_COUNT) {
        this.VIEW_COUNT = VIEW_COUNT;
    }

    public String getZAN() {
        return ZAN;
    }

    public void setZAN(String ZAN) {
        this.ZAN = ZAN;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getHINTTEXT() {
        return HINTTEXT;
    }

    public void setHINTTEXT(String HINTTEXT) {
        this.HINTTEXT = HINTTEXT;
    }

    public String getCOLLECT() {
        return COLLECT;
    }

    public void setCOLLECT(String COLLECT) {
        this.COLLECT = COLLECT;
    }

    public String getORIGINAL() {
        return ORIGINAL;
    }

    public void setORIGINAL(String ORIGINAL) {
        this.ORIGINAL = ORIGINAL;
    }

    public String getNICK_NAME() {
        return NICK_NAME;
    }

    public void setNICK_NAME(String NICK_NAME) {
        this.NICK_NAME = NICK_NAME;
    }

    public String getPARTID() {
        return PARTID;
    }

    public void setPARTID(String PARTID) {
        this.PARTID = PARTID;
    }

    public String getPINGLUN_COUNT() {
        return PINGLUN_COUNT;
    }

    public void setPINGLUN_COUNT(String PINGLUN_COUNT) {
        this.PINGLUN_COUNT = PINGLUN_COUNT;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public void setDISTRICT(String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }

    public String getTOP() {
        return TOP;
    }

    public void setTOP(String TOP) {
        this.TOP = TOP;
    }

    public String getPART() {
        return PART;
    }

    public void setPART(String PART) {
        this.PART = PART;
    }

    public String getCOLLECT_COUNT() {
        return COLLECT_COUNT;
    }

    public void setCOLLECT_COUNT(String COLLECT_COUNT) {
        this.COLLECT_COUNT = COLLECT_COUNT;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getHINTIMG() {
        return HINTIMG;
    }

    public void setHINTIMG(String HINTIMG) {
        this.HINTIMG = HINTIMG;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getBIGPARTID() {
        return BIGPARTID;
    }

    public void setBIGPARTID(String BIGPARTID) {
        this.BIGPARTID = BIGPARTID;
    }
}
