package net.osplay.utils;

import android.util.SparseArray;

import net.osplay.data.bean.HomeData;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.service.entity.HomeDetailBean;

import java.util.List;

/**
 * HomeData 的包装类
 */

public class HomeDataMapper {
    private static SparseArray<List<HomeData>> mHomeDataMap = new SparseArray<>();

    public static HomeData transformBannerData(List<HomeBannerBean> beans, int adapterType, boolean isSpan) {
        HomeData<List<HomeBannerBean>> homeData;
        if (beans != null && !beans.isEmpty()) {
            homeData = new HomeData();
            homeData.setData(beans);
            homeData.setItemType(adapterType);
            homeData.setLocal(false);
            homeData.setSpan(isSpan);
            return homeData;
        }
        return null;
    }

    public static HomeData transformHomeGoodsData(List<HomeDetailBean.TrailersBean> beans, int adapterType, boolean isSpan) {
        HomeData<List<HomeDetailBean.TrailersBean>> homeData;
        if (beans != null && !beans.isEmpty()) {
            homeData = new HomeData();
            homeData.setData(beans);
            homeData.setItemType(adapterType);
            homeData.setLocal(false);
            homeData.setSpan(isSpan);
            return homeData;
        }
        return null;
    }

    public static SparseArray<List<HomeData>> getHomeDataMap() {
        return mHomeDataMap;
    }

    public static void reset() {
        mHomeDataMap.clear();
    }

}
