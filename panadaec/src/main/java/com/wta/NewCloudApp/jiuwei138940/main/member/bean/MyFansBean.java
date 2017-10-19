package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

import java.util.List;

/**
 * Created by zzc on 2017/9/25.
 */

public class MyFansBean {


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
             * sum : 76
             * fans : 826
             * list : [{"id":"1932","nickname":"吴利群","realname":"","openid":"oR1RN03gEKxraK2YC7f7B3NqYGFE","avatar":"http://wx.qlogo.cn/mmopen/IXWgiaUsaTmqMDTvE0vSgy4SPJy0YPtmBtl5FtUzzprpa2ghlIVIwhywaSiaXReeCiaswntYGtpV8dINZfbE4l812GqdZkIZQdb/132","createtime":"1494662200","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"1942","nickname":"金大庆","realname":"","openid":"oR1RN0wjOBTqfuqB0g96wrJbM7_4","avatar":"","createtime":"1494663117","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"1944","nickname":"Zita Zhang","realname":"","openid":"oR1RN08F8ImHMv49z1mYHo-lZGJU","avatar":"http://wx.qlogo.cn/mmopen/NoFChqEQomHfUq6bHC3ojw6Fu8BFoqbRGn3OXXoYWKbUq7DB3Vcozt6LQaibkjNsFAlIX8VDlQXgdKC0df9fIow/132","createtime":"1494663500","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"1947","nickname":"Tony","realname":"","openid":"oR1RN0_IyTyoUwRl3CBzMTzbVCjU","avatar":"http://wx.qlogo.cn/mmopen/UYicv3exy3vw1oCkxRF7fHjDOKaibXEKAfuRu3wZSAIxUjsibibQGg36BuiaD3867YZDSEy3szNnIsddickibrpj8apckUOziaDg4JfK/132","createtime":"1494663663","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"2034","nickname":"只言片语","realname":"","openid":"oR1RN0zK9KG52fP18zEpjD2KCb-8","avatar":"http://wx.qlogo.cn/mmopen/CXR7xsiauq8Bfsnbiamuiay23UvPrcvv0NFyF8xBQKx4ibkE1Q8HxmDEfwHHqe8uFNmquVf36IV1jpgKHPsNCExD85lI8tWlpIk3/132","createtime":"1494672955","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"2107","nickname":"王丽锋","realname":"","openid":"oR1RN0xAyR9felAFU3vovFQvWhgk","avatar":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLC8XlucAkYtgCR7Wysv8UAZYQqHFMc3XHczHiaicZRgWvYQnrjqsbiasFxASicBwdTGH9mFSvnFmdkh3Q/0","createtime":"1494677677","isagent":"1","agenttime":"1502271945","agentnum":"0","pricenum":null},{"id":"2108","nickname":"怡然爸爸1号","realname":"","openid":"oR1RN06j7CuMD3rJinWZFnTfDYFs","avatar":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEKgcQFtiaia7gDct4hSlYLaZ9PjhricNVoSaib23rlZCLgZ06mlXPtBogFyia5I49brns3uMhdlGIp8E1g/0","createtime":"1494677813","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"2138","nickname":"琳妈","realname":"","openid":"oR1RN048beSC1aWloOpbvZNSgWGQ","avatar":"http://wx.qlogo.cn/mmopen/NoFChqEQomHh0JHqtial5XLMice9btKUZiaiaPH5wSB0FIhXzpwP6t84eibGGyNbQSc96sJmIicDXtkpIDyStciaRoEOw5MLCYHCb00/132","createtime":"1494680596","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"2139","nickname":"戴玉立","realname":"","openid":"oR1RN070sYCZZGQ23T2e0nxB8sN0","avatar":"http://wx.qlogo.cn/mmopen/CXR7xsiauq8Bfsnbiamuiay25jSx73RzwlbCB4RTGkNm9Nuko1IJvNusEMeiaDlD1jd27XR8EeqoMI6X70hMRibJuNeTLfeKJ1licY/132","createtime":"1494680676","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null},{"id":"2176","nickname":"俊逸老头","realname":"","openid":"oR1RN04QmhoNBhHADmLyXESB3ods","avatar":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM52Sib6Kl3pFBgL7Wm5KQicPUe0KZXmuIks4icworb9ibfxTc133jZ0cx3PtKH5UG1Ku2fEic3rvbeePo9j4Sol6MnfgJ484DAiapqibk/0","createtime":"1494688153","isagent":"0","agenttime":"0","agentnum":"0","pricenum":null}]
             */

            private int pagesize;
            private String sum;
            private String fans;
            private List<ListBean> list;

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }

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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1932
                 * nickname : 吴利群
                 * realname :
                 * openid : oR1RN03gEKxraK2YC7f7B3NqYGFE
                 * avatar : http://wx.qlogo.cn/mmopen/IXWgiaUsaTmqMDTvE0vSgy4SPJy0YPtmBtl5FtUzzprpa2ghlIVIwhywaSiaXReeCiaswntYGtpV8dINZfbE4l812GqdZkIZQdb/132
                 * createtime : 1494662200
                 * isagent : 0
                 * agenttime : 0
                 * agentnum : 0
                 * pricenum : null
                 */

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
