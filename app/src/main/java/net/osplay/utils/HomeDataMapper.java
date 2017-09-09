package net.osplay.utils;

import android.util.SparseArray;

import net.osplay.service.entity.base.HomeData;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.service.entity.VideoBean;

import java.util.ArrayList;
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

    public static HomeData transformRecommendData(List<HomeBannerBean> beans, int adapterType, boolean isSpan) {
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

    public static HomeData transformTabData(List<String> beans, int adapterType, boolean isSpan) {
        HomeData<List<String>> homeData;
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

    private static HomeData transformVideoData(VideoBean bean, int adapterType, boolean isSpan) {
        if (bean == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final HomeData<VideoBean> resData = new HomeData<>();
        resData.setItemType(adapterType);
        resData.setSpan(isSpan);
        resData.setLocal(false);
        resData.setData(bean);
        return resData;
    }

    public static List<HomeData> transformVideoDatas(List<VideoBean> beans, int adapterType, boolean isSpan) {
        List<HomeData> resDataCollection;
        if (beans != null && !beans.isEmpty()) {
            resDataCollection = new ArrayList<>();
            for (VideoBean bean : beans) {
                resDataCollection.add(transformVideoData(bean, adapterType, isSpan));
            }
            mHomeDataMap.put(adapterType, resDataCollection);
            return resDataCollection;
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
