package com.wta.NewCloudApp.jiuwei138940.main.me.bean;

/**
 * Created by zzc on 2017/9/29.
 */

public class MySelfBean {

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


            private String nickname;
            private String avatar;
            private String inviter;
            private String levelname;
            private int dfk;
            private int dfh;
            private int success;
            private int cg;
            private Credit1Bean credit1;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getInviter() {
                return inviter;
            }

            public void setInviter(String inviter) {
                this.inviter = inviter;
            }

            public String getLevelname() {
                return levelname;
            }

            public void setLevelname(String levelname) {
                this.levelname = levelname;
            }

            public int getDfk() {
                return dfk;
            }

            public void setDfk(int dfk) {
                this.dfk = dfk;
            }

            public int getDfh() {
                return dfh;
            }

            public void setDfh(int dfh) {
                this.dfh = dfh;
            }

            public int getSuccess() {
                return success;
            }

            public void setSuccess(int success) {
                this.success = success;
            }

            public int getCg() {
                return cg;
            }

            public void setCg(int cg) {
                this.cg = cg;
            }

            public Credit1Bean getCredit1() {
                return credit1;
            }

            public void setCredit1(Credit1Bean credit1) {
                this.credit1 = credit1;
            }

            public static class Credit1Bean {
                /**
                 * credit1 : 429.00
                 * credit2 : 377.28
                 */

                private String credit1;
                private String credit2;

                public String getCredit1() {
                    return credit1;
                }

                public void setCredit1(String credit1) {
                    this.credit1 = credit1;
                }

                public String getCredit2() {
                    return credit2;
                }

                public void setCredit2(String credit2) {
                    this.credit2 = credit2;
                }
            }
        }
}
