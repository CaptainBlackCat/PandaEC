package com.wta.NewCloudApp.jiuwei138940.main;

/**
 * Created by Lenovo on 2017/8/30.
 */

public class Constants {
    //api.anmur.com/Public/panda/?service=category.getGoodsSort&parentid=15
    public static final String BASE_URL = "http://192.168.3.59/Public/?s=";
    //分类一级接口
    public static final String CATEGORY_PCATE_URL = BASE_URL + "category.getgoodssort&parentid=0";
    //分类二级接口
    public static final String CATEGORY_CCATE_URL = BASE_URL + "category.getgoodssort&parentid=%s";
    //分类列表 http://api.anmur.com/Public/pandaapi/?service=category.goodslist&pcate=34&ccate=54&page=1
    public static final String URL_GOODS_LIST = BASE_URL + "category.getgoodslist";

    // http://api.anmur.com/Public/pandaapi/?service=category.goodsdetail&id=4129
    public static final String URL_SHOPDETAIL=BASE_URL+"category.getgoodsdetail";

    public static final String URL_SEARCH_CATEGORY_NOKEYWORD = BASE_URL + "Panda.getSearchByType&p_id=%s&c_id=%s&page=%d&type=%s";

    //积分商城 http://192.168.3.59/Public/?s=Jifen.Getjifengoods&page=1&id=161
    public static final String URL_INTEGRAL_SHOPINFO=BASE_URL + "Jifen.Getjifengoods";

    //获取个人信息 http://192.168.3.59/Public/?service=App.Mydata.GetMydataList&id=5
    public static final String URL_MY_GETMYINFO=BASE_URL+"Mydata.GetMydataList";

    //获取我的客户 http://192.168.3.59/Public/?service=App.Mymember.GetMymember&id=1&page=1
    public static final String URL_MEMBER_GETCLIENT=BASE_URL+"Mymember.GetMymember";


    //高德地图获取城市服务接口
    public final static  String URL_GAODE_CITY="http://restapi.amap.com/v3/config/district";
    //高德地图key
    public final static  String URL_GAODE_KEY="01248504843d2479ae91d1fe06db9f85";
    //获取用户提醒设置数据
    public final static String URL_GETNOTICE=BASE_URL+"Notice.GetNotice";

    //修改用户提醒设置接口
    public final static String URL_UPDATE_NOTICE= BASE_URL+"Notice.UpdateNotice";

    //获取粉丝的接口
    public final static String URL_MEMBER_GETMYFANS=BASE_URL+"MyFans.GetMyFans";
    //获取购物车的数据信息 Cart.GetCartGoods&id=34039&page=1
    public final static String URL_SHOPCATE_GETCAR=BASE_URL+"Cart.GetCartGoods";

    //获取我的信息 http://192.168.3.59/Public/?service=App.Myself.GetMyselfmess&id=1
    public final static String URL_GETMYSELF_MESSGAE=BASE_URL+"Myself.GetMyselfmess";

    //获取收藏的商品信息
    public final static String URL_GETCOLLECTGOODS=BASE_URL+"Collect.Getcollectgoods";

    //获取我的推荐商品
    public final static String URL_GETMYSTORE=BASE_URL+"Mystore.GetMystore";

    //删除推荐商品
    public final static String URLDELETECOLLECTGOODS=BASE_URL+"Collect.Deletecollectgoods";

    //添加收藏商品
    public final static String URL_INSERTCOLLECTGOODS=BASE_URL+"Collect.Insertcollectgoods";

    //获取我的足迹商品

    public final static String URL_GETMYRECORD=BASE_URL+"Myrecord.GetMyrecord";

    //删除我的足迹商品
    public final static String URL_DELETERECORD=BASE_URL+"Myrecord.DeleteRecord";

    //获取用户签到详细记录
    public final static String URL_GETSIGNINDAY=BASE_URL+"Signin.GetSigninDay";

    //获取自选商品一级分类
    public final static String URL_ALLOPTIONALGOODS=BASE_URL+"Optional.AllOptionalGoods";


    //删除自选商品
    public final static String URL_DELETEDOPTIONALGOODS=BASE_URL+"Optional.DeletedOptionalGoods";

    //添加自选商品
    public final static String URL_INSERTOPTIONALGOODS=BASE_URL+"Optional.InsertOptionalGoods";

    //获取首页数据
    public final static  String URL_GETINDEXDATA=BASE_URL+"Index.GetIndexData";




    public final static String URL_SHOP_WEB="http://gongshe.anmur.com/app/index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=goods.detail&id=%s";
}
