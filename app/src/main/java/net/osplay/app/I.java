package net.osplay.app;

/**
 * 全局接口参数
 */

public interface I {
    //////////////////// 正式接口 ////////////////////

    // TODO: 2017/9/24 基础 url
//    String BASE_URL = "http://120.27.22.118:8080/qda";//服务器
    String BASE_URL = "http://192.168.1.5:8080/qda";//本地（朱）
//    String BASE_URL = "http://192.168.1.9:8080/qda";//本地（素）

    // TODO: 2017/9/24 注册登录
    //注册
    String REGISTER = BASE_URL + "/memberMobile/memberSave.do";
    //登录
    String LOGIN = BASE_URL + "/memberMobile/login.do";
    //验证码
    String VERIFICATION_CODE = BASE_URL + "/memberMobile/sendRegisterMsg.do";
    //验证手机号码是否注册
    String IS_REGISTER = BASE_URL + "/memberMobile/checkPhone.do";

    // TODO: 2017/9/24 社区
    //加入的专区
    String ADD_WORD = BASE_URL + "/partMobile/myarrondi.do";
    //推荐的专区
    String RECOM_WORD = BASE_URL + "/partMobile/recommend.do";
    //专区
    String AREA = BASE_URL + "/partMobile/all.do";
    //请求专区
    String AREA_SUB = BASE_URL + "/partMobile/twoPart.do";
    //帖子列表
    String POSTS_LIST = BASE_URL + "/topicMobile/postBarPage.do";
    //帖子点赞
    String POSTS_ZAN = BASE_URL + "/topicMobile/zan.do";
    //帖子收藏
    String POSTS_COLLECT = BASE_URL + "/topicMobile/foCollect.do";
    //关注用户
    String FOLLOW = BASE_URL + "/topicMobile/foFollow.do";
    //是否关注用户
    String IS_FOLLOW = BASE_URL + "/topicMobile/isFollow.do";
    //帖子详情
    String POSTS_DETAIL = BASE_URL + "/topicMobile/topicDetail.do";

    // TODO: 2017/9/24 评论
    //查询一级评论总数（用于评论分页）
    String QUERY_COMMENT_COUNT = BASE_URL + "/topicMobile/queryOneCommentCount.do";
    //分页查询一级评论及一级评论下默认显示的二级评论
    String QUERY_COMMENT = BASE_URL + "/topicMobile/queryCommentPage.do";
    //分页查询二级评论
    String QUERY_COMMENT_SUB = BASE_URL + "/topicMobile/queryTwoCommentPage.do";

    // TODO: 2017/9/24 提交评论
    //查询可以@的所有用户
    String FOLLOW_ALL_AT = BASE_URL + "/topicMobile/followAll.do";
    //保存评论
    String WORD_ = BASE_URL + "/topicMobile/publishComment.do";

    // TODO: 2017/9/24 热帖
    //热帖列表
    String POSTS_HOT_LIST = BASE_URL + "/topicMobile/hotTopic.do";
    //热帖刷新
    String POSTS_REFRESH = BASE_URL + "/topicMobile/refreshHotTopic.do";


    //////////////////// 测试接口 ////////////////////

    //拼接 url
    String ADD_TEXT_DATA = "findNewAndBoutiqueGoods";
    //首页 Banner
    String HOME_BANNER = "http://112.124.22.238:8081/course_api/banner/query?type=1";
    //首页 Detail （暂时使用视频接口）
    String HOME_DETAIL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    //福利 url
    String MSG_FULI = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/2";
}
