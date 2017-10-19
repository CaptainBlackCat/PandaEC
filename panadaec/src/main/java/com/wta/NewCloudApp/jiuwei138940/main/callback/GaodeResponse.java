package com.wta.NewCloudApp.jiuwei138940.main.callback;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenovo on 2017/8/28.
 */

public class GaodeResponse<T> implements Serializable{
    public String status;
    public String info;
    public String infocode;
    public String count;
    public T suggestion;
    public List<T> districts;

    @Override
    public String toString() {
        return "GaodeResponse{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", infocode='" + infocode + '\'' +
                ", count='" + count + '\'' +
                ", suggestion=" + suggestion +
                ", districts=" + districts +
                '}';
    }
}
