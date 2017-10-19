package com.wta.NewCloudApp.jiuwei138940.main.cart.bean;
import java.util.List;


public class CartEntity {

    /**
     * ret : 200
     * data : {"ret":0,"msg":"","info":[{"merch":{"uid":"10","merchname":"熊猫公社自营店","logo":"http://wximgone.ergongshe.com/b7861ae444612e8f58abab6871788995.png"},"goodsList":[{"uid":"10","goodsid":"1022","selected":"1","selectedadd":"1","total":"32","marketprice":"45.00","thumb":"http://wximgone.ergongshe.com/a69f6afed4207e6c5f3d1c5ffefe7fdd.jpg","title":"创意折叠黑胶防晒两用伞防紫外线铁塔伞太阳伞遮阳伞","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"10","goodsid":"3785","selected":"1","selectedadd":"1","total":"1","marketprice":"56.00","thumb":"http://pandashopimg.anmur.com/8d1cbc95fa2b7b450e2d502e12e572fb.jpg","title":"民族风波西米亚灯笼裤沙滩裤哈伦裤女夏大码印花裤薄款雪纺裤子","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"}]},{"merch":{"uid":"42","merchname":"熊猫全球购","logo":"http://wximgone.ergongshe.com/04f5811750b240c51a9488e17dfbd297.jpg"},"goodsList":[{"uid":"42","goodsid":"2878","selected":"1","selectedadd":"1","total":"1","marketprice":"129.00","thumb":"http://wximgone.ergongshe.com/680b63564c326df3658f65e60d9a1821.jpg","title":"【香港直邮】日本 Biore/碧柔 水感精华保湿防晒霜 17年新版 50g*2","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2834","selected":"1","selectedadd":"1","total":"1","marketprice":"99.00","thumb":"http://wximgone.ergongshe.com/d7476fe2c91c7910587d1e3b3b6fb5ba.jpg","title":"日本奇士美/KissMe 花漾美姬加强浓密睫毛膏 01漆黑色 6g 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2385","selected":"1","selectedadd":"1","total":"0","marketprice":"238.00","thumb":"http://wximgone.ergongshe.com/adbbefa6fc0b2550c9f70e87bd0ea32a.jpg","title":"韩国蒂佳婷/Dr.Jart+ V7美白素颜霜 50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2383","selected":"1","selectedadd":"1","total":"2","marketprice":"59.00","thumb":"http://wximgone.ergongshe.com/bb574a87d7d7321e21cf315913fe122e.jpg","title":"韩国 悦诗风吟/innisfree 真萃草本薄荷控油散粉 5g 香港直邮","issendfree":"1","dispatchprice":"0.01","minbu y":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2380","selected":"1","selectedadd":"1","total":"1","marketprice":"39.00","thumb":"http://wximgone.ergongshe.com/7a7514e777c45db5f7ceeaf94e810d0d.jpg","title":"韩国 自然乐园/NatureRepublic 92%芦荟凝胶 300ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2374","selected":"1","selectedadd":"1","total":"1","marketprice":"69.00","thumb":"http://pandashopimg.anmur.com/ad0e0639173635d9c085bc08cd587419.jpg","title":"韩国MISSHA/谜尚遮瑕防晒红BB霜（21） 50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2373","selected":"1","selectedadd":"1","total":"1","marketprice":"219.00","thumb":"http://wximgone.ergongshe.com/e4eedcdc7360098fd9c2a01137dd3e42.jpg","title":"韩国 兰芝/Laneige 气垫BB霜 SPF50+ PA+++（21） 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2363","selected":"1","selectedadd":"1","total":"5","marketprice":"279.00","thumb":"http://wximgone.ergongshe.com/581ba6a52edbeef5e8602efa78a70eec.jpg","title":"意大利 阿玛尼/Armani 臻致丝绒哑光唇釉6.5ml 四色可选 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2376","selected":"1","selectedadd":"1","total":"2","marketprice":"89.00","thumb":"http://wximgone.ergongshe.com/86ad61145dc22470651a34a824dcae7e.png","title":"韩国 伊思/It&#039;sSkin 晶钻蜗牛洗面奶 150ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2393","selected":"1","selectedadd":"1","total":"1","marketprice":"601.00","thumb":"http://wximgone.ergongshe.com/6309abd12805abdfa4809bedb66194c3.jpg","title":"法国 Dior迪奥花漾甜心小姐女士香水EDT50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"}]}]}
     * msg :
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * ret : 0
         * msg :
         * info : [{"merch":{"uid":"10","merchname":"熊猫公社自营店","logo":"http://wximgone.ergongshe.com/b7861ae444612e8f58abab6871788995.png"},"goodsList":[{"uid":"10","goodsid":"1022","selected":"1","selectedadd":"1","total":"32","marketprice":"45.00","thumb":"http://wximgone.ergongshe.com/a69f6afed4207e6c5f3d1c5ffefe7fdd.jpg","title":"创意折叠黑胶防晒两用伞防紫外线铁塔伞太阳伞遮阳伞","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"10","goodsid":"3785","selected":"1","selectedadd":"1","total":"1","marketprice":"56.00","thumb":"http://pandashopimg.anmur.com/8d1cbc95fa2b7b450e2d502e12e572fb.jpg","title":"民族风波西米亚灯笼裤沙滩裤哈伦裤女夏大码印花裤薄款雪纺裤子","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"}]},{"merch":{"uid":"42","merchname":"熊猫全球购","logo":"http://wximgone.ergongshe.com/04f5811750b240c51a9488e17dfbd297.jpg"},"goodsList":[{"uid":"42","goodsid":"2878","selected":"1","selectedadd":"1","total":"1","marketprice":"129.00","thumb":"http://wximgone.ergongshe.com/680b63564c326df3658f65e60d9a1821.jpg","title":"【香港直邮】日本 Biore/碧柔 水感精华保湿防晒霜 17年新版 50g*2","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2834","selected":"1","selectedadd":"1","total":"1","marketprice":"99.00","thumb":"http://wximgone.ergongshe.com/d7476fe2c91c7910587d1e3b3b6fb5ba.jpg","title":"日本奇士美/KissMe 花漾美姬加强浓密睫毛膏 01漆黑色 6g 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2385","selected":"1","selectedadd":"1","total":"0","marketprice":"238.00","thumb":"http://wximgone.ergongshe.com/adbbefa6fc0b2550c9f70e87bd0ea32a.jpg","title":"韩国蒂佳婷/Dr.Jart+ V7美白素颜霜 50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2383","selected":"1","selectedadd":"1","total":"2","marketprice":"59.00","thumb":"http://wximgone.ergongshe.com/bb574a87d7d7321e21cf315913fe122e.jpg","title":"韩国 悦诗风吟/innisfree 真萃草本薄荷控油散粉 5g 香港直邮","issendfree":"1","dispatchprice":"0.01","minbu y":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2380","selected":"1","selectedadd":"1","total":"1","marketprice":"39.00","thumb":"http://wximgone.ergongshe.com/7a7514e777c45db5f7ceeaf94e810d0d.jpg","title":"韩国 自然乐园/NatureRepublic 92%芦荟凝胶 300ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2374","selected":"1","selectedadd":"1","total":"1","marketprice":"69.00","thumb":"http://pandashopimg.anmur.com/ad0e0639173635d9c085bc08cd587419.jpg","title":"韩国MISSHA/谜尚遮瑕防晒红BB霜（21） 50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2373","selected":"1","selectedadd":"1","total":"1","marketprice":"219.00","thumb":"http://wximgone.ergongshe.com/e4eedcdc7360098fd9c2a01137dd3e42.jpg","title":"韩国 兰芝/Laneige 气垫BB霜 SPF50+ PA+++（21） 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2363","selected":"1","selectedadd":"1","total":"5","marketprice":"279.00","thumb":"http://wximgone.ergongshe.com/581ba6a52edbeef5e8602efa78a70eec.jpg","title":"意大利 阿玛尼/Armani 臻致丝绒哑光唇釉6.5ml 四色可选 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2376","selected":"1","selectedadd":"1","total":"2","marketprice":"89.00","thumb":"http://wximgone.ergongshe.com/86ad61145dc22470651a34a824dcae7e.png","title":"韩国 伊思/It&#039;sSkin 晶钻蜗牛洗面奶 150ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"42","goodsid":"2393","selected":"1","selectedadd":"1","total":"1","marketprice":"601.00","thumb":"http://wximgone.ergongshe.com/6309abd12805abdfa4809bedb66194c3.jpg","title":"法国 Dior迪奥花漾甜心小姐女士香水EDT50ml 香港直邮","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"}]}]
         */

        private int ret;
        private String msg;
        private List<InfoBean> info;

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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * merch : {"uid":"10","merchname":"熊猫公社自营店","logo":"http://wximgone.ergongshe.com/b7861ae444612e8f58abab6871788995.png"}
             * goodsList : [{"uid":"10","goodsid":"1022","selected":"1","selectedadd":"1","total":"32","marketprice":"45.00","thumb":"http://wximgone.ergongshe.com/a69f6afed4207e6c5f3d1c5ffefe7fdd.jpg","title":"创意折叠黑胶防晒两用伞防紫外线铁塔伞太阳伞遮阳伞","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"},{"uid":"10","goodsid":"3785","selected":"1","selectedadd":"1","total":"1","marketprice":"56.00","thumb":"http://pandashopimg.anmur.com/8d1cbc95fa2b7b450e2d502e12e572fb.jpg","title":"民族风波西米亚灯笼裤沙滩裤哈伦裤女夏大码印花裤薄款雪纺裤子","issendfree":"1","dispatchprice":"0.01","minbuy":"0","maxbuy":"0","usermaxbuy":"0"}]
             */

            private MerchBean merch;
            private List<GoodsListBean> goodsList;

            public MerchBean getMerch() {
                return merch;
            }

            public void setMerch(MerchBean merch) {
                this.merch = merch;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public static class MerchBean {
                /**
                 * uid : 10
                 * merchname : 熊猫公社自营店
                 * logo : http://wximgone.ergongshe.com/b7861ae444612e8f58abab6871788995.png
                 */

                private String uid;
                private String merchname;
                private String logo;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getMerchname() {
                    return merchname;
                }

                public void setMerchname(String merchname) {
                    this.merchname = merchname;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }
            }

            public static class GoodsListBean {
                /**
                 * uid : 10
                 * goodsid : 1022
                 * selected : 1
                 * selectedadd : 1
                 * total : 32
                 * marketprice : 45.00
                 * thumb : http://wximgone.ergongshe.com/a69f6afed4207e6c5f3d1c5ffefe7fdd.jpg
                 * title : 创意折叠黑胶防晒两用伞防紫外线铁塔伞太阳伞遮阳伞
                 * issendfree : 1
                 * dispatchprice : 0.01
                 * minbuy : 0
                 * maxbuy : 0
                 * usermaxbuy : 0
                 */

                private String uid;
                private String goodsid;
                private String selected;
                private String selectedadd;
                private String total;
                private String marketprice;
                private String thumb;
                private String title;
                private String issendfree;
                private String dispatchprice;
                private String minbuy;
                private String maxbuy;
                private String usermaxbuy;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getGoodsid() {
                    return goodsid;
                }

                public void setGoodsid(String goodsid) {
                    this.goodsid = goodsid;
                }

                public String getSelected() {
                    return selected;
                }

                public void setSelected(String selected) {
                    this.selected = selected;
                }

                public String getSelectedadd() {
                    return selectedadd;
                }

                public void setSelectedadd(String selectedadd) {
                    this.selectedadd = selectedadd;
                }

                public String getTotal() {
                    return total;
                }

                public void setTotal(String total) {
                    this.total = total;
                }

                public String getMarketprice() {
                    return marketprice;
                }

                public void setMarketprice(String marketprice) {
                    this.marketprice = marketprice;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIssendfree() {
                    return issendfree;
                }

                public void setIssendfree(String issendfree) {
                    this.issendfree = issendfree;
                }

                public String getDispatchprice() {
                    return dispatchprice;
                }

                public void setDispatchprice(String dispatchprice) {
                    this.dispatchprice = dispatchprice;
                }

                public String getMinbuy() {
                    return minbuy;
                }

                public void setMinbuy(String minbuy) {
                    this.minbuy = minbuy;
                }

                public String getMaxbuy() {
                    return maxbuy;
                }

                public void setMaxbuy(String maxbuy) {
                    this.maxbuy = maxbuy;
                }

                public String getUsermaxbuy() {
                    return usermaxbuy;
                }

                public void setUsermaxbuy(String usermaxbuy) {
                    this.usermaxbuy = usermaxbuy;
                }
            }
        }
    }
}
