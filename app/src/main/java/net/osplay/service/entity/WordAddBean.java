package net.osplay.service.entity;

/**
 * 加入的社区
 */

public class WordAddBean {

    /**
     * PART_PATH : /image/main/theme/themem06.jpg
     * PART : 影视
     * ID : 6
     * NOTES : 供人放松娱乐的地方
     */

    private String PART_PATH;
    private String PART;
    private String ID;
    private String NOTES;

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

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }
}
