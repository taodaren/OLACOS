package net.osplay.data.bean;

/**
 * 封装所有首页数据
 */

public class HomeData<T> {
    private T data;//封装指定对象，可存任意对象
    private boolean isLocal;//是否为本地数据
    private int itemType;//Adapter Item 类型
    private boolean isSpan;//Span Line

    public HomeData() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isSpan() {
        return isSpan;
    }

    public void setSpan(boolean span) {
        isSpan = span;
    }

}
