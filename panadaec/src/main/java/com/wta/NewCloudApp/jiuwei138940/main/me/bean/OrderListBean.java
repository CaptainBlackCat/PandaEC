package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

import java.util.List;

/**
 * Created by zzc on 2017/10/11.
 */

public class OrderListBean {
    private List<OrderData> list;

    public OrderListBean() {
    }

    public OrderListBean(List<OrderData> list) {
        this.list = list;
    }

    public List<OrderData> getList() {
        return list;
    }

    public void setList(List<OrderData> list) {
        this.list = list;
    }

    public static class OrderData {
        private String orderNumber;
        private List<OrderGoodsData> list;

        public OrderData() {
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public List<OrderGoodsData> getList() {
            return list;
        }

        public void setList(List<OrderGoodsData> list) {
            this.list = list;
        }

        public static class OrderGoodsData {
            private String shopName;
            private List<Goods> list;

            public OrderGoodsData() {
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public List<Goods> getList() {
                return list;
            }

            public void setList(List<Goods> list) {
                this.list = list;
            }

            public static class Goods {
                private String title;
                private String price;
                private String num;

                public Goods() {
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
        }
    }
}
