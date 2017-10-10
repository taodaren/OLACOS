package net.osplay.app;

import net.osplay.service.entity.AllCommunityBean;

import java.util.List;

/**
 * 接口类，抽象方法用来获取过滤后的数据
 * @author 邹奇
 *
 */
public interface FilterListener {
    void getFilterData(List<AllCommunityBean.RowsBean> rows);// 获取过滤后的数据
}