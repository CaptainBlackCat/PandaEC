package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

import java.util.List;

/**
 * Created by zzc on 2017/9/20.
 */

public class IntegralBean {

        private int ret;
        private String msg;
        private InfoBean info;

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * credit1 : 1266.00
             * goodslist : [{"goodsid":"1047","thumb":"http://wximgone.ergongshe.com/3de148a3bac7dcba2a22dcbb13e996b3.jpg","price":"45.00","credit":"4500","money":"0.00","title":"遇水变色斑马伞防晒黑胶折叠遮阳伞","endtime":"1496841660"},{"goodsid":"932","thumb":"http://wximgone.ergongshe.com/1bafdacb5a25ef12d99df4fc0d32964a.jpg","price":"28.00","credit":"2800","money":"0.00","title":"手臂圈充气游泳浮圈 儿童游泳水袖双气囊加厚","endtime":"1496844960"},{"goodsid":"294","thumb":"http://wximgone.ergongshe.com/c98e32fe80a204a7eab33e2acf0a5d66.jpg","price":"68.00","credit":"6800","money":"0.00","title":"anumi澳洲进口山羊奶香皂135g 洗脸皂洁面皂男女祛痘手工精油皂","endtime":"1502880840"},{"goodsid":"302","thumb":"http://wximgone.ergongshe.com/0941738e619d0bfeb2243610ace6e759.jpg","price":"48.00","credit":"4800","money":"0.00","title":"anumi澳洲草原玫瑰味香皂100g","endtime":"1502881920"},{"goodsid":"5254","thumb":"http://pandashopimg.anmur.com/6c9cb90dd2b9d6bdf8bf140d0cf3826d.jpg","price":"149.00","credit":"14900","money":"0.00","title":"美的（Midea）电水壶HJ1508a 304不锈钢电热水壶 1.5L容量 无缝一体内胆 双层防烫烧水壶","endtime":"1502882700"},{"goodsid":"5253","thumb":"http://pandashopimg.anmur.com/2708e7e1dd8e7547bd66866b45be06f3.jpg","price":"499.00","credit":"49900","money":"0.00","title":"美的（Midea）电热水瓶PF704C-50G 304不锈钢电水壶","endtime":"1502883060"}]
             */

            private String credit1;
            private List<GoodslistBean> goodslist;

            public String getCredit1() {
                return credit1;
            }

            public void setCredit1(String credit1) {
                this.credit1 = credit1;
            }

            public List<GoodslistBean> getGoodslist() {
                return goodslist;
            }

            public void setGoodslist(List<GoodslistBean> goodslist) {
                this.goodslist = goodslist;
            }

            public static class GoodslistBean {
                /**
                 * goodsid : 1047
                 * thumb : http://wximgone.ergongshe.com/3de148a3bac7dcba2a22dcbb13e996b3.jpg
                 * price : 45.00
                 * credit : 4500
                 * money : 0.00
                 * title : 遇水变色斑马伞防晒黑胶折叠遮阳伞
                 * endtime : 1496841660
                 */

                private String goodsid;
                private String thumb;
                private String price;
                private String credit;
                private String money;
                private String title;
                private String endtime;

                public String getGoodsid() {
                    return goodsid;
                }

                public void setGoodsid(String goodsid) {
                    this.goodsid = goodsid;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getEndtime() {
                    return endtime;
                }

                public void setEndtime(String endtime) {
                    this.endtime = endtime;
                }
            }
        }
}
