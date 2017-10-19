package com.wta.NewCloudApp.jiuwei138940.main.index.search.SearchList;

/**
 * 下拉刷新起始值
 */

public class RefreshYield {

    private String url;
    private String pageSize; //获取page总数所要的key(pagesize)
    private String total;   //获取total总数所要的key(total)

    public RefreshYield(String url) {
        this.url = url;
    }

    public String getUrl() {
        if (url == null) {
            throw new NullPointerException("RefreshYield's url is null");
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getTotal() {
        return total;
    }
}
