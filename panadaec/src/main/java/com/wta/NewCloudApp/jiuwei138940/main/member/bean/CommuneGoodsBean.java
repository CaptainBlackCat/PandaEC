package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

import java.util.List;

/**
 * Created by zzc on 2017/9/29.
 */

public class CommuneGoodsBean {


    /**
     * ret : 200
     * data : {"ret":0,"msg":"","info":{"name":"","logo":"","count":"3","pagesize":10,"list":[{"marketprice":"88.00","thumb":"http://wximgone.ergongshe.com/ceb04ade9f7f2002777efc2b78588d0f.jpg","title":"正品施华蔻专属修护亮泽洗发水染烫受损修护洗发露柔顺1000ml"},{"marketprice":"38.00","thumb":"http://wximgone.ergongshe.com/b5a38012267b7f542e76d669f7c20797.jpg","title":"vinnos德国蕴诺丝韵雅控油抗屑洗发露50ml 去油洗发水便携旅行装"},{"marketprice":"59.00","thumb":"http://wximgone.ergongshe.com/e066fd629d84fd68e56a2faf91c684fd.jpg","title":"日本 资生堂/Shiseido UNO吾诺 男士洗面奶(蓝色劲凉焕肤) 130g"}]}}
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
         * info : {"name":"","logo":"","count":"3","pagesize":10,"list":[{"marketprice":"88.00","thumb":"http://wximgone.ergongshe.com/ceb04ade9f7f2002777efc2b78588d0f.jpg","title":"正品施华蔻专属修护亮泽洗发水染烫受损修护洗发露柔顺1000ml"},{"marketprice":"38.00","thumb":"http://wximgone.ergongshe.com/b5a38012267b7f542e76d669f7c20797.jpg","title":"vinnos德国蕴诺丝韵雅控油抗屑洗发露50ml 去油洗发水便携旅行装"},{"marketprice":"59.00","thumb":"http://wximgone.ergongshe.com/e066fd629d84fd68e56a2faf91c684fd.jpg","title":"日本 资生堂/Shiseido UNO吾诺 男士洗面奶(蓝色劲凉焕肤) 130g"}]}
         */

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
             * name :
             * logo :
             * count : 3
             * pagesize : 10
             * list : [{"marketprice":"88.00","thumb":"http://wximgone.ergongshe.com/ceb04ade9f7f2002777efc2b78588d0f.jpg","title":"正品施华蔻专属修护亮泽洗发水染烫受损修护洗发露柔顺1000ml"},{"marketprice":"38.00","thumb":"http://wximgone.ergongshe.com/b5a38012267b7f542e76d669f7c20797.jpg","title":"vinnos德国蕴诺丝韵雅控油抗屑洗发露50ml 去油洗发水便携旅行装"},{"marketprice":"59.00","thumb":"http://wximgone.ergongshe.com/e066fd629d84fd68e56a2faf91c684fd.jpg","title":"日本 资生堂/Shiseido UNO吾诺 男士洗面奶(蓝色劲凉焕肤) 130g"}]
             */

            private String name;
            private String logo;
            private String count;
            private int pagesize;
            private List<ListBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

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
                 * marketprice : 88.00
                 * thumb : http://wximgone.ergongshe.com/ceb04ade9f7f2002777efc2b78588d0f.jpg
                 * title : 正品施华蔻专属修护亮泽洗发水染烫受损修护洗发露柔顺1000ml
                 */

                private String marketprice;
                private String thumb;
                private String title;

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
            }
        }
    }
}
