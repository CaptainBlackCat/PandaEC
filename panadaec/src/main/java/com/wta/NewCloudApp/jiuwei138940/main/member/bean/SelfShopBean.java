package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

import java.util.List;

/**
 * Created by zzc on 2017/10/12.
 */

public class SelfShopBean {


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
             * id : 6651
             * title : 瑞典 FOREO/斐珞尔 LUNAgo 充电洁面仪 粉色中性肌肤
             * thumb : http://pandashopimg.anmur.com/96144a136bb7be3879d84a67cc6fd61c.jpg
             * marketprice : 699.00
             * status : 0
             */

            private String id;
            private String title;
            private String thumb;
            private String marketprice;
            private int status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
    }
}
