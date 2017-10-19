package com.wta.NewCloudApp.jiuwei138940.main.netException.dataConverter;
import com.alibaba.fastjson.JSON;
import com.wta.NewCloudApp.jiuwei138940.main.netException.bean.retError;


public class retConverter {

    private String mJsonData = null;

    private retError dataResponse = null;

    public retConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }


    protected String getJsonData(){
        if (mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("Data is NULL!");
        }
        return mJsonData;
    }

    public retError Convert(){
        dataResponse = JSON.parseObject(getJsonData(),retError.class);
        return dataResponse;
    }

}
