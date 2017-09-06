package net.osplay.utils;

import android.util.SparseArray;

import net.osplay.data.bean.HomeData;
import net.osplay.service.entity.HomeBannerBean;
import java.util.List;

/**
 * Created by xns on 2017/9/6.
 * HomeData的包装类
 */

public class HomeDataMapper {
    private static SparseArray<List<HomeData>> mHomeDataMap = new SparseArray<>();

    public static HomeData transformBannerDatas(List<HomeBannerBean> beans, int adapterType, boolean isSpan) {
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

    public static SparseArray<List<HomeData>> getHomeDataMap() {
        return mHomeDataMap;
    }

    public static void reset() {
        mHomeDataMap.clear();
    }
}
