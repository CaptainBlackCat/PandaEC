package com.wta.NewCloudApp.jiuwei138940.main.index.bean;

import java.util.List;

/**
 * Created by zzc on 2017/10/16.
 */

public class IndexBean {

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
             * data : [{"cellData":[{"imgurl":"http://pandashopimg.anmur.com/a4cd070640f99013add0c47bb3a1924f.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=99","title":"","text":"圣罗兰"},{"imgurl":"http://pandashopimg.anmur.com/e914639df8b3c04e2c33b1a1c7659d27.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=119","title":"","text":"贝亲"},{"imgurl":"http://pandashopimg.anmur.com/9355c53a86007971a1919f1ec03c7c7a.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=136","title":"","text":"这浪莎"},{"imgurl":"http://pandashopimg.anmur.com/497a581ff53aafd04e86fffca71fe4a9.jpg","linkurl":"","title":"","text":"悦诗风吟"},{"imgurl":"http://pandashopimg.anmur.com/2fcb4df5615d5f9db6f96e08e5b978db.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=132","title":"","text":"施华洛世奇"},{"imgurl":"http://pandashopimg.anmur.com/ef1327733d86816965af452bb5c77d69.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=116","title":"","text":"伊思"}],"cellType":"11","bottomMargin":"8"},{"cellData":[{"imgurl":"http://pandashopimg.anmur.com/a1c36cdbe2ac8b8a83f099a4046b592c.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=82"},{"imgurl":"http://pandashopimg.anmur.com/4c4906f53560d8cfc0be6808a262c3e5.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=121"},{"imgurl":"http://pandashopimg.anmur.com/f45736fa3583b099dca8e459dcc9a1b9.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=142"},{"imgurl":"http://pandashopimg.anmur.com/750c717778f8d23e260230c647d13922.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=131"},{"imgurl":"http://pandashopimg.anmur.com/fc512cc6b0d87b8d1afb97e6a62da2f4.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=135"},{"imgurl":"http://pandashopimg.anmur.com/87b0b3bc702a5befbe592b32e0066660.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=139"},{"imgurl":"http://pandashopimg.anmur.com/4c4906f53560d8cfc0be6808a262c3e5.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=137"},{"imgurl":"http://pandashopimg.anmur.com/a1c36cdbe2ac8b8a83f099a4046b592c.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=128"}],"cellType":"12"},{"cellData":[{"thumb":"http://pandashopimg.anmur.com/af5af0c3d0c42216cb8dc2a07871aa1e.jpg","goodstitle":"云泉约巴氏鲜羊奶6瓶装","price":"76.00","url":"http://api.anmur.com/Public/?s=Category.getGoodsDetail&id=6658"},{"thumb":"http://pandashopimg.anmur.com/bca96eb5529d13669f5df41eb95f2d14.jpg","goodstitle":"云泉约巴氏酸羊奶10盒装","price":"100.00","url":"http://api.anmur.com/Public/?s=Category.getGoodsDetail&id=6654"}],"cellType":"13"}]
             * pagesize : 10
             */

            private int pagesize;
            private List<DataBean> data;

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * cellData : [{"imgurl":"http://pandashopimg.anmur.com/a4cd070640f99013add0c47bb3a1924f.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=99","title":"","text":"圣罗兰"},{"imgurl":"http://pandashopimg.anmur.com/e914639df8b3c04e2c33b1a1c7659d27.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=119","title":"","text":"贝亲"},{"imgurl":"http://pandashopimg.anmur.com/9355c53a86007971a1919f1ec03c7c7a.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=136","title":"","text":"这浪莎"},{"imgurl":"http://pandashopimg.anmur.com/497a581ff53aafd04e86fffca71fe4a9.jpg","linkurl":"","title":"","text":"悦诗风吟"},{"imgurl":"http://pandashopimg.anmur.com/2fcb4df5615d5f9db6f96e08e5b978db.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=132","title":"","text":"施华洛世奇"},{"imgurl":"http://pandashopimg.anmur.com/ef1327733d86816965af452bb5c77d69.jpg","linkurl":"./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=116","title":"","text":"伊思"}]
                 * cellType : 11
                 * bottomMargin : 8
                 */

                private String cellType;
                private String bottomMargin;
                private List<CellDataBean> cellData;

                public String getCellType() {
                    return cellType;
                }

                public void setCellType(String cellType) {
                    this.cellType = cellType;
                }

                public String getBottomMargin() {
                    return bottomMargin;
                }

                public void setBottomMargin(String bottomMargin) {
                    this.bottomMargin = bottomMargin;
                }

                public List<CellDataBean> getCellData() {
                    return cellData;
                }

                public void setCellData(List<CellDataBean> cellData) {
                    this.cellData = cellData;
                }

                public static class CellDataBean {
                    /**
                     * imgurl : http://pandashopimg.anmur.com/a4cd070640f99013add0c47bb3a1924f.jpg
                     * linkurl : ./index.php?i=4&c=entry&m=ewei_shopv2&do=mobile&r=diypage&id=99
                     * title :
                     * text : 圣罗兰
                     */

                    private String imgurl;
                    private String linkurl;
                    private String text;
                    private String thumb;
                    private String goodstitle;
                    private String price;
                    private String url;

                    public String getImgurl() {
                        return imgurl;
                    }

                    public void setImgurl(String imgurl) {
                        this.imgurl = imgurl;
                    }

                    public String getLinkurl() {
                        return linkurl;
                    }

                    public void setLinkurl(String linkurl) {
                        this.linkurl = linkurl;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getGoodstitle() {
                        return goodstitle;
                    }

                    public void setGoodstitle(String goodstitle) {
                        this.goodstitle = goodstitle;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    private String catename;
                    private String closesec;
                    private String roomid;
                    private String roomtitle;
                    private String taskid;
                    private String times;
                    private String title;
                    private List<ListBean> list;


                    public String getCatename() {
                        return catename;
                    }

                    public void setCatename(String catename) {
                        this.catename = catename;
                    }

                    public String getClosesec() {
                        return closesec;
                    }

                    public void setClosesec(String closesec) {
                        this.closesec = closesec;
                    }

                    public String getRoomid() {
                        return roomid;
                    }

                    public void setRoomid(String roomid) {
                        this.roomid = roomid;
                    }

                    public String getRoomtitle() {
                        return roomtitle;
                    }

                    public void setRoomtitle(String roomtitle) {
                        this.roomtitle = roomtitle;
                    }

                    public String getTaskid() {
                        return taskid;
                    }

                    public void setTaskid(String taskid) {
                        this.taskid = taskid;
                    }

                    public String getTimes() {
                        return times;
                    }

                    public void setTimes(String times) {
                        this.times = times;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public List<ListBean> getList() {
                        return list;
                    }

                    public void setList(List<ListBean> list) {
                        this.list = list;
                    }

                    public static class ListBean {
                        /**
                         * endtime : 1508227199
                         * goods : [{"goodsid":"6614","gthumb":"http://pandashopimg.anmur.com/7f7411313d1277d0cef9b32746565534.jpg","gtitle":"宝姿国际--skin sound酵母净肤洗颜霜洁面洗面奶100g","marketprice":"108.00","maxbuy":"1","optionid":"25334","othumb":"","otitle":"100g","price":"5.00","specs":"16459","total":"2"}]
                         * starttime : 1508205600
                         */

                        private int endtime;
                        private int starttime;
                        private List<GoodsBean> goods;

                        public int getEndtime() {
                            return endtime;
                        }

                        public void setEndtime(int endtime) {
                            this.endtime = endtime;
                        }

                        public int getStarttime() {
                            return starttime;
                        }

                        public void setStarttime(int starttime) {
                            this.starttime = starttime;
                        }

                        public List<GoodsBean> getGoods() {
                            return goods;
                        }

                        public void setGoods(List<GoodsBean> goods) {
                            this.goods = goods;
                        }

                        public static class GoodsBean {
                            /**
                             * goodsid : 6614
                             * gthumb : http://pandashopimg.anmur.com/7f7411313d1277d0cef9b32746565534.jpg
                             * gtitle : 宝姿国际--skin sound酵母净肤洗颜霜洁面洗面奶100g
                             * marketprice : 108.00
                             * maxbuy : 1
                             * optionid : 25334
                             * othumb :
                             * otitle : 100g
                             * price : 5.00
                             * specs : 16459
                             * total : 2
                             */

                            private String goodsid;
                            private String gthumb;
                            private String gtitle;
                            private String marketprice;
                            private String maxbuy;
                            private String optionid;
                            private String othumb;
                            private String otitle;
                            private String price;
                            private String specs;
                            private String total;

                            public String getGoodsid() {
                                return goodsid;
                            }

                            public void setGoodsid(String goodsid) {
                                this.goodsid = goodsid;
                            }

                            public String getGthumb() {
                                return gthumb;
                            }

                            public void setGthumb(String gthumb) {
                                this.gthumb = gthumb;
                            }

                            public String getGtitle() {
                                return gtitle;
                            }

                            public void setGtitle(String gtitle) {
                                this.gtitle = gtitle;
                            }

                            public String getMarketprice() {
                                return marketprice;
                            }

                            public void setMarketprice(String marketprice) {
                                this.marketprice = marketprice;
                            }

                            public String getMaxbuy() {
                                return maxbuy;
                            }

                            public void setMaxbuy(String maxbuy) {
                                this.maxbuy = maxbuy;
                            }

                            public String getOptionid() {
                                return optionid;
                            }

                            public void setOptionid(String optionid) {
                                this.optionid = optionid;
                            }

                            public String getOthumb() {
                                return othumb;
                            }

                            public void setOthumb(String othumb) {
                                this.othumb = othumb;
                            }

                            public String getOtitle() {
                                return otitle;
                            }

                            public void setOtitle(String otitle) {
                                this.otitle = otitle;
                            }

                            public String getPrice() {
                                return price;
                            }

                            public void setPrice(String price) {
                                this.price = price;
                            }

                            public String getSpecs() {
                                return specs;
                            }

                            public void setSpecs(String specs) {
                                this.specs = specs;
                            }

                            public String getTotal() {
                                return total;
                            }

                            public void setTotal(String total) {
                                this.total = total;
                            }
                        }
                    }
                }
            }
    }
}
