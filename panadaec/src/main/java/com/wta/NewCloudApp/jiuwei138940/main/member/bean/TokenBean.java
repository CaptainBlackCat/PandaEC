package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

/**
 * Created by zzc on 2017/9/26.
 */

public class TokenBean {

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
             * token : dc24bb01f37da0929dc81c1d28573ed0
             * key : 5fb7__ewei_shopv2_member_session_4
             * cookie : eyJpZCI6IjM0MDM5Iiwib3BlbmlkIjoib1IxUk4wNFdTeU1lQ1lXVi1nR3o5d2VfSXcybyIsIm1vYmlsZSI6IjE4MjI5ODU4MTQ2IiwicHdkIjoiOGFhM2I3N2QzOGY3M2ViYjFiZDNmNDM5YWIwMTljM2IiLCJzYWx0IjoiVFlkMlRaMnRlRHoxcEl4aSIsImV3ZWlfc2hvcHYyX21lbWJlcl9oYXNoIjoiNjE0MTU4ZWU5ZjFiOGQ4ZTkxZDRjMDM1YWY2ZTNjNDYifQ==
             */

            private String token;
            private String key;
            private String cookie;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getCookie() {
                return cookie;
            }

            public void setCookie(String cookie) {
                this.cookie = cookie;
            }
        }
}
