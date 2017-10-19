package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

import java.util.List;

/**
 * Created by zzc on 2017/9/23.
 */

public class MyClientBean {



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


            private String sum;
            private String fans;
            private int pagesize;
            private List<ListBean> list;

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
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

                private String id;
                private String nickname;
                private String realname;
                private String openid;
                private String avatar;
                private String createtime;
                private String isagent;
                private String agenttime;
                private String agentnum;
                private String pricenum;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }

                public String getOpenid() {
                    return openid;
                }

                public void setOpenid(String openid) {
                    this.openid = openid;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getIsagent() {
                    return isagent;
                }

                public void setIsagent(String isagent) {
                    this.isagent = isagent;
                }

                public String getAgenttime() {
                    return agenttime;
                }

                public void setAgenttime(String agenttime) {
                    this.agenttime = agenttime;
                }

                public String getAgentnum() {
                    return agentnum;
                }

                public void setAgentnum(String agentnum) {
                    this.agentnum = agentnum;
                }

                public String getPricenum() {
                    return pricenum;
                }

                public void setPricenum(String pricenum) {
                    this.pricenum = pricenum;
                }
            }
        }
}
