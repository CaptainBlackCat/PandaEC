package com.wta.NewCloudApp.jiuwei138940.main.netException.bean;

import java.util.List;


public class retError {

    /**
     * ret : 200
     * data : {"ret":1,"msg":"goods not exists","info":[]}
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
         * ret : 1
         * msg : goods not exists
         * info : []
         */

        private int ret;
        private String msg;
        private List<?> info;

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

        public List<?> getInfo() {
            return info;
        }

        public void setInfo(List<?> info) {
            this.info = info;
        }
    }
}
