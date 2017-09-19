package net.osplay.app;

/**
 * 全局接口参数
 */

public interface I {
    //基础 url
    String BASE_URL = "http://101.251.196.90:8080/FuLiCenterServerV2.0/";
    //拼接 url
    String ADD_TEXT_DATA = "findNewAndBoutiqueGoods";
    //首页 Banner
    String HOME_BANNER = "http://112.124.22.238:8081/course_api/banner/query?type=1";
    //首页 Detail （暂时使用视频接口）
    String HOME_DETAIL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    //福利 url
    String MSG_FULI = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
}
