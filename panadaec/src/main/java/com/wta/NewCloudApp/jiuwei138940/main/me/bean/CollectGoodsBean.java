package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

import java.util.List;

/**
 * Created by zzc on 2017/9/29.
 */

public class CollectGoodsBean {




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
             * pagesize : 10
             * list : [{"id":"899","createtime":"1506934038","goodsid":"4324","thumb":"http://pandashopimg.anmur.com/be907725d04bd27a8580010cca80fd76.jpg","productprice":"980.00","marketprice":"688.00","title":"锦蕾深海野生干海参辽参50g*2盒"}]
             */

            private int pagesize;
            private List<ListBean> list;

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 899
                 * createtime : 1506934038
                 * goodsid : 4324
                 * thumb : http://pandashopimg.anmur.com/be907725d04bd27a8580010cca80fd76.jpg
                 * productprice : 980.00
                 * marketprice : 688.00
                 * title : 锦蕾深海野生干海参辽参50g*2盒
                 */

                private String id;
                private String createtime;
                private String goodsid;
                private String thumb;
                private String productprice;
                private String marketprice;
                private String title;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

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

                public String getProductprice() {
                    return productprice;
                }

                public void setProductprice(String productprice) {
                    this.productprice = productprice;
                }

                public String getMarketprice() {
                    return marketprice;
                }

                public void setMarketprice(String marketprice) {
                    this.marketprice = marketprice;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
    }
}
