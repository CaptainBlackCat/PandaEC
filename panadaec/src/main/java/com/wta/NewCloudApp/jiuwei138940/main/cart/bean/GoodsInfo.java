package com.wta.NewCloudApp.jiuwei138940.main.cart.bean;



public class GoodsInfo {
    protected boolean isChoosed;
    protected String id;
    protected String title;
    protected int total;
    private double price;
    private double dispatchprice;
    private String thumb;
    private int position;

    public GoodsInfo(String id, String title, int total, double price, double dispatchprice, String thumb) {
        this.id = id;
        this.title = title;
        this.total = total;
        this.price = price;
        this.dispatchprice = dispatchprice;
        this.thumb = thumb;
    }



    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDispatchprice() {
        return dispatchprice;
    }

    public void setDispatchprice(double dispatchprice) {
        this.dispatchprice = dispatchprice;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
