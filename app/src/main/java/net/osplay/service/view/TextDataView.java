package net.osplay.service.view;

import net.osplay.service.entity.TestBean;

/**
 * 展示测试数据接口
 */

public interface TextDataView extends View {
    //如果 presenter 请求成功，将向该方法传入请求下来的实体类
    void onSuccess(TestBean testBean);

    //如果请求失败，就会向这个 view 传入失败信息
    void onError(String result);
}
