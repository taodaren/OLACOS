package net.osplay.service.entity;

/**
 * Created by Administrator on 2017/9/11.
 */

public class AllCommunityBean {
    private int icon;
    private String name;

    public AllCommunityBean() {
    }

    public AllCommunityBean(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
