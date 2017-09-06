package net.osplay.utils;

import android.util.SparseArray;

import net.osplay.data.bean.HomeData;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.ui.adapter.TabHomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xns on 2017/9/6.
 * HomeData的包装类
 */

public class HomeDataMapper {
    private static SparseArray<List<HomeData>> mHomeDataMap = new SparseArray<>();

    private static HomeData transformBannerData(HomeBannerBean bean, int adapterType, boolean isSpan) {
        if (bean == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final HomeData<HomeBannerBean> resData = new HomeData<>();
        resData.setItemType(adapterType);
        resData.setSpan(isSpan);
        resData.setLocal(false);
        resData.setData(bean);
        return resData;
    }

    public static List<HomeData> transformBannerDatas(List<HomeBannerBean> beans, int adapterType, boolean isSpan) {
        List<HomeData> resDataCollection;
        if (beans != null && !beans.isEmpty()) {
            resDataCollection = new ArrayList<>();
            for (HomeBannerBean bean : beans) {
                resDataCollection.add(transformBannerData(bean, TabHomeAdapter.TYPE_BANNER, isSpan));
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
