package net.osplay.app;

/**
 * 全局接口参数
 */

public interface I {

    // TODO: 正式接口

    /**
     * 基础 url
     **/

     //String BASE_URL = "http://www.olacos.net";//服务器域名
//    String BASE_URL = "http://120.27.22.118:8080/qda";//服务器 IP
    String BASE_URL = "http://192.168.1.5:8080/qda";//本地（朱）
   // String BASE_URL = "http://192.168.1.9:8080/qda";//本地（素）

    /**
     * 注册登录
     **/
    //注册
    String REGISTER = BASE_URL + "/memberMobile/memberSave.do";
    //登录
    String LOGIN = BASE_URL + "/memberMobile/login.do";
    //验证码
    String VERIFICATION_CODE = BASE_URL + "/memberMobile/sendRegisterMsg.do";
    //验证手机号码是否注册
    String IS_REGISTER = BASE_URL + "/memberMobile/checkPhone.do";
    //检查昵称是否存在
    String IS_NICKNAME = BASE_URL + "/memberMobile/testNickName.do";

    /**
     * 个人中心
     */

    //修改个人信息
    String CHANGE_USER = BASE_URL + "/memberMobile/updateMember.do";
    //我的专区列表
    String MY_AREA = BASE_URL + "/centerMobile/myarrondi.do";
    //关注专区/取消专区
    String ATTENORCANCEL = BASE_URL + "/centerMobile/foMyarrondi.do";
    //我的帖子
    String MY_POSTS = BASE_URL + "/centerMobile/community.do";
    //我的收藏
    String MY_COLLECTION = BASE_URL + "/centerMobile/collect.do";
    //我关注的用户 全部
    String MY_FOCUS_ALL = BASE_URL + "/centerMobile/followAll.do";
    //我关注的用户 分页
    String MY_FOCUS_PAGER = BASE_URL + "/centerMobile/follow.do";
    //我的粉丝 分页
    String MY_FANS_PAGER = BASE_URL + "/centerMobile/fans.do";
    //成为/取消 粉丝
    String IS_FANS = BASE_URL + "/centerMobile/foFollow.do";
    //点赞/取消赞
    String GOOD = BASE_URL + "/centerMobile/zan.do";
    //验证是否关注当前的专区
    String IS_ATTENTION = BASE_URL + "/centerMobile/departBymemberId.do";
    //他人个人主页
    String OTHER_CENTER = BASE_URL + "/member/getMemberInfo.do";

    /**
     * 社区
     **/
    //加入的专区
    String ADD_WORD = BASE_URL + "/partMobile/myarrondi.do";
    //推荐的专区
    String RECOM_WORD = BASE_URL + "/partMobile/recommend.do";
    //专区
    String AREA = BASE_URL + "/partMobile/all.do";
    //专区子分区
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

    /**
     * 评论
     **/
    //查询一级评论总数（用于评论分页）
    String QUERY_COMMENT_COUNT = BASE_URL + "/topicMobile/queryOneCommentCount.do";
    //分页查询一级评论及一级评论下默认显示的二级评论
    String QUERY_COMMENT = BASE_URL + "/topicMobile/queryCommentPage.do";
    //分页查询二级评论
    String QUERY_COMMENT_SUB = BASE_URL + "/topicMobile/queryTwoCommentPage.do";

    /**
     * 提交评论
     **/
    //查询可以@的所有用户
    String FOLLOW_ALL_AT = BASE_URL + "/topicMobile/followAll.do";
    //保存评论
    String WORD_ = BASE_URL + "/topicMobile/publishComment.do";

    /**
     * 热帖
     **/
    //热帖列表
    String POSTS_HOT_LIST = BASE_URL + "/topicMobile/hotTopic.do";
    //热帖刷新
    String POSTS_REFRESH = BASE_URL + "/topicMobile/refreshHotTopic.do";


 /**
  * 社团
  */
    //查询已经参加过的社团（可用于判断是否参加过社团）
    String IS_JOIN=BASE_URL + "/corporationMobile/joinCorporation.do";
    //创建社团
    String ESTABLISH=BASE_URL+ "/corporationMobile/addCorporation.do";
    //查询全部社团
    String ALL_ASSOCIATION=BASE_URL+"/corporationMobile/corporationList.do";
    //添加社团成员（申请加入社团）
    String ADD_GROUP_MEMBER=BASE_URL + "/corporationMobile/addGroupMember.do";
    //查询社团成员
    String SELECT_GROUP_MEMBER=BASE_URL+"/corporationMobile/memberList.do";


    /**
     * 获取省市国家等数据
     **/
    String ASCII_CITY = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js";


    // TODO: 测试接口

    //拼接 url
    String ADD_TEXT_DATA = "findNewAndBoutiqueGoods";
    //首页 Banner
    String HOME_BANNER = "http://112.124.22.238:8081/course_api/banner/query?type=1";
    //首页 Detail （暂时使用视频接口）
    String HOME_DETAIL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    //福利 url
    String MSG_FULI = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/2";


    // TODO: 全局常量

    interface Account {
        String USER_NAME = "account_name";
        String PASS_WORD = "account_pwd";
        String USER_ID = "account_id";
        String IS_LOGIN = "is_login";
        String TOKEN = "account_token";
        String NICK_ICON = "nick_icon";
    }

    interface Img {
        String IMG_KEY = "img_key";
    }

    interface Type {
        String TYPE_NAME = "type_name";
    }

    interface Organization {
        String PARENT_ID = "organization_parent_id";
    }

    interface Posts {
        String POSTS_ID = "postsId";
    }

    interface Action {
        int ACTION_DO = 0;
        int ACTION_CANCEL = 1;
    }

}
