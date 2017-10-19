package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

import java.util.List;

/**
 * Created by zzc on 2017/10/11.
 */

public class SignRecordBean {




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
             * list : [{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1500139084","credit":"1","log":"日常签到+1积分","type":"0","day":"0"},{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1497106315","credit":"1","log":"日常签到+1积分","type":"0","day":"0"},{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1495345987","credit":"1","log":"日常签到+1积分","type":"0","day":"0"},{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1494661323","credit":"1","log":"日常签到+1积分","type":"0","day":"0"},{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1494494128","credit":"1","log":"日常签到+1积分","type":"0","day":"0"},{"order":"1","orderday":"1","sum":"6","signdate":"2017-07","time":"1494176960","credit":"5","log":"首次签到+5积分","type":"0","day":"0"}]
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
                 * order : 1
                 * orderday : 1
                 * sum : 6
                 * signdate : 2017-07
                 * time : 1500139084
                 * credit : 1
                 * log : 日常签到+1积分
                 * type : 0
                 * day : 0
                 */

                private String order;
                private String orderday;
                private String sum;
                private String signdate;
                private String time;
                private String credit;
                private String log;
                private String type;
                private String day;

                public String getOrder() {
                    return order;
                }

                public void setOrder(String order) {
                    this.order = order;
                }

                public String getOrderday() {
                    return orderday;
                }

                public void setOrderday(String orderday) {
                    this.orderday = orderday;
                }

                public String getSum() {
                    return sum;
                }

                public void setSum(String sum) {
                    this.sum = sum;
                }

                public String getSigndate() {
                    return signdate;
                }

                public void setSigndate(String signdate) {
                    this.signdate = signdate;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getCredit() {
                    return credit;
                }

                public void setCredit(String credit) {
                    this.credit = credit;
                }

                public String getLog() {
                    return log;
                }

                public void setLog(String log) {
                    this.log = log;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }
            }
    }
}
