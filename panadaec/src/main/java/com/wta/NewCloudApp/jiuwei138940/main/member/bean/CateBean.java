package com.wta.NewCloudApp.jiuwei138940.main.member.bean;

import java.util.List;

/**
 * Created by zzc on 2017/10/12.
 */

public class CateBean {

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
             * id : 1
             * name : 全部商品
             * pcate : 1
             * ccate : 2
             */

            private String id;
            private String name;
            private String pcate;
            private String ccate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPcate() {
                return pcate;
            }

            public void setPcate(String pcate) {
                this.pcate = pcate;
            }

            public String getCcate() {
                return ccate;
            }

            public void setCcate(String ccate) {
                this.ccate = ccate;
            }
        }
}
